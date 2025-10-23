package com.transportation.algorithm;

import com.transportation.model.Edge;
import com.transportation.model.MSTResult;
import java.util.*;

public class PrimAlgorithm {

    public MSTResult findMST(List<String> nodes, List<Edge> edges) {
        long startTime = System.nanoTime();
        int operationsCount = 0;

        if (nodes.isEmpty()) {
            return new MSTResult(new ArrayList<>(), 0, 0, 0);
        }

        // Create node to index mapping
        Map<String, Integer> nodeIndex = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) {
            nodeIndex.put(nodes.get(i), i);
        }
        operationsCount += nodes.size();

        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < nodes.size(); i++) {
            adj.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            int u = nodeIndex.get(edge.from);
            int v = nodeIndex.get(edge.to);
            adj.get(u).add(new int[]{v, edge.weight});
            adj.get(v).add(new int[]{u, edge.weight});
            operationsCount += 2;
        }
        operationsCount += edges.size();

        boolean[] inMST = new boolean[nodes.size()];
        int[] minEdge = new int[nodes.size()];
        int[] parent = new int[nodes.size()];
        
        Arrays.fill(minEdge, Integer.MAX_VALUE);
        Arrays.fill(parent, -1);
        minEdge[0] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{0, 0});
        operationsCount += 6;

        List<Edge> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int edgesAdded = 0;

        while (!pq.isEmpty() && edgesAdded < nodes.size() - 1) {
            int[] current = pq.poll();
            int u = current[0];
            operationsCount += 2;

            if (inMST[u]) {
                operationsCount++;
                continue;
            }

            inMST[u] = true;
            operationsCount++;

            if (parent[u] != -1) {
                String fromNode = nodes.get(parent[u]);
                String toNode = nodes.get(u);
                int weight = minEdge[u];
                mstEdges.add(new Edge(fromNode, toNode, weight));
                totalCost += weight;
                edgesAdded++;
                operationsCount += 4;
            }

            for (int[] neighbor : adj.get(u)) {
                int v = neighbor[0];
                int weight = neighbor[1];
                operationsCount += 2;

                if (!inMST[v] && weight < minEdge[v]) {
                    minEdge[v] = weight;
                    parent[v] = u;
                    pq.offer(new int[]{v, weight});
                    operationsCount += 4;
                }
                operationsCount += 2;
            }
            operationsCount += adj.get(u).size();
        }

        long endTime = System.nanoTime();
        double executionTimeMs = (endTime - startTime) / 1_000_000.0;

        return new MSTResult(mstEdges, totalCost, operationsCount, executionTimeMs);
    }
}