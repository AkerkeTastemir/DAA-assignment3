package org.citymst;

import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class IoJson {

    public static List<Graph> readInput(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray graphsArray = jsonObject.getJSONArray("graphs");

        List<Graph> graphs = new ArrayList<>();

        for (int i = 0; i < graphsArray.length(); i++) {
            JSONObject graphObject = graphsArray.getJSONObject(i);

            int id = graphObject.getInt("id");

            JSONArray nodesArray = graphObject.getJSONArray("nodes");
            List<String> nodes = new ArrayList<>();
            for (int j = 0; j < nodesArray.length(); j++) {
                nodes.add(nodesArray.getString(j));
            }

            JSONArray edgesArray = graphObject.getJSONArray("edges");
            List<Edge> edges = new ArrayList<>();
            for (int j = 0; j < edgesArray.length(); j++) {
                JSONObject edgeObject = edgesArray.getJSONObject(j);
                String from = edgeObject.getString("from");
                String to = edgeObject.getString("to");
                int weight = edgeObject.getInt("weight");
                edges.add(new Edge(from, to, weight));
            }

            graphs.add(new Graph(id, nodes, edges));
        }

        return graphs;
    }
}