import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CourseDependencyGraph {

    private final Map<String, Set<String>> graph;

    public CourseDependencyGraph() {
        graph = new HashMap<String, Set<String>>();
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
                new HashSet<String>()
        );

        return true;
    }

    public boolean addDependency(
            String prerequisite,
            String nextCourse) {

        if (prerequisite == null
                || nextCourse == null) {
            return false;
        }

        prerequisite = prerequisite.trim();
        nextCourse = nextCourse.trim();

        if (prerequisite.isEmpty()
                || nextCourse.isEmpty()
                || prerequisite.equals(nextCourse)) {
            return false;
        }

        if (!graph.containsKey(prerequisite)
                || !graph.containsKey(nextCourse)) {
            return false;
        }

        if (graph.get(prerequisite).contains(nextCourse)) {
            return false;
        }

        graph.get(prerequisite).add(nextCourse);

        return true;
    }

    public Set<String> prerequisitesOf(String course) {

        Set<String> result =
                new TreeSet<String>();

        if (course == null) {
            return result;
        }

        course = course.trim();

        if (!graph.containsKey(course)) {
            return result;
        }

        for (Map.Entry<String, Set<String>> entry
                : graph.entrySet()) {

            if (entry.getValue().contains(course)) {
                result.add(entry.getKey());
            }
        }

        return result;
    }

    public Set<String> nextCoursesOf(String course) {

        Set<String> result =
                new TreeSet<String>();

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

    public int inDegree(String course) {

        return prerequisitesOf(course).size();
    }

    public int outDegree(String course) {

        if (course == null) {
            return 0;
        }

        course = course.trim();

        if (!graph.containsKey(course)) {
            return 0;
        }

        return graph.get(course).size();
    }

    public void printReport() {

        TreeSet<String> courses =
                new TreeSet<String>(
                        graph.keySet()
                );

        for (String course : courses) {

            System.out.println(
                    "課程：" + course
            );

            System.out.println(
                    "先決條件："
                    + prerequisitesOf(course)
            );

            System.out.println(
                    "後續課程："
                    + nextCoursesOf(course)
            );

            System.out.println(
                    "進度數：" + inDegree(course)
            );

            System.out.println(
                    "出度數：" + outDegree(course)
            );

            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {

        CourseDependencyGraph graph =
                new CourseDependencyGraph();

        graph.addCourse("CS101");
        graph.addCourse("CS102");
        graph.addCourse("CS201");
        graph.addCourse("CS202");
        graph.addCourse("CS301");

        graph.addDependency(
                "CS101",
                "CS201"
        );

        graph.addDependency(
                "CS102",
                "CS201"
        );

        graph.addDependency(
                "CS201",
                "CS301"
        );

        graph.addDependency(
                "CS202",
                "CS301"
        );

        System.out.println(
                "CS201 先決條件："
                + graph.prerequisitesOf("CS201")
        );

        System.out.println(
                "CS201 後續課程："
                + graph.nextCoursesOf("CS201")
        );

        System.out.println(
                "CS201 進度數："
                + graph.inDegree("CS201")
        );

        System.out.println(
                "CS201 出度數："
                + graph.outDegree("CS201")
        );

        System.out.println("====================");

        graph.printReport();
    }
}