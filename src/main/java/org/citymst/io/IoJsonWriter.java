package org.citymst.io;

import org.citymst.model.Edge;
import org.citymst.model.Graph;
import org.citymst.algo.Metrics;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class IoJsonWriter {

    public static void saveResults(String filePath, List<Graph> graphs,
                                   List<Metrics> primMetrics, List<Metrics> kruskalMetrics) throws IOException {
        JSONObject root = new JSONObject();
        JSONArray results = new JSONArray();

        for (int i = 0; i < graphs.size(); i++) {
            Graph g = graphs.get(i);
            Metrics prim = primMetrics.get(i);
            Metrics kruskal = kruskalMetrics.get(i);

            JSONObject graphObj = new JSONObject();
            graphObj.put("graph_id", g.getId());

            JSONObject inputStats = new JSONObject();
            inputStats.put("vertices", g.getNodes().size());
            inputStats.put("edges", g.getEdges().size());
            graphObj.put("input_stats", inputStats);

            JSONObject primObj = new JSONObject();
            primObj.put("mst_edges", edgesToJson(prim.getMstEdges()));
            primObj.put("total_cost", prim.getTotalCost());
            primObj.put("operations_count", prim.getOperations());
            primObj.put("execution_time_ms", prim.getTimeMs());

            JSONObject kruskalObj = new JSONObject();
            kruskalObj.put("mst_edges", edgesToJson(kruskal.getMstEdges()));
            kruskalObj.put("total_cost", kruskal.getTotalCost());
            kruskalObj.put("operations_count", kruskal.getOperations());
            kruskalObj.put("execution_time_ms", kruskal.getTimeMs());

            graphObj.put("prim", primObj);
            graphObj.put("kruskal", kruskalObj);
            results.put(graphObj);
        }

        root.put("results", results);

        try (FileWriter file = new FileWriter(filePath)) {
            file.write(root.toString(4)); // форматированный JSON
        }
    }

    private static JSONArray edgesToJson(List<Edge> edges) {
        JSONArray arr = new JSONArray();
        for (Edge e : edges) {
            JSONObject o = new JSONObject();
            o.put("from", e.getFrom());
            o.put("to", e.getTo());
            o.put("weight", e.getWeight());
            arr.put(o);
        }
        return arr;
    }
}