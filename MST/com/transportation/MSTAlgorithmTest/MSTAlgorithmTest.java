package com.transportation;

import com.transportation.algorithm.PrimAlgorithm;
import com.transportation.algorithm.KruskalAlgorithm;
import com.transportation.model.Edge;
import com.transportation.model.MSTResult;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class MSTAlgorithmTest {

    @Test
    public void testSameMSTCost() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("A", "C", 4),
            new Edge("B", "C", 2),
            new Edge("C", "D", 3),
            new Edge("B", "D", 5)
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        MSTResult primResult = prim.findMST(nodes, edges);
        MSTResult kruskalResult = kruskal.findMST(nodes, edges);

        assertEquals(primResult.totalCost, kruskalResult.totalCost);
        assertEquals(6, primResult.totalCost);
    }

    @Test
    public void testMSTHasVMinus1Edges() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 4),
            new Edge("A", "C", 3),
            new Edge("B", "C", 2),
            new Edge("B", "D", 5),
            new Edge("C", "D", 7),
            new Edge("C", "E", 8),
            new Edge("D", "E", 6)
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(nodes, edges);

        assertEquals(nodes.size() - 1, result.mstEdges.size());
    }

    @Test
    public void testPerformanceMetrics() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("B", "C", 2),
            new Edge("A", "C", 3)
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        MSTResult result = prim.findMST(nodes, edges);

        assertTrue(result.executionTimeMs >= 0);
        assertTrue(result.operationsCount > 0);
        assertEquals(3, result.totalCost);
    }

    @Test
    public void testDisconnectedGraph() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> edges = Arrays.asList(
            new Edge("A", "B", 1),
            new Edge("C", "D", 2)
            // No connection between AB and CD components
        );

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        MSTResult primResult = prim.findMST(nodes, edges);
        MSTResult kruskalResult = kruskal.findMST(nodes, edges);

        // For disconnected graphs, MST should have V-2 edges (2 components)
        assertEquals(nodes.size() - 2, primResult.mstEdges.size());
        assertEquals(nodes.size() - 2, kruskalResult.mstEdges.size());
    }

    @Test
    public void testSingleVertex() {
        List<String> nodes = Collections.singletonList("A");
        List<Edge> edges = Collections.emptyList();

        PrimAlgorithm prim = new PrimAlgorithm();
        KruskalAlgorithm kruskal = new KruskalAlgorithm();

        MSTResult primResult = prim.findMST(nodes, edges);
        MSTResult kruskalResult = kruskal.findMST(nodes, edges);

        assertEquals(0, primResult.totalCost);
        assertEquals(0, kruskalResult.totalCost);
        assertEquals(0, primResult.mstEdges.size());
        assertEquals(0, kruskalResult.mstEdges.size());
    }
}