import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Collections;
import java.util.Comparator;

public class CourseCollectionManager {

    static class Enrollment {
        private String studentId;
        private String name;
        private int score;
        private String tag;

        public Enrollment(String studentId,
                          String name,
                          int score,
                          String tag) {

            this.studentId = studentId;
            this.name = name;

            if (score < 0) {
                score = 0;
            }

            if (score > 100) {
                score = 100;
            }

            this.score = score;
            this.tag = tag;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        public String getTag() {
            return tag;
        }

        public void setScore(int score) {

            if (score < 0) {
                score = 0;
            }

            if (score > 100) {
                score = 100;
            }

            this.score = score;
        }

        @Override
        public String toString() {
            return "學號=" + studentId
                    + ", 姓名=" + name
                    + ", 分數=" + score
                    + ", 標籤=" + tag;
        }
    }

    static class ScoreComparator
            implements Comparator<Enrollment> {

        @Override
        public int compare(Enrollment a, Enrollment b) {

            if (a.getScore() > b.getScore()) {
                return -1;
            }

            if (a.getScore() < b.getScore()) {
                return 1;
            }

            return a.getStudentId()
                    .compareTo(b.getStudentId());
        }
    }

    private List<Enrollment> enrollmentList;
    private Set<String> studentIds;
    private Map<String, Enrollment> enrollmentMap;

    public CourseCollectionManager() {

        enrollmentList =
                new ArrayList<Enrollment>();

        studentIds =
                new HashSet<String>();

        enrollmentMap =
                new HashMap<String, Enrollment>();
    }

    public boolean add(Enrollment enrollment) {

        if (enrollment == null) {
            return false;
        }

        String id = enrollment.getStudentId();

        if (id == null || id.trim().isEmpty()) {
            return false;
        }

        if (studentIds.contains(id)) {
            return false;
        }

        enrollmentList.add(enrollment);
        studentIds.add(id);
        enrollmentMap.put(id, enrollment);

        return true;
    }

    public boolean updateScore(String studentId, int score) {

        Enrollment enrollment =
                enrollmentMap.get(studentId);

        if (enrollment == null) {
            return false;
        }

        enrollment.setScore(score);

        return true;
    }

    public List<Enrollment> findByTag(String tag) {

        List<Enrollment> result =
                new ArrayList<Enrollment>();

        if (tag == null) {
            return result;
        }

        for (int i = 0;
             i < enrollmentList.size();
             i++) {

            Enrollment e =
                    enrollmentList.get(i);

            String currentTag = e.getTag();

            if (currentTag != null
                    && currentTag.equalsIgnoreCase(tag)) {

                result.add(e);
            }
        }

        return result;
    }

    public Map<String, Integer> scoreDistribution() {

        Map<String, Integer> result =
                new HashMap<String, Integer>();

        result.put("A", 0);
        result.put("B", 0);
        result.put("C", 0);
        result.put("D", 0);
        result.put("F", 0);

        for (int i = 0;
             i < enrollmentList.size();
             i++) {

            int score =
                    enrollmentList.get(i).getScore();

            String grade;

            if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }

            result.put(
                    grade,
                    result.get(grade) + 1
            );
        }

        return result;
    }

    public List<Enrollment> top(int count) {

        List<Enrollment> copy =
                new ArrayList<Enrollment>(
                        enrollmentList
                );

        Collections.sort(
                copy,
                new ScoreComparator()
        );

        if (count <= 0) {
            return new ArrayList<Enrollment>();
        }

        if (count >= copy.size()) {
            return copy;
        }

        List<Enrollment> result =
                new ArrayList<Enrollment>();

        for (int i = 0; i < count; i++) {
            result.add(copy.get(i));
        }

        return result;
    }

    public void removeBelow(int minimum) {

        List<Enrollment> keep =
                new ArrayList<Enrollment>();

        for (int i = 0;
             i < enrollmentList.size();
             i++) {

            Enrollment e =
                    enrollmentList.get(i);

            if (e.getScore() >= minimum) {
                keep.add(e);
            }
        }

        enrollmentList.clear();
        studentIds.clear();
        enrollmentMap.clear();

        for (int i = 0; i < keep.size(); i++) {

            Enrollment e = keep.get(i);

            enrollmentList.add(e);
            studentIds.add(e.getStudentId());
            enrollmentMap.put(
                    e.getStudentId(),
                    e
            );
        }
    }

    public void printAll() {

        for (int i = 0;
             i < enrollmentList.size();
             i++) {

            System.out.println(
                    enrollmentList.get(i)
            );
        }
    }

    public static void main(String[] args) {

        CourseCollectionManager manager =
                new CourseCollectionManager();

        System.out.println("新增結果：");

        System.out.println(
            manager.add(
                new Enrollment(
                    "S001", "Amy", 95, "Java"
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S002", "Bob", 85, "Database"
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S003", "Cindy", 85, "Java"
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S004", "David", 72, ""
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S005", "Eric", 65, "Web"
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S006", "Fiona", 50, "Java"
                )
            )
        );

        System.out.println(
            manager.add(
                new Enrollment(
                    "S001", "Kevin", 100, "AI"
                )
            )
        );

        System.out.println("--------------------");

        System.out.println("所有資料：");
        manager.printAll();

        System.out.println("--------------------");

        System.out.println(
            "更新 S006 分數："
            + manager.updateScore("S006", 75)
        );

        System.out.println("--------------------");

        System.out.println("Java 標籤：");

        List<Enrollment> javaStudents =
                manager.findByTag("Java");

        for (int i = 0;
             i < javaStudents.size();
             i++) {

            System.out.println(
                    javaStudents.get(i)
            );
        }

        System.out.println("--------------------");

        System.out.println("分數分布：");

        Map<String, Integer> distribution =
                manager.scoreDistribution();

        System.out.println(
            "A：" + distribution.get("A")
        );

        System.out.println(
            "B：" + distribution.get("B")
        );

        System.out.println(
            "C：" + distribution.get("C")
        );

        System.out.println(
            "D：" + distribution.get("D")
        );

        System.out.println(
            "F：" + distribution.get("F")
        );

        System.out.println("--------------------");

        System.out.println("前三名：");

        List<Enrollment> top3 =
                manager.top(3);

        for (int i = 0;
             i < top3.size();
             i++) {

            System.out.println(
                    top3.get(i)
            );
        }

        System.out.println("--------------------");

        System.out.println("前 20 名：");

        List<Enrollment> top20 =
                manager.top(20);

        for (int i = 0;
             i < top20.size();
             i++) {

            System.out.println(
                    top20.get(i)
            );
        }

        System.out.println("--------------------");

        manager.removeBelow(70);

        System.out.println("移除低於 70 分後：");
        manager.printAll();
    }
}