public class CourseBstIndex {

    static class Course {
        private String courseCode;
        private String courseName;
        private int credits;

        public Course(String courseCode, String courseName, int credits) {
            this.courseCode = courseCode;
            this.courseName = courseName;

            if (credits < 1) {
                credits = 1;
            }

            if (credits > 6) {
                credits = 6;
            }

            this.credits = credits;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getCourseName() {
            return courseName;
        }

        public int getCredits() {
            return credits;
        }

        public void setCredits(int credits) {

            if (credits < 1) {
                credits = 1;
            }

            if (credits > 6) {
                credits = 6;
            }

            this.credits = credits;
        }

        @Override
        public String toString() {
            return "代碼=" + courseCode
                    + ", 課程=" + courseName
                    + ", 學分=" + credits;
        }
    }

    static class Node {
        Course course;
        Node left;
        Node right;

        public Node(Course course) {
            this.course = course;
        }
    }

    static class CourseBst {

        private Node root;

        public boolean add(Course course) {

            if (course == null) {
                return false;
            }

            if (course.getCourseCode() == null
                    || course.getCourseCode().trim().isEmpty()) {
                return false;
            }

            if (find(course.getCourseCode()) != null) {
                return false;
            }

            root = add(root, course);

            return true;
        }

        private Node add(Node node, Course course) {

            if (node == null) {
                return new Node(course);
            }

            int result =
                    course.getCourseCode()
                            .compareTo(node.course.getCourseCode());

            if (result < 0) {

                node.left = add(node.left, course);

            } else if (result > 0) {

                node.right = add(node.right, course);
            }

            return node;
        }

        public Course find(String courseCode) {

            if (courseCode == null) {
                return null;
            }

            Node current = root;

            while (current != null) {

                int result =
                        courseCode.compareTo(
                                current.course.getCourseCode()
                        );

                if (result == 0) {
                    return current.course;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean updateCredits(
                String courseCode,
                int credits) {

            Course course = find(courseCode);

            if (course == null) {
                return false;
            }

            course.setCredits(credits);

            return true;
        }

        public boolean delete(String courseCode) {

            if (find(courseCode) == null) {
                return false;
            }

            root = delete(root, courseCode);

            return true;
        }

        private Node delete(
                Node node,
                String courseCode) {

            if (node == null) {
                return null;
            }

            int result =
                    courseCode.compareTo(
                            node.course.getCourseCode()
                    );

            if (result < 0) {

                node.left =
                        delete(node.left, courseCode);

            } else if (result > 0) {

                node.right =
                        delete(node.right, courseCode);

            } else {

                if (node.left == null
                        && node.right == null) {
                    return null;
                }

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.course = successor.course;

                node.right =
                        delete(
                                node.right,
                                successor.course.getCourseCode()
                        );
            }

            return node;
        }

        private Node findMin(Node node) {

            Node current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        public void rangeReport(
                String lowCode,
                String highCode) {

            if (lowCode == null || highCode == null) {
                return;
            }

            if (lowCode.compareTo(highCode) > 0) {
                System.out.println("lowCode > highCode");
                return;
            }

            rangeReport(
                    root,
                    lowCode,
                    highCode
            );
        }

        private void rangeReport(
                Node node,
                String lowCode,
                String highCode) {

            if (node == null) {
                return;
            }

            String code =
                    node.course.getCourseCode();

            if (code.compareTo(lowCode) > 0) {
                rangeReport(
                        node.left,
                        lowCode,
                        highCode
                );
            }

            if (code.compareTo(lowCode) >= 0
                    && code.compareTo(highCode) <= 0) {

                System.out.println(node.course);
            }

            if (code.compareTo(highCode) < 0) {
                rangeReport(
                        node.right,
                        lowCode,
                        highCode
                );
            }
        }

        public void sortedReport() {
            sortedReport(root);
        }

        private void sortedReport(Node node) {

            if (node == null) {
                return;
            }

            sortedReport(node.left);

            System.out.println(node.course);

            sortedReport(node.right);
        }
    }

    public static void main(String[] args) {

        CourseBst tree =
                new CourseBst();

        System.out.println(
                "新增 CS103："
                + tree.add(
                        new Course(
                                "CS103",
                                "資料結構",
                                3
                        )
                )
        );

        System.out.println(
                "新增 CS101："
                + tree.add(
                        new Course(
                                "CS101",
                                "程式設計",
                                3
                        )
                )
        );

        System.out.println(
                "新增 CS105："
                + tree.add(
                        new Course(
                                "CS105",
                                "資料庫",
                                4
                        )
                )
        );

        System.out.println(
                "新增 CS102："
                + tree.add(
                        new Course(
                                "CS102",
                                "物件導向",
                                3
                        )
                )
        );

        System.out.println(
                "新增 CS104："
                + tree.add(
                        new Course(
                                "CS104",
                                "網頁程式",
                                2
                        )
                )
        );

        System.out.println(
                "重複新增 CS103："
                + tree.add(
                        new Course(
                                "CS103",
                                "重複課程",
                                5
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("排序報表：");
        tree.sortedReport();

        System.out.println("--------------------");

        System.out.println(
                "尋找 CS102："
                + tree.find("CS102")
        );

        System.out.println(
                "尋找 CS999："
                + tree.find("CS999")
        );

        System.out.println("--------------------");

        System.out.println(
                "更新 CS102 為 5 學分："
                + tree.updateCredits(
                        "CS102",
                        5
                )
        );

        System.out.println(
                "更新後 CS102："
                + tree.find("CS102")
        );

        System.out.println(
                "更新 CS104 為 10 學分："
                + tree.updateCredits(
                        "CS104",
                        10
                )
        );

        System.out.println(
                "更新後 CS104："
                + tree.find("CS104")
        );

        System.out.println("--------------------");

        System.out.println(
                "代碼範圍 CS102 到 CS104："
        );

        tree.rangeReport(
                "CS102",
                "CS104"
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 CS103："
                + tree.delete("CS103")
        );

        System.out.println(
                "刪除 CS999："
                + tree.delete("CS999")
        );

        System.out.println("--------------------");

        System.out.println("刪除後排序報表：");
        tree.sortedReport();
    }
}