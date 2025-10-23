package com.transportation;

import com.transportation.algorithm.PrimAlgorithm;
import com.transportation.algorithm.KruskalAlgorithm;
import com.transportation.model.*;
import com.transportation.util.JsonFileProcessor;
import com.transportation.util.DataGenerator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println("=== MST Transportation Network Optimization ===");
            
            // Generate datasets if needed
            System.out.println("Generating test datasets...");
            DataGenerator.generateAllDatasets();
            
            // Process main input file
            System.out.println("Processing graphs...");
            InputData inputData = JsonFileProcessor.readInput("data/input/assign_3_input.json");
            
            PrimAlgorithm prim = new PrimAlgorithm();
            KruskalAlgorithm kruskal = new KruskalAlgorithm();
            
            List<GraphResult> results = new ArrayList<>();
            
            for (Graph graph : inputData.graphs) {
                System.out.printf("Processing Graph %d (%d vertices, %d edges)%n", 
                    graph.id, graph.nodes.size(), graph.edges.size());
                
                InputStats stats = new InputStats(graph.nodes.size(), graph.edges.size());
                
                MSTResult primResult = prim.findMST(graph.nodes, graph.edges);
                MSTResult kruskalResult = kruskal.findMST(graph.nodes, graph.edges);
                
                GraphResult graphResult = new GraphResult(
                    graph.id, stats, primResult, kruskalResult
                );
                results.add(graphResult);
                
                System.out.printf("  - Prim: cost=%d, ops=%d, time=%.2fms%n",
                    primResult.totalCost, primResult.operationsCount, primResult.executionTimeMs);
                System.out.printf("  - Kruskal: cost=%d, ops=%d, time=%.2fms%n",
                    kruskalResult.totalCost, kruskalResult.operationsCount, kruskalResult.executionTimeMs);
            }
            
            OutputData outputData = new OutputData(results);
            JsonFileProcessor.writeOutput("data/output/assign_3_output.json", outputData);
            
            System.out.println("=== Processing completed successfully! ===");
            System.out.println("Output saved to: data/output/assign_3_output.json");
            
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}