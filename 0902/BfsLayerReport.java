import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BfsLayerReport {

    private final Map<String, List<String>> graph;

    public BfsLayerReport() {
        graph = new LinkedHashMap<String, List<String>>();
    }

    public boolean addVertex(String vertex) {

        if (vertex == null || vertex.trim().isEmpty()) {
            return false;
        }

        vertex = vertex.trim();

        if (graph.containsKey(vertex)) {
            return false;
        }

        graph.put(
                vertex,
                new ArrayList<String>()
        );

        return true;
    }

    public boolean addEdge(String a, String b) {

        if (a == null || b == null) {
            return false;
        }

        a = a.trim();
        b = b.trim();

        if (!graph.containsKey(a)
                || !graph.containsKey(b)) {
            return false;
        }

        if (a.equals(b)) {
            return false;
        }

        if (graph.get(a).contains(b)) {
            return false;
        }

        graph.get(a).add(b);
        graph.get(b).add(a);

        return true;
    }

    public Map<String, Integer> bfsLayers(String start) {

        Map<String, Integer> distance =
                new LinkedHashMap<String, Integer>();

        if (start == null) {
            return distance;
        }

        start = start.trim();

        if (!graph.containsKey(start)) {
            return distance;
        }

        Deque<String> queue =
                new ArrayDeque<String>();

        distance.put(start, 0);
        queue.offer(start);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            int currentDistance =
                    distance.get(current);

            for (String neighbor : graph.get(current)) {

                if (!distance.containsKey(neighbor)) {

                    distance.put(
                            neighbor,
                            currentDistance + 1
                    );

                    queue.offer(neighbor);
                }
            }
        }

        return distance;
    }

    public void printLayerReport(String start) {

        Map<String, Integer> result =
                bfsLayers(start);

        if (result.isEmpty()) {

            System.out.println(
                    "start 不存在或圖為空"
            );

            return;
        }

        for (Map.Entry<String, Integer> entry
                : result.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " 距離 "
                    + start
                    + " = "
                    + entry.getValue()
            );
        }
    }

    public static void main(String[] args) {

        BfsLayerReport graph =
                new BfsLayerReport();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("C", "E");
        graph.addEdge("D", "F");
        graph.addEdge("E", "F");

        System.out.println("從 A 開始：");

        graph.printLayerReport("A");

        System.out.println("--------------------");

        System.out.println("不存在的 start：");

        graph.printLayerReport("Z");

        System.out.println("--------------------");

        System.out.println("Empty Graph：");

        BfsLayerReport emptyGraph =
                new BfsLayerReport();

        emptyGraph.printLayerReport("A");
    }
}