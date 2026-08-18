class CourseGrade {
    private String studentId;
    private String name;
    private double usualScore;
    private double midtermScore;
    private double finalExamScore;
    private double attendance;

    public CourseGrade(String studentId, String name,
                       double usualScore, double midtermScore,
                       double finalExamScore, double attendance) {

        this.studentId = studentId;
        this.name = name;

        this.usualScore = checkScore(usualScore);
        this.midtermScore = checkScore(midtermScore);
        this.finalExamScore = checkScore(finalExamScore);
        this.attendance = checkScore(attendance);
    }

    private double checkScore(double score) {
        if (score < 0) {
            return 0;
        }

        if (score > 100) {
            return 100;
        }

        return score;
    }

    public double calculateFinalScore() {
        return usualScore * 0.5
                + midtermScore * 0.2
                + finalExamScore * 0.2
                + attendance * 0.1;
    }

    public String getLevel() {
        double score = calculateFinalScore();

        if (score >= 90) {
            return "A";
        } else if (score >= 80) {
            return "B";
        } else if (score >= 70) {
            return "C";
        } else if (score >= 60) {
            return "D";
        } else {
            return "F";
        }
    }

    @Override
    public String toString() {
        return "學號：" + studentId
                + "，姓名：" + name
                + "，平時：" + usualScore
                + "，期中：" + midtermScore
                + "，期末：" + finalExamScore
                + "，出席：" + attendance
                + "，總分：" + calculateFinalScore()
                + "，等級：" + getLevel();
    }
}


public class CourseGradeManager {
    public static void main(String[] args) {

        CourseGrade[] students = {
            new CourseGrade("S001", "小明", 90, 85, 88, 100),
            new CourseGrade("S002", "小華", 75, 80, 70, 90),
            new CourseGrade("S003", "小美", 95, 92, 96, 100),
            new CourseGrade("S004", "小強", 50, 55, 45, 80),
            new CourseGrade("S005", "小安", 60, 50, 55, 70)
        };

        System.out.println("=== 所有學生成績 ===");

        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i]);
        }

        double total = 0;

        for (int i = 0; i < students.length; i++) {
            total += students[i].calculateFinalScore();
        }

        double average = total / students.length;

        System.out.println("\n=== 全班平均 ===");
        System.out.println("平均分數：" + average);

        CourseGrade highest = students[0];

        for (int i = 1; i < students.length; i++) {
            if (students[i].calculateFinalScore()
                    > highest.calculateFinalScore()) {

                highest = students[i];
            }
        }

        System.out.println("\n=== 最高分 ===");
        System.out.println(highest);
        System.out.println("\n=== 不及格名單 ===");

        for (int i = 0; i < students.length; i++) {
            if (students[i].calculateFinalScore() < 60) {
                System.out.println(students[i]);
            }
        }
    }
}