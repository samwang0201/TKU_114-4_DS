import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class IterativeDfsTrace {

    private final Map<String, List<String>> graph;

    public IterativeDfsTrace() {
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

    public List<String> dfs(String start) {

        List<String> order =
                new ArrayList<String>();

        if (start == null) {
            System.out.println("start 不可為 null");
            return order;
        }

        start = start.trim();

        if (!graph.containsKey(start)) {
            System.out.println("start 不存在");
            return order;
        }

        Deque<String> stack =
                new ArrayDeque<String>();

        Set<String> visited =
                new HashSet<String>();

        stack.push(start);

        System.out.println(
                "PUSH " + start
                + " | Stack=" + stack
                + " | Visited=" + visited
        );

        while (!stack.isEmpty()) {

            String current = stack.pop();

            System.out.println(
                    "POP " + current
                    + " | Stack=" + stack
                    + " | Visited=" + visited
            );

            if (visited.contains(current)) {
                continue;
            }

            visited.add(current);
            order.add(current);

            List<String> neighbors =
                    graph.get(current);

            for (int i = neighbors.size() - 1;
                    i >= 0;
                    i--) {

                String neighbor =
                        neighbors.get(i);

                if (!visited.contains(neighbor)) {

                    stack.push(neighbor);

                    System.out.println(
                            "PUSH " + neighbor
                            + " | Stack=" + stack
                            + " | Visited=" + visited
                    );
                }
            }
        }

        return order;
    }

    public static void main(String[] args) {

        IterativeDfsTrace graph =
                new IterativeDfsTrace();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "D");
        graph.addEdge("B", "E");
        graph.addEdge("C", "F");

        System.out.println("===== 一般案例 =====");

        List<String> result =
                graph.dfs("A");

        System.out.println(
                "DFS 結果 = " + result
        );

        System.out.println("--------------------");

        System.out.println("===== 不存在的 start =====");

        System.out.println(
                "DFS 結果 = " + graph.dfs("Z")
        );

        System.out.println("--------------------");

        System.out.println("===== Empty Graph =====");

        IterativeDfsTrace empty =
                new IterativeDfsTrace();

        System.out.println(
                "DFS 結果 = " + empty.dfs("A")
        );
    }
}