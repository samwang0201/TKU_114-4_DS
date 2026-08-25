public class BstSearchTrace {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class BinarySearchTree {
        Node root;

        public void insert(int value) {
            root = insert(root, value);
        }

        private Node insert(Node node, int value) {

            if (node == null) {
                return new Node(value);
            }

            if (value < node.value) {
                node.left = insert(node.left, value);
            } else if (value > node.value) {
                node.right = insert(node.right, value);
            }

            return node;
        }

        public boolean searchTrace(int target) {

            Node current = root;
            int comparisonCount = 0;

            while (current != null) {

                comparisonCount++;

                System.out.println(
                        "current value = " + current.value
                );

                System.out.println(
                        "comparison count = "
                        + comparisonCount
                );

                if (target == current.value) {

                    System.out.println("結果：找到");
                    System.out.println(
                            "總比較次數："
                            + comparisonCount
                    );

                    return true;
                }

                if (target < current.value) {

                    System.out.println("方向：left");

                    current = current.left;

                } else {

                    System.out.println("方向：right");

                    current = current.right;
                }

                System.out.println("--------------------");
            }

            System.out.println("結果：找不到");
            System.out.println(
                    "總比較次數：" + comparisonCount
            );

            return false;
        }
    }

    public static void main(String[] args) {

        BinarySearchTree tree =
                new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        System.out.println("搜尋 root：50");
        tree.searchTrace(50);

        System.out.println("====================");

        System.out.println("搜尋 leaf：20");
        tree.searchTrace(20);

        System.out.println("====================");

        System.out.println("搜尋 internal node：70");
        tree.searchTrace(70);

        System.out.println("====================");

        System.out.println("搜尋 missing value：65");
        tree.searchTrace(65);
    }
}