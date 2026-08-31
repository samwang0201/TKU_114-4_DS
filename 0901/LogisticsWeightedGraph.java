import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class LogisticsWeightedGraph {

    private final Map<String, Map<String, Integer>> graph;

    public LogisticsWeightedGraph() {
        graph = new HashMap<String, Map<String, Integer>>();
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
                new HashMap<String, Integer>()
        );

        return true;
    }

    public boolean addOrUpdateEdge(
            String from,
            String to,
            int cost) {

        if (from == null || to == null) {
            return false;
        }

        from = from.trim();
        to = to.trim();

        if (from.isEmpty() || to.isEmpty()) {
            return false;
        }

        if (cost < 0) {
            return false;
        }

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return false;
        }

        graph.get(from).put(to, cost);

        return true;
    }

    public boolean removeEdge(
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

        if (!graph.get(from).containsKey(to)) {
            return false;
        }

        graph.get(from).remove(to);

        return true;
    }

    public Integer getCost(
            String from,
            String to) {

        if (from == null || to == null) {
            return null;
        }

        from = from.trim();
        to = to.trim();

        if (!graph.containsKey(from)
                || !graph.containsKey(to)) {
            return null;
        }

        return graph.get(from).get(to);
    }

    public boolean hasEdge(
            String from,
            String to) {

        return getCost(from, to) != null;
    }

    public void printReport() {

        TreeMap<String, Map<String, Integer>> sortedVertices =
                new TreeMap<String, Map<String, Integer>>(
                        graph
                );

        for (Map.Entry<String, Map<String, Integer>> entry
                : sortedVertices.entrySet()) {

            String from = entry.getKey();

            TreeMap<String, Integer> edges =
                    new TreeMap<String, Integer>(
                            entry.getValue()
                    );

            System.out.println(
                    from + " -> " + edges
            );
        }
    }

    public static void main(String[] args) {

        LogisticsWeightedGraph network =
                new LogisticsWeightedGraph();

        network.addVertex("Taipei");
        network.addVertex("Taoyuan");
        network.addVertex("Hsinchu");
        network.addVertex("Taichung");

        System.out.println(
                "新增 Taipei -> Taoyuan："
                + network.addOrUpdateEdge(
                        "Taipei",
                        "Taoyuan",
                        120
                )
        );

        System.out.println(
                "新增 Taoyuan -> Hsinchu："
                + network.addOrUpdateEdge(
                        "Taoyuan",
                        "Hsinchu",
                        100
                )
        );

        System.out.println(
                "新增 Hsinchu -> Taichung："
                + network.addOrUpdateEdge(
                        "Hsinchu",
                        "Taichung",
                        180
                )
        );

        System.out.println(
                "負權重測試："
                + network.addOrUpdateEdge(
                        "Taipei",
                        "Hsinchu",
                        -50
                )
        );

        System.out.println(
                "不存在頂點測試："
                + network.addOrUpdateEdge(
                        "Taipei",
                        "Kaohsiung",
                        500
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "Taipei -> Taoyuan 成本："
                + network.getCost(
                        "Taipei",
                        "Taoyuan"
                )
        );

        System.out.println(
                "更新 Taipei -> Taoyuan："
                + network.addOrUpdateEdge(
                        "Taipei",
                        "Taoyuan",
                        90
                )
        );

        System.out.println(
                "更新後成本："
                + network.getCost(
                        "Taipei",
                        "Taoyuan"
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 Taoyuan -> Hsinchu："
                + network.removeEdge(
                        "Taoyuan",
                        "Hsinchu"
                )
        );

        System.out.println(
                "再次刪除："
                + network.removeEdge(
                        "Taoyuan",
                        "Hsinchu"
                )
        );

        System.out.println("--------------------");

        network.printReport();
    }
}