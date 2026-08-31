import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CoursePlanningGraph {

    private final Map<String, List<String>> graph;

    public CoursePlanningGraph() {
        graph = new LinkedHashMap<String, List<String>>();
    }

    public boolean addCourse(String course) {

        if (course == null || course.trim().isEmpty()) {
            return false;
        }

        course = course.trim();

        if (graph.containsKey(course)) {
            return false;
        }

        graph.put(
                course,
                new ArrayList<String>()
        );

        return true;
    }

    public boolean addPrerequisite(
            String prerequisite,
            String nextCourse) {

        if (prerequisite == null
                || nextCourse == null) {
            return false;
        }

        prerequisite = prerequisite.trim();
        nextCourse = nextCourse.trim();

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(nextCourse)) {
            return false;
        }

        if (prerequisite.equals(nextCourse)) {
            return false;
        }

        if (graph.get(prerequisite)
                .contains(nextCourse)) {
            return false;
        }

        graph.get(prerequisite)
                .add(nextCourse);

        return true;
    }

    public boolean isReachable(
            String start,
            String target) {

        if (start == null || target == null) {
            return false;
        }

        start = start.trim();
        target = target.trim();

        if (!graph.containsKey(start)
                || !graph.containsKey(target)) {
            return false;
        }

        Set<String> visited =
                new HashSet<String>();

        return dfsReachable(
                start,
                target,
                visited
        );
    }

    private boolean dfsReachable(
            String current,
            String target,
            Set<String> visited) {

        if (current.equals(target)) {
            return true;
        }

        visited.add(current);

        for (String next : graph.get(current)) {

            if (!visited.contains(next)) {

                if (dfsReachable(
                        next,
                        target,
                        visited)) {

                    return true;
                }
            }
        }

        return false;
    }

    public List<List<String>> allPathsFrom(
            String start) {

        List<List<String>> result =
                new ArrayList<List<String>>();

        if (start == null) {
            return result;
        }

        start = start.trim();

        if (!graph.containsKey(start)) {
            return result;
        }

        List<String> path =
                new ArrayList<String>();

        Set<String> onPath =
                new HashSet<String>();

        collectPaths(
                start,
                path,
                onPath,
                result
        );

        return result;
    }

    private void collectPaths(
            String current,
            List<String> path,
            Set<String> onPath,
            List<List<String>> result) {

        path.add(current);
        onPath.add(current);

        List<String> nextCourses =
                graph.get(current);

        boolean extended = false;

        for (String next : nextCourses) {

            if (!onPath.contains(next)) {

                extended = true;

                collectPaths(
                        next,
                        path,
                        onPath,
                        result
                );
            }
        }

        if (!extended) {
            result.add(
                    new ArrayList<String>(path)
            );
        }

        path.remove(path.size() - 1);
        onPath.remove(current);
    }

    public List<String> nextCourses(
            String course) {

        List<String> result =
                new ArrayList<String>();

        if (course == null) {
            return result;
        }

        course = course.trim();

        if (!graph.containsKey(course)) {
            return result;
        }

        result.addAll(
                graph.get(course)
        );

        return result;
    }

    public static void main(String[] args) {

        CoursePlanningGraph graph =
                new CoursePlanningGraph();

        graph.addCourse("CS101");
        graph.addCourse("CS102");
        graph.addCourse("CS201");
        graph.addCourse("CS202");
        graph.addCourse("CS301");
        graph.addCourse("CS302");

        graph.addPrerequisite(
                "CS101",
                "CS201"
        );

        graph.addPrerequisite(
                "CS102",
                "CS201"
        );

        graph.addPrerequisite(
                "CS201",
                "CS301"
        );

        graph.addPrerequisite(
                "CS201",
                "CS302"
        );

        graph.addPrerequisite(
                "CS202",
                "CS302"
        );

        System.out.println(
                "CS101 -> CS301："
                + graph.isReachable(
                        "CS101",
                        "CS301"
                )
        );

        System.out.println(
                "CS102 -> CS302："
                + graph.isReachable(
                        "CS102",
                        "CS302"
                )
        );

        System.out.println(
                "CS301 -> CS101："
                + graph.isReachable(
                        "CS301",
                        "CS101"
                )
        );

        System.out.println(
                "不存在課程："
                + graph.isReachable(
                        "CS101",
                        "CS999"
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "CS201 後續課程："
                + graph.nextCourses("CS201")
        );

        System.out.println("--------------------");

        System.out.println(
                "從 CS101 出發的所有路徑："
        );

        List<List<String>> paths =
                graph.allPathsFrom("CS101");

        for (List<String> path : paths) {
            System.out.println(path);
        }

        System.out.println("--------------------");

        System.out.println(
                "不存在起點路徑："
                + graph.allPathsFrom("CS999")
        );
    }
}