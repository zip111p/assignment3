package com.tasks.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Edge {
    @JsonProperty("from")
    public String from;

    @JsonProperty("to")
    public String to;

    @JsonProperty("weight")
    public int weight;

    public Edge() {}

    public Edge(String from, String to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("Edge{from='%s', to='%s', weight=%d}", from, to, weight);
    }
}