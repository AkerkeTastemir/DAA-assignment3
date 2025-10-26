package org.citymst;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = "data/ass_3_input.json";
            List<Graph> graphs = IoJson.readInput(path);
            System.out.println("Loaded graphs: " + graphs.size());
            for (Graph g : graphs) {
                System.out.println(g);
            }
        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }
    }
}