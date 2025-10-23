package com.transportation.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.transportation.model.InputData;
import com.transportation.model.OutputData;
import java.io.File;
import java.io.IOException;

public class JsonFileProcessor {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static InputData readInput(String filePath) throws IOException {
        return mapper.readValue(new File(filePath), InputData.class);
    }

    public static void writeOutput(String filePath, OutputData outputData) throws IOException {
        mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), outputData);
    }
}