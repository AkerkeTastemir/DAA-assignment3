package org.citymst.algo;

import org.citymst.model.Edge;
import org.citymst.model.Graph;
import java.util.*;

public class PrimAlgorithm {

    public void run(Graph graph, Metrics metrics) {
        metrics.reset();
        metrics.start();

        List<String> nodes = graph.getNodes();
        List<Edge> edges = graph.getEdges();

        // строим список смежности
        Map<String, List<Edge>> adj = new HashMap<>();
        for (String node : nodes) adj.put(node, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.getFrom()).add(e);
            adj.get(e.getTo()).add(e);
        }

        Set<String> visited = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        // стартуем с минимальной вершины по имени
        String start = Collections.min(nodes);
        visited.add(start);
        pq.addAll(adj.get(start));

        while (!pq.isEmpty() && visited.size() < nodes.size()) {
            metrics.inc(); // операция: extract-min
            Edge minEdge = pq.poll();
            String u = minEdge.getFrom();
            String v = minEdge.getTo();

            String next = !visited.contains(u) ? u : v;
            if (visited.contains(next)) continue;

            metrics.addEdge(minEdge);
            visited.add(next);

            for (Edge e : adj.get(next)) {
                String to = e.getFrom().equals(next) ? e.getTo() : e.getFrom();
                if (!visited.contains(to)) {
                    pq.add(e);
                    metrics.inc(); // добавление в очередь
                }
            }
        }

        metrics.stop();
    }
}