import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MetroTransferPath {

    private final Map<String, List<String>> graph;

    public MetroTransferPath() {
        graph = new LinkedHashMap<String, List<String>>();
    }

    public boolean addStation(String station) {

        if (station == null || station.trim().isEmpty()) {
            return false;
        }

        station = station.trim();

        if (graph.containsKey(station)) {
            return false;
        }

        graph.put(
                station,
                new ArrayList<String>()
        );

        return true;
    }

    public boolean addConnection(String a, String b) {

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

    public List<String> shortestPath(
            String start,
            String target) {

        List<String> emptyResult =
                new ArrayList<String>();

        if (start == null || target == null) {
            return emptyResult;
        }

        start = start.trim();
        target = target.trim();

        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return emptyResult;
        }

        if (start.equals(target)) {
            List<String> result =
                    new ArrayList<String>();

            result.add(start);

            return result;
        }

        Deque<String> queue =
                new ArrayDeque<String>();

        Map<String, String> previous =
                new HashMap<String, String>();

        Map<String, Boolean> visited =
                new HashMap<String, Boolean>();

        queue.offer(start);
        visited.put(start, true);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            for (String neighbor : graph.get(current)) {

                if (visited.containsKey(neighbor)) {
                    continue;
                }

                visited.put(neighbor, true);
                previous.put(neighbor, current);

                if (neighbor.equals(target)) {
                    return buildPath(
                            previous,
                            start,
                            target
                    );
                }

                queue.offer(neighbor);
            }
        }

        return emptyResult;
    }

    private List<String> buildPath(
            Map<String, String> previous,
            String start,
            String target) {

        Deque<String> stack =
                new ArrayDeque<String>();

        String current = target;

        while (current != null) {

            stack.push(current);

            if (current.equals(start)) {
                break;
            }

            current = previous.get(current);
        }

        List<String> result =
                new ArrayList<String>();

        while (!stack.isEmpty()) {
            result.add(stack.pop());
        }

        return result;
    }

    public int edgeCount(
            String start,
            String target) {

        List<String> path =
                shortestPath(start, target);

        if (path.isEmpty()) {
            return -1;
        }

        return path.size() - 1;
    }

    public void printPath(
            String start,
            String target) {

        List<String> path =
                shortestPath(start, target);

        if (path.isEmpty()) {

            System.out.println(
                    start
                    + " -> "
                    + target
                    + "：找不到路徑"
            );

            return;
        }

        System.out.println(
                start
                + " -> "
                + target
                + " 路徑："
                + path
        );

        System.out.println(
                "邊數："
                + (path.size() - 1)
        );
    }

    public static void main(String[] args) {

        MetroTransferPath metro =
                new MetroTransferPath();

        metro.addStation("A");
        metro.addStation("B");
        metro.addStation("C");
        metro.addStation("D");
        metro.addStation("E");
        metro.addStation("F");
        metro.addStation("G");

        metro.addConnection("A", "B");
        metro.addConnection("A", "C");

        metro.addConnection("B", "D");

        metro.addConnection("C", "E");

        metro.addConnection("D", "F");
        metro.addConnection("E", "F");

        System.out.println("===== 一般案例 =====");

        metro.printPath("A", "F");

        System.out.println("--------------------");

        System.out.println("===== 同一站 =====");

        metro.printPath("A", "A");

        System.out.println("--------------------");

        System.out.println("===== 不可達 =====");

        metro.printPath("A", "G");

        System.out.println("--------------------");

        System.out.println("===== 不存在站點 =====");

        metro.printPath("A", "Z");

        System.out.println("--------------------");

        System.out.println(
                "A 到 F 最少邊數："
                + metro.edgeCount("A", "F")
        );
    }
}