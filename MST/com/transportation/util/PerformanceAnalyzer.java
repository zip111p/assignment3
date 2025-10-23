package com.transportation.util;

import com.transportation.model.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class PerformanceAnalyzer {

    public static void generateReport(OutputData outputData) throws IOException {
        StringBuilder csv = new StringBuilder();
        csv.append("GraphID,Vertices,Edges,PrimCost,KruskalCost,PrimTime,KruskalTime,PrimOps,KruskalOps\n");

        for (GraphResult result : outputData.results) {
            csv.append(String.format("%d,%d,%d,%d,%d,%.3f,%.3f,%d,%d\n",
                result.graphId,
                result.inputStats.vertices,
                result.inputStats.edges,
                result.prim.totalCost,
                result.kruskal.totalCost,
                result.prim.executionTimeMs,
                result.kruskal.executionTimeMs,
                result.prim.operationsCount,
                result.kruskal.operationsCount
            ));
        }

        try (FileWriter writer = new FileWriter("data/output/performance_analysis.csv")) {
            writer.write(csv.toString());
        }

        System.out.println("Performance analysis saved to: data/output/performance_analysis.csv");
    }
}