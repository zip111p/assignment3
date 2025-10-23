package com.transportation.graph;

import com.transportation.graph.Edge;
import com.transportation.graph.Vertex;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Custom graph data structure implementing object-oriented principles
 * Supports both adjacency list and matrix representations
 */
public class Graph {
    private final Map<String, Vertex> vertices;
    private final List<Edge> edges;
    private final boolean directed;
    private final String name;
    
    public Graph(String name) {
        this(name, false);
    }
    
    public Graph(String name, boolean directed) {
        this.name = name;
        this.directed = directed;
        this.vertices = new HashMap<>();
        this.edges = new ArrayList<>();
    }
    
    // Vertex operations
    public Vertex addVertex(String id) {
        return addVertex(id, id); // Use ID as default label
    }
    
    public Vertex addVertex(String id, String label) {
        Vertex vertex = new Vertex(id, label);
        vertices.put(id, vertex);
        return vertex;
    }
    
    public Vertex getVertex(String id) {
        return vertices.get(id);
    }
    
    public boolean containsVertex(String id) {
        return vertices.containsKey(id);
    }
    
    // Edge operations
    public Edge addEdge(String fromId, String toId, int weight) {
        if (!vertices.containsKey(fromId) || !vertices.containsKey(toId)) {
            throw new IllegalArgumentException("Both vertices must exist in graph");
        }
        
        Vertex from = vertices.get(fromId);
        Vertex to = vertices.get(toId);
        
        Edge edge = new Edge(from, to, weight);
        edges.add(edge);
        
        // Add to adjacency
        from.addAdjacentEdge(edge);
        if (!directed) {
            to.addAdjacentEdge(new Edge(to, from, weight)); // Add reverse edge for undirected
        }
        
        return edge;
    }
    
    // Graph properties
    public int getVertexCount() {
        return vertices.size();
    }
    
    public int getEdgeCount() {
        return edges.size();
    }
    
    public boolean isConnected() {
        if (vertices.isEmpty()) return true;
        
        Set<Vertex> visited = new HashSet<>();
        dfs(vertices.values().iterator().next(), visited);
        
        return visited.size() == vertices.size();
    }
    
    private void dfs(Vertex current, Set<Vertex> visited) {
        visited.add(current);
        for (Edge edge : current.getAdjacentEdges()) {
            Vertex neighbor = edge.getTo();
            if (!visited.contains(neighbor)) {
                dfs(neighbor, visited);
            }
        }
    }
    
    // Accessors
    public Collection<Vertex> getVertices() {
        return Collections.unmodifiableCollection(vertices.values());
    }
    
    public List<Edge> getEdges() {
        return Collections.unmodifiableList(edges);
    }
    
    public String getName() {
        return name;
    }
    
    public boolean isDirected() {
        return directed;
    }
    
    // Conversion methods for algorithm compatibility
    public List<String> getNodeIds() {
        return vertices.values().stream()
                .map(Vertex::getId)
                .collect(Collectors.toList());
    }
    
    public List<com.transportation.model.Edge> toSimpleEdges() {
        return edges.stream()
                .map(e -> new com.transportation.model.Edge(
                    e.getFrom().getId(), 
                    e.getTo().getId(), 
                    e.getWeight()))
                .collect(Collectors.toList());
    }
    
    // Graph analysis
    public int getDegree(String vertexId) {
        Vertex vertex = vertices.get(vertexId);
        return vertex != null ? vertex.getDegree() : 0;
    }
    
    public boolean hasEdge(String fromId, String toId) {
        Vertex from = vertices.get(fromId);
        if (from == null) return false;
        
        return from.getAdjacentEdges().stream()
                .anyMatch(edge -> edge.getTo().getId().equals(toId));
    }
    
    @Override
    public String toString() {
        return String.format("Graph{name='%s', vertices=%d, edges=%d, connected=%s}", 
                name, getVertexCount(), getEdgeCount(), isConnected());
    }
    
    // Factory method from JSON data
    public static Graph fromJsonData(com.transportation.model.Graph jsonGraph) {
        Graph graph = new Graph("Graph-" + jsonGraph.id);
        
        // Add vertices
        for (String nodeId : jsonGraph.nodes) {
            graph.addVertex(nodeId);
        }
        
        // Add edges
        for (com.transportation.model.Edge edge : jsonGraph.edges) {
            graph.addEdge(edge.from, edge.to, edge.weight);
        }
        
        return graph;
    }
}