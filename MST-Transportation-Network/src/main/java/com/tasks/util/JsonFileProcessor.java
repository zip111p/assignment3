package com.tasks.util;

import com.tasks.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.*;
import java.util.*;

public class JsonFileProcessor {
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public static void saveInputData(InputData inputData, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();
            mapper.writeValue(file, inputData);
            System.out.println("INPUT.JSON saved: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving INPUT.JSON: " + e.getMessage());
        }
    }

    public static void saveResults(List<GraphResult> results, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            Map<String, Object> output = new HashMap<>();
            output.put("analysis_date", new Date().toString());
            output.put("total_graphs", results.size());

            List<Map<String, Object>> resultsData = new ArrayList<>();
            for (GraphResult result : results) {
                Map<String, Object> resultMap = new HashMap<>();
                resultMap.put("graph_id", result.getGraphId());
                resultMap.put("category", result.getCategory());
                resultMap.put("vertices", result.getVertices());
                resultMap.put("edges", result.getEdges());

                // Prim results
                Map<String, Object> primMap = new HashMap<>();
                primMap.put("total_cost", result.getPrimResult().totalCost);
                primMap.put("execution_time_ms", result.getPrimResult().executionTimeMs);
                primMap.put("operations_count", result.getPrimResult().operationsCount);
                primMap.put("mst_edges_count", result.getPrimResult().mstEdges.size());
                resultMap.put("prim", primMap);

                // Kruskal results
                Map<String, Object> kruskalMap = new HashMap<>();
                kruskalMap.put("total_cost", result.getKruskalResult().totalCost);
                kruskalMap.put("execution_time_ms", result.getKruskalResult().executionTimeMs);
                kruskalMap.put("operations_count", result.getKruskalResult().operationsCount);
                kruskalMap.put("mst_edges_count", result.getKruskalResult().mstEdges.size());
                resultMap.put("kruskal", kruskalMap);

                // Validation
                resultMap.put("costs_match", result.isCostsMatch());
                resultMap.put("valid_mst", result.isValidMST());

                resultsData.add(resultMap);
            }

            output.put("results", resultsData);
            mapper.writeValue(file, output);
            System.out.println("OUTPUT.JSON saved: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving OUTPUT.JSON: " + e.getMessage());
        }
    }

    public static void saveCSVComparison(List<GraphResult> results, String filePath) {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            PrintWriter writer = new PrintWriter(new FileWriter(file));

            // CSV header
            writer.println("GraphID,Category,Vertices,Edges,Prim_Cost,Kruskal_Cost,Cost_Match,Prim_Time_ms,Kruskal_Time_ms,Prim_Operations,Kruskal_Operations,Valid_MST");

            // Data rows
            for (GraphResult result : results) {
                writer.printf("%d,%s,%d,%d,%d,%d,%s,%.6f,%.6f,%d,%d,%s%n",
                        result.getGraphId(),
                        result.getCategory(),
                        result.getVertices(),
                        result.getEdges(),
                        result.getPrimResult().totalCost,
                        result.getKruskalResult().totalCost,
                        result.isCostsMatch() ? "YES" : "NO",
                        result.getPrimResult().executionTimeMs,
                        result.getKruskalResult().executionTimeMs,
                        result.getPrimResult().operationsCount,
                        result.getKruskalResult().operationsCount,
                        result.isValidMST() ? "YES" : "NO"
                );
            }

            writer.close();
            System.out.println("CSV file saved: " + file.getAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error saving CSV: " + e.getMessage());
        }
    }
}