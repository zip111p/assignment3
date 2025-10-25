package com.tasks.util;

import com.tasks.model.Graph;
import com.tasks.model.Edge;
import com.tasks.model.InputData;
import java.util.*;

public class DataGenerator {

    public static InputData generateAllDatasets() {
        InputData inputData = new InputData();

        // Small graphs (4-6 vertices) - 5 graphs
        inputData.setSmallGraphs(generateSmallGraphs(5));

        // Medium graphs (10-15 vertices) - 10 graphs
        inputData.setMediumGraphs(generateMediumGraphs(10));

        // Large graphs (20-35 vertices) - 10 graphs
        inputData.setLargeGraphs(generateLargeGraphs(10));

        // Extra large graphs (50-100 vertices) - 3 graphs
        inputData.setExtraLargeGraphs(generateExtraLargeGraphs(3));

        return inputData;
    }

    private static List<Graph> generateSmallGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 1; i <= count; i++) {
            int vertices = 4 + random.nextInt(3); // 4-6 vertices
            double density = 0.6;
            graphs.add(generateGraph("small_" + i, vertices, density, random));
        }
        return graphs;
    }

    private static List<Graph> generateMediumGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 1; i <= count; i++) {
            int vertices = 10 + random.nextInt(6); // 10-15 vertices
            double density = 0.4;
            graphs.add(generateGraph("medium_" + i, vertices, density, random));
        }
        return graphs;
    }

    private static List<Graph> generateLargeGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);

        for (int i = 1; i <= count; i++) {
            int vertices = 20 + random.nextInt(16); // 20-35 vertices
            double density = 0.3;
            graphs.add(generateGraph("large_" + i, vertices, density, random));
        }
        return graphs;
    }

    private static List<Graph> generateExtraLargeGraphs(int count) {
        List<Graph> graphs = new ArrayList<>();
        Random random = new Random(42);

        int[] verticesList = {50, 75, 100};
        for (int i = 0; i < count; i++) {
            int vertices = verticesList[i];
            double density = 0.1;
            graphs.add(generateGraph("xlarge_" + (i + 1), vertices, density, random));
        }
        return graphs;
    }

    private static Graph generateGraph(String id, int vertices, double density, Random random) {
        List<String> nodes = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            nodes.add("N" + i);
        }

        List<Edge> edges = new ArrayList<>();
        int maxEdges = vertices * (vertices - 1) / 2;
        int targetEdges = Math.max((int) (maxEdges * density), vertices - 1);

        // Create spanning tree for connectivity
        for (int i = 1; i < vertices; i++) {
            int parent = random.nextInt(i);
            int weight = 1 + random.nextInt(100);
            edges.add(new Edge("N" + parent, "N" + i, weight));
        }

        // Add remaining edges
        Set<String> addedEdges = new HashSet<>();
        for (Edge edge : edges) {
            addedEdges.add(edge.from + "-" + edge.to);
            addedEdges.add(edge.to + "-" + edge.from);
        }

        while (edges.size() < targetEdges && edges.size() < maxEdges) {
            int u = random.nextInt(vertices);
            int v = random.nextInt(vertices);
            if (u != v) {
                String edgeKey = "N" + u + "-N" + v;
                String reverseKey = "N" + v + "-N" + u;

                if (!addedEdges.contains(edgeKey) && !addedEdges.contains(reverseKey)) {
                    int weight = 1 + random.nextInt(100);
                    edges.add(new Edge("N" + u, "N" + v, weight));
                    addedEdges.add(edgeKey);
                    addedEdges.add(reverseKey);
                }
            }
        }

        return new Graph(id, nodes, edges);
    }

    public static List<Graph> getAllTestGraphs() {
        InputData inputData = generateAllDatasets();
        List<Graph> allGraphs = new ArrayList<>();
        allGraphs.addAll(inputData.getSmallGraphs());
        allGraphs.addAll(inputData.getMediumGraphs());
        allGraphs.addAll(inputData.getLargeGraphs());
        allGraphs.addAll(inputData.getExtraLargeGraphs());
        return allGraphs;
    }
}