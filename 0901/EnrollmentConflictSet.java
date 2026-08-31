import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class EnrollmentConflictSet {

    public static class Enrollment {
        private String studentId;
        private String courseCode;

        public Enrollment(String studentId, String courseCode) {
            this.studentId = studentId;
            this.courseCode = courseCode;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String key() {
            return studentId + "|" + courseCode;
        }

        @Override
        public String toString() {
            return studentId + "|" + courseCode;
        }
    }

    public static void analyze(List<Enrollment> records) {

        Set<String> seen =
                new HashSet<String>();

        Set<String> duplicates =
                new TreeSet<String>();

        Map<String, Set<String>> studentCourses =
                new HashMap<String, Set<String>>();

        Map<String, Set<String>> courseStudents =
                new HashMap<String, Set<String>>();

        if (records != null) {

            for (Enrollment record : records) {

                if (record == null) {
                    continue;
                }

                String studentId = record.getStudentId();
                String courseCode = record.getCourseCode();

                if (studentId == null
                        || courseCode == null) {
                    continue;
                }

                studentId = studentId.trim();
                courseCode = courseCode.trim();

                if (studentId.isEmpty()
                        || courseCode.isEmpty()) {
                    continue;
                }

                String key =
                        studentId + "|" + courseCode;

                if (!seen.add(key)) {
                    duplicates.add(key);
                }

                if (!studentCourses.containsKey(studentId)) {
                    studentCourses.put(
                            studentId,
                            new TreeSet<String>()
                    );
                }

                studentCourses
                        .get(studentId)
                        .add(courseCode);

                if (!courseStudents.containsKey(courseCode)) {
                    courseStudents.put(
                            courseCode,
                            new TreeSet<String>()
                    );
                }

                courseStudents
                        .get(courseCode)
                        .add(studentId);
            }
        }

        System.out.println("===== 重複記錄 =====");

        if (duplicates.isEmpty()) {
            System.out.println("無");
        } else {
            for (String duplicate : duplicates) {
                System.out.println(duplicate);
            }
        }

        System.out.println("--------------------");

        System.out.println("===== 每人課程集合 =====");

        Map<String, Set<String>> sortedStudents =
                new TreeMap<String, Set<String>>(
                        studentCourses
                );

        for (Map.Entry<String, Set<String>> entry
                : sortedStudents.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " = "
                    + entry.getValue()
            );
        }

        System.out.println("--------------------");

        System.out.println("===== 每門課修課人數 =====");

        Map<String, Set<String>> sortedCourses =
                new TreeMap<String, Set<String>>(
                        courseStudents
                );

        for (Map.Entry<String, Set<String>> entry
                : sortedCourses.entrySet()) {

            System.out.println(
                    entry.getKey()
                    + " = "
                    + entry.getValue().size()
            );
        }
    }

    public static void main(String[] args) {

        List<Enrollment> records =
                new ArrayList<Enrollment>();

        records.add(
                new Enrollment("S01", "JAVA")
        );

        records.add(
                new Enrollment("S01", "DS")
        );

        records.add(
                new Enrollment("S02", "JAVA")
        );

        records.add(
                new Enrollment("S01", "JAVA")
        );

        records.add(
                new Enrollment("S03", "DB")
        );

        records.add(
                new Enrollment("S02", "DS")
        );

        records.add(
                new Enrollment("S02", "JAVA")
        );

        analyze(records);
    }
}