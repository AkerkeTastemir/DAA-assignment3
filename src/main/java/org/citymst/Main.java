package org.citymst;

import org.citymst.algo.*;
import org.citymst.io.*;
import org.citymst.model.*;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            String path = "data/ass_3_input.json";
            List<Graph> graphs = IoJson.readInput(path);

            KruskalAlgorithm kruskal = new KruskalAlgorithm();
            PrimAlgorithm prim = new PrimAlgorithm();

            Metrics mk = new Metrics();
            Metrics mp = new Metrics();

            for (Graph g : graphs) {
                System.out.println("\n--- Graph #" + g.getId() + " ---");

                kruskal.run(g, mk);
                System.out.println("Kruskal: cost=" + mk.getTotalCost() +
                        " ops=" + mk.getOperations() +
                        " time(ms)=" + mk.getTimeMs());

                prim.run(g, mp);
                System.out.println("Prim: cost=" + mp.getTotalCost() +
                        " ops=" + mp.getOperations() +
                        " time(ms)=" + mp.getTimeMs());

                if (mk.getTotalCost() == mp.getTotalCost()) {
                    System.out.println("✅ MST costs match");
                } else {
                    System.out.println("⚠️ Costs differ!");
                }
            }

        } catch (IOException e) {
            System.err.println("Error reading JSON: " + e.getMessage());
        }
    }
}