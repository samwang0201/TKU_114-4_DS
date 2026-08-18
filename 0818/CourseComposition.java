class Instructor {
    private String id;
    private String name;

    // Constructor
    public Instructor(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


class Course {
    private String courseCode;
    private String title;

    private Instructor instructor;

    public Course(String courseCode, String title, Instructor instructor) {
        this.courseCode = courseCode;
        this.title = title;
        this.instructor = instructor;
    }

    public String summary() {
        return "課程代碼：" + courseCode
                + "，課程名稱：" + title
                + "，授課教師編號：" + instructor.getId()
                + "，授課教師：" + instructor.getName();
    }
}


public class CourseComposition {
    public static void main(String[] args) {

        Instructor teacher = new Instructor("T001", "王老師");

        Course course1 = new Course("C101","Math",teacher);
        Course course2 = new Course("C102","English",teacher);
        System.out.println(course1.summary());
        System.out.println(course2.summary());
    }
}