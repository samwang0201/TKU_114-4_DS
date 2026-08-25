import java.util.ArrayList;
import java.util.List;

public class BstRangeStatistics {

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    static class BinarySearchTree {

        private Node root;

        public void insert(int key) {
            root = insert(root, key);
        }

        private Node insert(Node node, int key) {

            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }

            return node;
        }

        public List<Integer> valuesBetween(int low, int high) {

            List<Integer> result =
                    new ArrayList<Integer>();

            if (low > high) {
                return result;
            }

            valuesBetween(root, low, high, result);

            return result;
        }

        private void valuesBetween(
                Node node,
                int low,
                int high,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            if (node.key > low) {
                valuesBetween(
                        node.left,
                        low,
                        high,
                        result
                );
            }

            if (node.key >= low
                    && node.key <= high) {

                result.add(node.key);
            }

            if (node.key < high) {
                valuesBetween(
                        node.right,
                        low,
                        high,
                        result
                );
            }
        }

        public int countBetween(int low, int high) {

            if (low > high) {
                return 0;
            }

            return countBetween(root, low, high);
        }

        private int countBetween(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return 0;
            }

            if (node.key < low) {
                return countBetween(
                        node.right,
                        low,
                        high
                );
            }

            if (node.key > high) {
                return countBetween(
                        node.left,
                        low,
                        high
                );
            }

            return 1
                    + countBetween(
                            node.left,
                            low,
                            high
                    )
                    + countBetween(
                            node.right,
                            low,
                            high
                    );
        }

        public int sumBetween(int low, int high) {

            if (low > high) {
                return 0;
            }

            return sumBetween(root, low, high);
        }

        private int sumBetween(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return 0;
            }

            if (node.key < low) {
                return sumBetween(
                        node.right,
                        low,
                        high
                );
            }

            if (node.key > high) {
                return sumBetween(
                        node.left,
                        low,
                        high
                );
            }

            return node.key
                    + sumBetween(
                            node.left,
                            low,
                            high
                    )
                    + sumBetween(
                            node.right,
                            low,
                            high
                    );
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
        tree.insert(35);
        tree.insert(65);

        System.out.println("範圍 30 到 65：");

        System.out.println(
                "values："
                + tree.valuesBetween(30, 65)
        );

        System.out.println(
                "count："
                + tree.countBetween(30, 65)
        );

        System.out.println(
                "sum："
                + tree.sumBetween(30, 65)
        );

        System.out.println("--------------------");

        System.out.println("空範圍 90 到 100：");

        System.out.println(
                "values："
                + tree.valuesBetween(90, 100)
        );

        System.out.println(
                "count："
                + tree.countBetween(90, 100)
        );

        System.out.println(
                "sum："
                + tree.sumBetween(90, 100)
        );

        System.out.println("--------------------");

        System.out.println("low > high：");

        System.out.println(
                "values："
                + tree.valuesBetween(70, 30)
        );

        System.out.println(
                "count："
                + tree.countBetween(70, 30)
        );

        System.out.println(
                "sum："
                + tree.sumBetween(70, 30)
        );
    }
}