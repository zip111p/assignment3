package com.transportation.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GraphResult {
    @JsonProperty("graph_id")
    public int graphId;
    
    @JsonProperty("input_stats")
    public InputStats inputStats;
    
    @JsonProperty("prim")
    public MSTResult prim;
    
    @JsonProperty("kruskal")
    public MSTResult kruskal;

    public GraphResult() {}

    public GraphResult(int graphId, InputStats inputStats, MSTResult prim, MSTResult kruskal) {
        this.graphId = graphId;
        this.inputStats = inputStats;
        this.prim = prim;
        this.kruskal = kruskal;
    }
}