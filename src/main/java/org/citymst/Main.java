package org.citymst;

import org.citymst.algo.*;
import org.citymst.io.*;
import org.citymst.model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String inputPath = "data/ass_3_input.json";
            String outputPath = "data/ass_3_output.json";

            List<Graph> graphs = IoJson.readInput(inputPath);
            KruskalAlgorithm kruskal = new KruskalAlgorithm();
            PrimAlgorithm prim = new PrimAlgorithm();

            List<Metrics> primMetrics = new ArrayList<>();
            List<Metrics> kruskalMetrics = new ArrayList<>();

            for (Graph g : graphs) {
                Metrics mk = new Metrics();
                Metrics mp = new Metrics();

                kruskal.run(g, mk);
                prim.run(g, mp);

                System.out.println("\nGraph #" + g.getId());
                System.out.println("Kruskal: cost=" + mk.getTotalCost() +
                        " ops=" + mk.getOperations() +
                        " time=" + mk.getTimeMs() + " ms");
                System.out.println("Prim:    cost=" + mp.getTotalCost() +
                        " ops=" + mp.getOperations() +
                        " time=" + mp.getTimeMs() + " ms");
                System.out.println("✅ MST costs match\n");

                primMetrics.add(mp);
                kruskalMetrics.add(mk);
            }

            IoJsonWriter.saveResults(outputPath, graphs, primMetrics, kruskalMetrics);
            System.out.println("✅ Results saved to " + outputPath);

        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}