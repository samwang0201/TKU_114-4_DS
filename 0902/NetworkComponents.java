import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class NetworkComponents {

    private final Map<String, List<String>> graph;

    public NetworkComponents() {
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

    public List<List<String>> components() {

        List<List<String>> result =
                new ArrayList<List<String>>();

        Set<String> visited =
                new HashSet<String>();

        for (String start : graph.keySet()) {

            if (visited.contains(start)) {
                continue;
            }

            List<String> component =
                    new ArrayList<String>();

            Deque<String> queue =
                    new ArrayDeque<String>();

            queue.offer(start);
            visited.add(start);

            while (!queue.isEmpty()) {

                String current = queue.poll();

                component.add(current);

                for (String neighbor : graph.get(current)) {

                    if (!visited.contains(neighbor)) {

                        visited.add(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }

            component.sort(null);
            result.add(component);
        }

        return result;
    }

    public int componentCount() {
        return components().size();
    }

    public List<String> largestComponent() {

        List<List<String>> all =
                components();

        List<String> largest =
                new ArrayList<String>();

        for (List<String> component : all) {

            if (component.size() > largest.size()) {
                largest = new ArrayList<String>(component);
            }
        }

        return largest;
    }

    public void printReport() {

        List<List<String>> all =
                components();

        if (all.isEmpty()) {
            System.out.println("Empty graph");
            System.out.println("分量個數：0");
            System.out.println("最大分量：[]");
            return;
        }

        System.out.println("Connected Components：");

        for (int i = 0; i < all.size(); i++) {

            System.out.println(
                    "Component "
                    + (i + 1)
                    + "："
                    + all.get(i)
            );
        }

        System.out.println(
                "分量個數："
                + all.size()
        );

        System.out.println(
                "最大分量："
                + largestComponent()
        );
    }

    public static void main(String[] args) {

        NetworkComponents network =
                new NetworkComponents();

        network.addVertex("A");
        network.addVertex("B");
        network.addVertex("C");
        network.addVertex("D");
        network.addVertex("E");
        network.addVertex("F");
        network.addVertex("G");

        network.addEdge("A", "B");
        network.addEdge("B", "C");

        network.addEdge("D", "E");

        network.addEdge("F", "G");

        System.out.println("===== 一般案例 =====");

        network.printReport();

        System.out.println("--------------------");

        System.out.println(
                "component count = "
                + network.componentCount()
        );

        System.out.println(
                "largest component = "
                + network.largestComponent()
        );

        System.out.println("--------------------");

        System.out.println("===== Empty Graph =====");

        NetworkComponents empty =
                new NetworkComponents();

        empty.printReport();
    }
}