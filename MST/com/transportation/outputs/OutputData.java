package com.transportation.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OutputData {
    @JsonProperty("results")
    public List<GraphResult> results;

    public OutputData() {}

    public OutputData(List<GraphResult> results) {
        this.results = results;
    }
}