package com.transportation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class InputData {
    @JsonProperty("graphs")
    public List<Graph> graphs;

    public InputData() {}

    public InputData(List<Graph> graphs) {
        this.graphs = graphs;
    }
}