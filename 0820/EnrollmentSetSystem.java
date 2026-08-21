import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class EnrollmentSetSystem {

    static class Enrollment {
        private String studentId;
        private String courseCode;

        public Enrollment(String studentId, String courseCode) {
            this.studentId = studentId;
            this.courseCode = courseCode;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null) {
                return false;
            }

            if (!(obj instanceof Enrollment)) {
                return false;
            }

            Enrollment other = (Enrollment) obj;

            return Objects.equals(this.studentId, other.studentId)
                    && Objects.equals(this.courseCode, other.courseCode);
        }

        @Override
        public int hashCode() {
            return Objects.hash(studentId, courseCode);
        }

        @Override
        public String toString() {
            return "學生：" + studentId
                    + "，課程：" + courseCode;
        }
    }

    public static void main(String[] args) {

        Set<Enrollment> enrollments =
                new HashSet<Enrollment>();

        Enrollment e1 =
                new Enrollment("S001", "JAVA");

        Enrollment e2 =
                new Enrollment("S001", "DATABASE");

        Enrollment e3 =
                new Enrollment("S001", "JAVA");

        Enrollment e4 =
                new Enrollment("S002", "JAVA");

        System.out.println("新增 S001 JAVA："+ enrollments.add(e1));
        System.out.println("新增 S001 DATABASE：" + enrollments.add(e2));
        System.out.println("再次新增 S001 JAVA："+ enrollments.add(e3));
        System.out.println("新增 S002 JAVA："+ enrollments.add(e4));
        System.out.println("--------------------");
        System.out.println("目前報名資料：");

        for (Enrollment e : enrollments) {
            System.out.println(e);
        }

        System.out.println("--------------------");

        Enrollment test =
                new Enrollment("S001", "JAVA");

        System.out.println("contains S001 JAVA："+ enrollments.contains(test));
        System.out.println("取消 S001 JAVA：" + enrollments.remove(test));
        System.out.println("再次取消 S001 JAVA：" + enrollments.remove(test));
        System.out.println("--------------------");
        System.out.println("取消後資料：");

        for (Enrollment e : enrollments) {
            System.out.println(e);
        }
    }
}