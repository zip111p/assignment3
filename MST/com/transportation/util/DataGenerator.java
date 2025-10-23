package com.transportation.util;

import com.transportation.model.Graph;
import com.transportation.model.Edge;
import com.transportation.model.InputData;
import java.io.IOException;
import java.util.*;

public class DataGenerator {

    public static void generateAllDatasets() throws IOException {
        // Small graphs (4-6 vertices)
        List<Graph> smallGraphs = generateSmallGraphs(10);
        
        // Medium graphs (10-15 vertices)  
        List<Graph> mediumGraphs = generateMediumGraphs(10);
        
        // Large graphs (20-30 vertices)
        List<Graph> largeGraphs = generateLargeGraphs(15);

        // Save all datasets
        saveDataset(smallGraphs, "data/input/small_graphs.json");
        saveDataset(mediumGraphs, "data/input/medium_graphs.json");
        saveDataset(largeGraphs, "data/input/large_graphs.json");
        
        // Combined dataset
        List<Graph> allGraphs = new ArrayList<>();
        allGraphs.addAll(smallGraphs);
        allGraphs.addAll(mediumGraphs);
        allGraphs.addAll(largeGraphs);
        saveDataset(allGraphs, "data/input/assign_3_input.json");
    }

    private static List<Graph> generateSmallGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);
        
        for (int i = 1; i <= count; i++) {
            int vertices = 4 + random.nextInt(3); // 4-6 vertices
            graphs.add(generateGraph(i, vertices, 0.6, random));
        }
        return graphs;
    }

    private static List<Graph> generateMediumGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);
        
        for (int i = 1; i <= count; i++) {
            int vertices = 10 + random.nextInt(6); // 10-15 vertices
            graphs.add(generateGraph(i + 10, vertices, 0.4, random));
        }
        return graphs;
    }

    private static List<Graph> generateLargeGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);
        
        for (int i = 1; i <= count; i++) {
            int vertices = 20 + random.nextInt(11); // 20-30 vertices
            graphs.add(generateGraph(i + 20, vertices, 0.3, random));
        }
        return graphs;
    }

    private static Graph generateGraph(int id, int vertices, double density, Random random) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            nodes.add("N" + i);
        }

        List<Edge> edges = new ArrayList<>();
        int maxEdges = vertices * (vertices - 1) / 2;
        int targetEdges = (int) (maxEdges * density);
        targetEdges = Math.max(targetEdges, vertices - 1); // Ensure connectivity

        // First create a spanning tree to ensure connectivity
        for (int i = 1; i < vertices; i++) {
            int j = random.nextInt(i);
            int weight = 1 + random.nextInt(20);
            edges.add(new Edge("N" + j, "N" + i, weight));
        }

        // Add additional edges
        while (edges.size() < targetEdges) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            if (u != v) {
                String from = "N" + u;
                String to = "N" + v;
                
                // Check if edge already exists
                boolean exists = edges.stream().anyMatch(e -> 
                    (e.from.equals(from) && e.to.equals(to)) || 
                    (e.from.equals(to) && e.to.equals(from)));
                
                if (!exists) {
                    int weight = 1 + random.nextInt(20);
                    edges.add(new Edge(from, to, weight));
                }
            }
        }

        return new Graph(id, nodes, edges);
    }

    private static void saveDataset(List<Graph> graphs, String filePath) throws IOException {
        InputData inputData = new InputData(graphs);
        JsonFileProcessor.writeOutput(filePath, inputData);
    }
}