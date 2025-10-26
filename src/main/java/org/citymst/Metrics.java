package org.citymst;

import java.util.ArrayList;
import java.util.List;

public class Metrics {
    private final List<Edge> mstEdges = new ArrayList<>();
    private int totalCost = 0;
    private long operations = 0;      // счётчик ключевых действий
    private long startNs;
    private double timeMs;

    public void start() { startNs = System.nanoTime(); }
    public void stop()  { timeMs = (System.nanoTime() - startNs) / 1_000_000.0; }

    public void addEdge(Edge e) {
        mstEdges.add(e);
        totalCost += e.getWeight();
    }

    public void inc() { operations++; }

    public List<Edge> getMstEdges() { return mstEdges; }
    public int getTotalCost() { return totalCost; }
    public long getOperations() { return operations; }
    public double getTimeMs() { return timeMs; }

    public void reset() {
        mstEdges.clear();
        totalCost = 0;
        operations = 0;
        startNs = 0;
        timeMs = 0.0;
    }
}