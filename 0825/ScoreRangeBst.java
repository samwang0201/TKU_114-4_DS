public class ScoreRangeBst {

    static class StudentScore {
        private String studentId;
        private String name;
        private int score;

        public StudentScore(
                String studentId,
                String name,
                int score) {

            this.studentId = studentId;
            this.name = name;
            this.score = score;
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

        @Override
        public String toString() {
            return "學號=" + studentId
                    + ", 姓名=" + name
                    + ", 分數=" + score;
        }
    }

    static class Node {
        StudentScore data;
        Node left;
        Node right;

        public Node(StudentScore data) {
            this.data = data;
        }
    }

    static class ScoreBst {

        private Node root;

        public boolean insert(StudentScore data) {

            if (data == null) {
                return false;
            }

            if (search(
                    data.getScore(),
                    data.getStudentId()
            ) != null) {
                return false;
            }

            root = insert(root, data);

            return true;
        }

        private Node insert(
                Node node,
                StudentScore data) {

            if (node == null) {
                return new Node(data);
            }

            int result =
                    compare(
                            data.getScore(),
                            data.getStudentId(),
                            node.data.getScore(),
                            node.data.getStudentId()
                    );

            if (result < 0) {
                node.left = insert(node.left, data);
            } else if (result > 0) {
                node.right = insert(node.right, data);
            }

            return node;
        }

        private int compare(
                int score1,
                String id1,
                int score2,
                String id2) {

            if (score1 < score2) {
                return -1;
            }

            if (score1 > score2) {
                return 1;
            }

            return id1.compareTo(id2);
        }

        public StudentScore search(
                int score,
                String studentId) {

            Node current = root;

            while (current != null) {

                int result =
                        compare(
                                score,
                                studentId,
                                current.data.getScore(),
                                current.data.getStudentId()
                        );

                if (result == 0) {
                    return current.data;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public void printRange(int low, int high) {

            if (low > high) {
                System.out.println("low > high");
                return;
            }

            printRange(root, low, high);
        }

        private void printRange(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return;
            }

            int score = node.data.getScore();

            if (score >= low) {
                printRange(node.left, low, high);
            }

            if (score >= low && score <= high) {
                System.out.println(node.data);
            }

            if (score <= high) {
                printRange(node.right, low, high);
            }
        }

        public void inorder() {
            inorder(root);
        }

        private void inorder(Node node) {

            if (node == null) {
                return;
            }

            inorder(node.left);

            System.out.println(node.data);

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        ScoreBst tree =
                new ScoreBst();

        System.out.println(
                "新增 S001："
                + tree.insert(
                        new StudentScore(
                                "S001",
                                "Amy",
                                85
                        )
                )
        );

        System.out.println(
                "新增 S002："
                + tree.insert(
                        new StudentScore(
                                "S002",
                                "Bob",
                                90
                        )
                )
        );

        System.out.println(
                "新增 S003："
                + tree.insert(
                        new StudentScore(
                                "S003",
                                "Cindy",
                                85
                        )
                )
        );

        System.out.println(
                "新增 S004："
                + tree.insert(
                        new StudentScore(
                                "S004",
                                "David",
                                70
                        )
                )
        );

        System.out.println(
                "新增 S005："
                + tree.insert(
                        new StudentScore(
                                "S005",
                                "Eric",
                                95
                        )
                )
        );

        System.out.println(
                "新增 S006："
                + tree.insert(
                        new StudentScore(
                                "S006",
                                "Fiona",
                                90
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("Inorder：");
        tree.inorder();

        System.out.println("--------------------");

        System.out.println("查詢 85 分 S003：");

        System.out.println(
                tree.search(85, "S003")
        );

        System.out.println("--------------------");

        System.out.println("分數範圍 80 到 90：");

        tree.printRange(80, 90);

        System.out.println("--------------------");

        System.out.println("分數範圍 90 到 95：");

        tree.printRange(90, 95);

        System.out.println("--------------------");

        System.out.println("low > high 測試：");

        tree.printRange(95, 80);
    }
}