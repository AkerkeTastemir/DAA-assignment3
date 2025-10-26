package org.citymst;

import java.util.*;

public class KruskalAlgorithm {

    public void run(Graph graph, Metrics metrics) {
        metrics.reset();
        metrics.start();

        // 1) сортируем рёбра по весу
        List<Edge> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingInt(Edge::getWeight));

        // 2) отображение имени вершины в индекс [0..|V|-1]
        List<String> nodes = graph.getNodes();
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < nodes.size(); i++) idx.put(nodes.get(i), i);

        // 3) DSU на |V|
        DSU dsu = new DSU(nodes.size());

        // 4) идём по рёбрам в порядке возрастания веса
        for (Edge e : edges) {
            int u = idx.get(e.getFrom());
            int v = idx.get(e.getTo());

            // две операции find
            dsu.find(u); metrics.inc();
            dsu.find(v); metrics.inc();

            if (dsu.union(u, v)) {  // если объединились — успешная операция
                metrics.inc();      // считаем успешный union
                metrics.addEdge(e);
                if (metrics.getMstEdges().size() == nodes.size() - 1) break;
            }
        }

        metrics.stop();
    }
}