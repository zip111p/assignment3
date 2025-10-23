package com.transportation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputStats {
    @JsonProperty("vertices")
    public int vertices;
    
    @JsonProperty("edges")
    public int edges;

    public InputStats() {}

    public InputStats(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
    }
}