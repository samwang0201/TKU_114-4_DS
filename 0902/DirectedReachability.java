import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DirectedReachability {

    private final Map<String, List<String>> graph;

    public DirectedReachability() {
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

    public boolean addEdge(String from, String to) {

        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (graph.get(from).contains(to)) {
            return false;
        }

        graph.get(from).add(to);

        return true;
    }

    public boolean isReachable(
            String from,
            String to) {

        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        if (from.equals(to)) {
            return true;
        }

        Deque<String> queue =
                new ArrayDeque<String>();

        Set<String> visited =
                new HashSet<String>();

        queue.offer(from);
        visited.add(from);

        while (!queue.isEmpty()) {

            String current =
                    queue.poll();

            for (String neighbor : graph.get(current)) {

                if (neighbor.equals(to)) {
                    return true;
                }

                if (!visited.contains(neighbor)) {

                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }

        return false;
    }

    public static void main(String[] args) {

        DirectedReachability graph =
                new DirectedReachability();

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");

        graph.addEdge("A", "B");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");
        graph.addEdge("A", "E");
        graph.addEdge("E", "F");

        System.out.println(
                "A -> D："
                + graph.isReachable("A", "D")
        );

        System.out.println(
                "B -> D："
                + graph.isReachable("B", "D")
        );

        System.out.println(
                "D -> A："
                + graph.isReachable("D", "A")
        );

        System.out.println(
                "A -> F："
                + graph.isReachable("A", "F")
        );

        System.out.println(
                "F -> A："
                + graph.isReachable("F", "A")
        );

        System.out.println(
                "A -> A："
                + graph.isReachable("A", "A")
        );

        System.out.println("--------------------");

        System.out.println(
                "不存在頂點："
                + graph.isReachable("A", "Z")
        );

        System.out.println("--------------------");

        DirectedReachability empty =
                new DirectedReachability();

        System.out.println(
                "Empty Graph："
                + empty.isReachable("A", "B")
        );
    }
}