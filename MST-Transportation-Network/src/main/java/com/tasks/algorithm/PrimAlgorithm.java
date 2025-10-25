package com.tasks.algorithm;

import com.tasks.model.Graph;
import com.tasks.model.Edge;
import com.tasks.model.MSTResult;
import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(Graph graph) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();

        if (nodes.isEmpty()) {
            return new MSTResult(new ArrayList<>(), 0, 0, 0);
        }

        // Build adjacency list
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : nodes) {
            adj.put(node, new ArrayList<>());
            operationsCount++;
        }

        for (Edge edge : edges) {
            adj.get(edge.from).add(edge);
            adj.get(edge.to).add(new Edge(edge.to, edge.from, edge.weight));
            operationsCount += 2;
        }

        Set<String> inMST = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Start with first node
        String startNode = nodes.get(0);
        pq.addAll(adj.get(startNode));
        inMST.add(startNode);
        operationsCount += 3;

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;

        while (!pq.isEmpty() && inMST.size() < nodes.size()) {
            Edge edge = pq.poll();
            operationsCount++;

            if (inMST.contains(edge.to)) continue;

            inMST.add(edge.to);
            mstEdges.add(edge);
            totalCost += edge.weight;
            operationsCount += 3;

            // Add edges from the new node
            for (Edge nextEdge : adj.get(edge.to)) {
                if (!inMST.contains(nextEdge.to)) {
                    pq.offer(nextEdge);
                    operationsCount++;
                }
                operationsCount++;
            }
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}