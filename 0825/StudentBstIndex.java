public class StudentBstIndex {

    static class Student {
        private String studentId;
        private String name;

        public Student(String studentId, String name) {
            this.studentId = studentId;
            this.name = name;
        }

        public String getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "學號=" + studentId
                    + ", 姓名=" + name;
        }
    }

    static class Node {
        Student student;
        Node left;
        Node right;

        public Node(Student student) {
            this.student = student;
        }
    }

    static class StudentBst {
        private Node root;

        public boolean insert(Student student) {

            if (student == null) {
                return false;
            }

            if (search(student.getStudentId()) != null) {
                return false;
            }

            root = insert(root, student);
            return true;
        }

        private Node insert(Node node, Student student) {

            if (node == null) {
                return new Node(student);
            }

            int result =
                    student.getStudentId()
                            .compareTo(node.student.getStudentId());

            if (result < 0) {
                node.left = insert(node.left, student);
            } else if (result > 0) {
                node.right = insert(node.right, student);
            }

            return node;
        }

        public Student search(String studentId) {

            Node current = root;

            while (current != null) {

                int result =
                        studentId.compareTo(
                                current.student.getStudentId()
                        );

                if (result == 0) {
                    return current.student;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean delete(String studentId) {

            if (search(studentId) == null) {
                return false;
            }

            root = delete(root, studentId);
            return true;
        }

        private Node delete(Node node, String studentId) {

            if (node == null) {
                return null;
            }

            int result =
                    studentId.compareTo(
                            node.student.getStudentId()
                    );

            if (result < 0) {

                node.left =
                        delete(node.left, studentId);

            } else if (result > 0) {

                node.right =
                        delete(node.right, studentId);

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

                node.student = successor.student;

                node.right =
                        delete(
                                node.right,
                                successor.student.getStudentId()
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

        public void inorder() {
            inorder(root);
            System.out.println();
        }

        private void inorder(Node node) {

            if (node == null) {
                return;
            }

            inorder(node.left);

            System.out.println(node.student);

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        StudentBst tree =
                new StudentBst();

        System.out.println(
                "新增 S003："
                + tree.insert(
                        new Student("S003", "Amy")
                )
        );

        System.out.println(
                "新增 S001："
                + tree.insert(
                        new Student("S001", "Bob")
                )
        );

        System.out.println(
                "新增 S005："
                + tree.insert(
                        new Student("S005", "Cindy")
                )
        );

        System.out.println(
                "新增 S002："
                + tree.insert(
                        new Student("S002", "David")
                )
        );

        System.out.println(
                "新增 S004："
                + tree.insert(
                        new Student("S004", "Eric")
                )
        );

        System.out.println(
                "重複新增 S003："
                + tree.insert(
                        new Student("S003", "Kevin")
                )
        );

        System.out.println("--------------------");

        System.out.println("Inorder：");
        tree.inorder();

        System.out.println("--------------------");

        System.out.println(
                "搜尋 S002："
                + tree.search("S002")
        );

        System.out.println(
                "搜尋 S999："
                + tree.search("S999")
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 S001："
                + tree.delete("S001")
        );

        System.out.println(
                "刪除 S999："
                + tree.delete("S999")
        );

        System.out.println("--------------------");

        System.out.println("刪除後 Inorder：");
        tree.inorder();
    }
}