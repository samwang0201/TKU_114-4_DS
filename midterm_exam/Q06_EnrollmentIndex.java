import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Q06_EnrollmentIndex {

    private final Map<String, Set<String>> enrollmentMapR26;

    public Q06_EnrollmentIndex() {
        enrollmentMapR26 =
                new HashMap<String, Set<String>>();
    }

    public boolean enroll(
            String courseCode,
            String studentId) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return false;
        }

        if (studentId == null
                || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students =
                enrollmentMapR26.get(courseCode);

        if (students == null) {
            students = new HashSet<String>();
            enrollmentMapR26.put(courseCode, students);
        }

        if (students.contains(studentId)) {
            return false;
        }

        students.add(studentId);
        return true;
    }

    public boolean drop(
            String courseCode,
            String studentId) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return false;
        }

        if (studentId == null
                || studentId.trim().isEmpty()) {
            return false;
        }

        courseCode = courseCode.trim();
        studentId = studentId.trim();

        Set<String> students =
                enrollmentMapR26.get(courseCode);

        if (students == null) {
            return false;
        }

        if (!students.remove(studentId)) {
            return false;
        }

        if (students.isEmpty()) {
            enrollmentMapR26.remove(courseCode);
        }

        return true;
    }

    public int courseSize(String courseCode) {

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return 0;
        }

        Set<String> students =
                enrollmentMapR26.get(courseCode.trim());

        if (students == null) {
            return 0;
        }

        return students.size();
    }

    public List<String> studentsOf(String courseCode) {

        List<String> result =
                new ArrayList<String>();

        if (courseCode == null
                || courseCode.trim().isEmpty()) {
            return result;
        }

        Set<String> students =
                enrollmentMapR26.get(courseCode.trim());

        if (students == null) {
            return result;
        }

        result.addAll(students);
        result.sort(null);

        return result;
    }

    public List<String> coursesOf(String studentId) {

        List<String> result =
                new ArrayList<String>();

        if (studentId == null
                || studentId.trim().isEmpty()) {
            return result;
        }

        studentId = studentId.trim();

        for (Map.Entry<String, Set<String>> entry
                : enrollmentMapR26.entrySet()) {

            if (entry.getValue().contains(studentId)) {
                result.add(entry.getKey());
            }
        }

        result.sort(null);

        return result;
    }

    public Map<String, Integer> summary() {

        Map<String, Integer> result =
                new TreeMap<String, Integer>();

        for (Map.Entry<String, Set<String>> entry
                : enrollmentMapR26.entrySet()) {

            result.put(
                    entry.getKey(),
                    entry.getValue().size()
            );
        }

        return result;
    }

    public static void main(String[] args) {

        Q06_EnrollmentIndex index =
                new Q06_EnrollmentIndex();

        index.enroll("DS", "S02");
        index.enroll("DS", "S01");
        index.enroll("JAVA", "S01");

        System.out.println(
                index.studentsOf("DS")
        );

        System.out.println(
                index.coursesOf("S01")
        );

        System.out.println(
                index.summary()
        );
    }
}