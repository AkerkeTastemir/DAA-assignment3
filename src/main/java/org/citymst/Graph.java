package org.citymst;

import java.util.List;

public class Graph {
    private final int id;
    private final List<String> nodes;
    private final List<Edge> edges;

    public Graph(int id, List<String> nodes, List<Edge> edges) {
        this.id = id;
        this.nodes = nodes;
        this.edges = edges;
    }

    public int getId() {
        return id;
    }

    public List<String> getNodes() {
        return nodes;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "id=" + id +
                ", vertices=" + nodes.size() +
                ", edges=" + edges.size() +
                '}';
    }
}