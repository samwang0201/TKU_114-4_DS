import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CampusNavigationSystem {

    public static class Location {
        private final String id;
        private final String name;

        public Location(String id, String name) {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("id 不可為空");
            }

            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("name 不可為空");
            }

            this.id = id.trim();
            this.name = name.trim();
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return id + "|" + name;
        }
    }

    private final Map<String, Location> locations;
    private final Map<String, List<String>> graph;

    public CampusNavigationSystem() {
        locations = new HashMap<String, Location>();
        graph = new LinkedHashMap<String, List<String>>();
    }

    public boolean addLocation(String id, String name) {

        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        id = id.trim();
        name = name.trim();

        if (locations.containsKey(id)) {
            return false;
        }

        Location location =
                new Location(id, name);

        locations.put(id, location);

        graph.put(
                id,
                new ArrayList<String>()
        );

        return true;
    }

    public Location findLocation(String id) {

        if (id == null) {
            return null;
        }

        return locations.get(id.trim());
    }

    public boolean addPath(String a, String b) {

        if (a == null || b == null) {
            return false;
        }

        a = a.trim();
        b = b.trim();

        if (!locations.containsKey(a)
                || !locations.containsKey(b)) {
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

        List<String> empty =
                new ArrayList<String>();

        if (start == null || target == null) {
            return empty;
        }

        start = start.trim();
        target = target.trim();

        if (!locations.containsKey(start)
                || !locations.containsKey(target)) {
            return empty;
        }

        if (start.equals(target)) {

            List<String> result =
                    new ArrayList<String>();

            result.add(start);

            return result;
        }

        Deque<String> queue =
                new ArrayDeque<String>();

        Map<String, Boolean> visited =
                new HashMap<String, Boolean>();

        Map<String, String> previous =
                new HashMap<String, String>();

        queue.offer(start);
        visited.put(start, true);

        while (!queue.isEmpty()) {

            String current = queue.poll();

            for (String neighbor : graph.get(current)) {

                if (visited.containsKey(neighbor)) {
                    continue;
                }

                visited.put(neighbor, true);

                previous.put(
                        neighbor,
                        current
                );

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

        return empty;
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

    public int distance(
            String start,
            String target) {

        List<String> path =
                shortestPath(start, target);

        if (path.isEmpty()) {
            return -1;
        }

        return path.size() - 1;
    }

    public void printRoute(
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
                "路徑：" + path
        );

        System.out.println(
                "最少邊數："
                + (path.size() - 1)
        );

        System.out.println("地點名稱：");

        for (String id : path) {

            Location location =
                    findLocation(id);

            System.out.println(
                    location
            );
        }
    }

    public static void main(String[] args) {

        CampusNavigationSystem campus =
                new CampusNavigationSystem();

        campus.addLocation(
                "A",
                "圖書館"
        );

        campus.addLocation(
                "B",
                "行政大樓"
        );

        campus.addLocation(
                "C",
                "體育館"
        );

        campus.addLocation(
                "D",
                "資訊大樓"
        );

        campus.addLocation(
                "E",
                "學生餐廳"
        );

        campus.addLocation(
                "F",
                "宿舍"
        );

        campus.addLocation(
                "G",
                "停車場"
        );

        campus.addPath("A", "B");
        campus.addPath("A", "C");
        campus.addPath("B", "D");
        campus.addPath("C", "E");
        campus.addPath("D", "F");
        campus.addPath("E", "F");

        System.out.println(
                "===== 一般案例 ====="
        );

        campus.printRoute("A", "F");

        System.out.println("--------------------");

        System.out.println(
                "===== 同一地點 ====="
        );

        campus.printRoute("A", "A");

        System.out.println("--------------------");

        System.out.println(
                "===== 不可達 ====="
        );

        campus.printRoute("A", "G");

        System.out.println("--------------------");

        System.out.println(
                "===== 不存在地點 ====="
        );

        campus.printRoute("A", "Z");
    }
}