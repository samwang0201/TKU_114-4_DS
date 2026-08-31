import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CourseGradeMap {

    private final Map<String, List<Integer>> gradeMap;

    public CourseGradeMap() {
        gradeMap = new HashMap<String, List<Integer>>();
    }

    public boolean addGrade(String courseCode, int score) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();

        if (score < 0) {
            score = 0;
        }

        if (score > 100) {
            score = 100;
        }

        List<Integer> grades =
                gradeMap.get(courseCode);

        if (grades == null) {
            grades = new ArrayList<Integer>();
            gradeMap.put(courseCode, grades);
        }

        grades.add(score);

        return true;
    }

    public double average(String courseCode) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return 0.0;
        }

        List<Integer> grades =
                gradeMap.get(courseCode.trim());

        if (grades == null || grades.isEmpty()) {
            return 0.0;
        }

        int total = 0;

        for (int i = 0; i < grades.size(); i++) {
            total = total + grades.get(i);
        }

        return (double) total / grades.size();
    }

    public Integer highest(String courseCode) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return null;
        }

        List<Integer> grades =
                gradeMap.get(courseCode.trim());

        if (grades == null || grades.isEmpty()) {
            return null;
        }

        int max = grades.get(0);

        for (int i = 1; i < grades.size(); i++) {

            if (grades.get(i) > max) {
                max = grades.get(i);
            }
        }

        return max;
    }

    public void printSortedReport() {

        Map<String, List<Integer>> sorted =
                new TreeMap<String, List<Integer>>(
                        gradeMap
                );

        for (Map.Entry<String, List<Integer>> entry
                : sorted.entrySet()) {

            String courseCode = entry.getKey();

            System.out.println(
                    courseCode
                    + " 成績="
                    + entry.getValue()
                    + ", 平均="
                    + average(courseCode)
                    + ", 最高="
                    + highest(courseCode)
            );
        }
    }

    public static void main(String[] args) {

        CourseGradeMap manager =
                new CourseGradeMap();

        manager.addGrade("DS", 80);
        manager.addGrade("DS", 90);
        manager.addGrade("DS", 70);

        manager.addGrade("JAVA", 95);
        manager.addGrade("JAVA", 85);

        manager.addGrade("DB", 88);
        manager.addGrade("DB", 92);

        System.out.println(
                "DS 平均="
                + manager.average("DS")
        );

        System.out.println(
                "DS 最高="
                + manager.highest("DS")
        );

        System.out.println("--------------------");

        System.out.println("依課號排序報告：");

        manager.printSortedReport();
    }
}