import com.google.gson.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class MSTAssignment {

    static class Edge {
        int from, to, weight;
        String fromName, toName;
        Edge(int from, int to, int weight, String fromName, String toName) {
            this.from = from; this.to = to; this.weight = weight;
            this.fromName = fromName; this.toName = toName;
        }
    }

    static class UnionFind {
        int[] parent, rank;
        int operations = 0;

        UnionFind(int n) {
            parent = new int[n]; rank = new int[n];
            for (int i=0; i<n; i++) parent[i] = i;
        }

        int find(int u) {
            operations++;
            if (parent[u] != u)
                parent[u] = find(parent[u]);
            return parent[u];
        }

        boolean union(int u, int v) {
            operations++;
            int ru = find(u), rv = find(v);
            if (ru == rv) return false;
            if (rank[ru] < rank[rv]) parent[ru] = rv;
            else if (rank[ru] > rank[rv]) parent[rv] = ru;
            else { parent[rv] = ru; rank[ru]++; }
            return true;
        }
    }

    static class MSTResult {
        List<JsonObject> mstEdges = new ArrayList<>();
        int totalCost = 0;
        int operationsCount = 0;
        double executionTimeMs = 0;
    }

    // Алгоритм Краскала
    public static MSTResult kruskal(int vertices, List<Edge> edges) {
        MSTResult res = new MSTResult();
        UnionFind uf = new UnionFind(vertices);
        edges.sort(Comparator.comparingInt(e -> e.weight));
        List<Edge> mst = new ArrayList<>();

        long startTime = System.nanoTime();
        for (Edge e : edges) {
            if (uf.union(e.from, e.to)) {
                mst.add(e);
                res.totalCost += e.weight;
            }
        }
        long endTime = System.nanoTime();

        for (Edge e : mst) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", e.fromName);
            edgeObj.addProperty("to", e.toName);
            edgeObj.addProperty("weight", e.weight);
            res.mstEdges.add(edgeObj);
        }

        res.operationsCount = uf.operations;
        res.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        return res;
    }

    // Алгоритм Прима
    public static MSTResult prim(int vertices, List<Edge> edges) {
        MSTResult res = new MSTResult();
        Map<Integer, List<Edge>> adj = new HashMap<>();
        for (int i=0; i<vertices; i++) adj.put(i, new ArrayList<>());
        for (Edge e : edges) {
            adj.get(e.from).add(e);
            adj.get(e.to).add(new Edge(e.to, e.from, e.weight, e.toName, e.fromName));
        }

        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));
        int start = 0;
        pq.add(new Edge(-1, start, 0, "", ""));
        List<Edge> mst = new ArrayList<>();
        int ops = 0;
        int totalCost = 0;

        long startTime = System.nanoTime();
        while (!pq.isEmpty() && mst.size() < vertices - 1) {
            Edge e = pq.poll();
            ops++;
            if (visited[e.to]) continue;
            visited[e.to] = true;
            if (e.from != -1) {
                mst.add(e);
                totalCost += e.weight;
            }
            for (Edge ne : adj.get(e.to)) {
                if (!visited[ne.to]) {
                    pq.add(ne);
                }
            }
        }
        long endTime = System.nanoTime();

        for (Edge e : mst) {
            JsonObject edgeObj = new JsonObject();
            edgeObj.addProperty("from", e.fromName);
            edgeObj.addProperty("to", e.toName);
            edgeObj.addProperty("weight", e.weight);
            res.mstEdges.add(edgeObj);
        }
        res.totalCost = totalCost;
        res.operationsCount = ops;
        res.executionTimeMs = (endTime - startTime) / 1_000_000.0;
        return res;
    }

    public static void main(String[] args) throws IOException {
        String inputText = Files.readString(Path.of("assign_3_input.json"));
        JsonObject input = JsonParser.parseString(inputText).getAsJsonObject();
        JsonArray graphs = input.getAsJsonArray("graphs");

        JsonArray results = new JsonArray();

        for (JsonElement g : graphs) {
            JsonObject graph = g.getAsJsonObject();
            int vertices = graph.getAsJsonArray("nodes").size();
            JsonArray nodes = graph.getAsJsonArray("nodes");

            // Преобразуем имена вершин в индексы
            Map<String, Integer> nodeIdx = new HashMap<>();
            for (int i=0; i<vertices; i++) {
                nodeIdx.put(nodes.get(i).getAsString(), i);
            }

            // Список ребер в удобном формате
            List<Edge> edges = new ArrayList<>();
            for (JsonElement e : graph.getAsJsonArray("edges")) {
                JsonObject edge = e.getAsJsonObject();
                String fromName = edge.get("from").getAsString();
                String toName = edge.get("to").getAsString();
                int from = nodeIdx.get(fromName);
                int to = nodeIdx.get(toName);
                int weight = edge.get("weight").getAsInt();
                edges.add(new Edge(from, to, weight, fromName, toName));
            }

            // Запускаем алгоритмы
            MSTResult primResult = prim(vertices, edges);
            MSTResult kruskalResult = kruskal(vertices, edges);

            // Проверка, что стоимость совпадает
            if (primResult.totalCost != kruskalResult.totalCost) {
                System.err.println("Mismatch MST cost for graph id " + graph.get("id").getAsInt());
            }

            // Формируем результат
            JsonObject resObj = new JsonObject();
            resObj.addProperty("graph_id", graph.get("id").getAsInt());

            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", vertices);
            inputStats.addProperty("edges", edges.size());
            resObj.add("input_stats", inputStats);

            // Prim
            JsonObject primJson = new JsonObject();
            primJson.add("mst_edges", toJsonArray(primResult.mstEdges));
            primJson.addProperty("total_cost", primResult.totalCost);
            primJson.addProperty("operations_count", primResult.operationsCount);
            primJson.addProperty("execution_time_ms", primResult.executionTimeMs);
            resObj.add("prim", primJson);

            // Kruskal
            JsonObject kruskalJson = new JsonObject();
            kruskalJson.add("mst_edges", toJsonArray(kruskalResult.mstEdges));
            kruskalJson.addProperty("total_cost", kruskalResult.totalCost);
            kruskalJson.addProperty("operations_count", kruskalResult.operationsCount);
            kruskalJson.addProperty("execution_time_ms", kruskalResult.executionTimeMs);
            resObj.add("kruskal", kruskalJson);

            results.add(resObj);
        }

        JsonObject output = new JsonObject();
        output.add("results", results);

        try (FileWriter writer = new FileWriter("output.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(output, writer);
        }

        System.out.println("MST computation completed. Results saved to output.json");
    }

    private static JsonArray toJsonArray(List<JsonObject> list) {
        JsonArray arr = new JsonArray();
        for (JsonObject obj : list) arr.add(obj);
        return arr;
    }
}
