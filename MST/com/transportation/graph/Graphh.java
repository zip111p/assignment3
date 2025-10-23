package com.transportation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Graphh {
    @JsonProperty("id")
    public int id;
    
    @JsonProperty("nodes")
    public List<String> nodes;
    
    @JsonProperty("edges")
    public List<Edge> edges;

    public Graphh() {}

    public Graphh(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }
}