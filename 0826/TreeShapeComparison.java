public class TreeShapeComparison {

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

        public int height() {
            return height(root);
        }

        private int height(Node node) {

            if (node == null) {
                return 0;
            }

            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            }

            return rightHeight + 1;
        }

        public int searchComparisonCount(int target) {

            Node current = root;
            int count = 0;

            while (current != null) {

                count++;

                if (target == current.key) {
                    return count;
                }

                if (target < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return count;
        }

        public int totalSearchComparisons(int[] keys) {

            int total = 0;

            for (int i = 0; i < keys.length; i++) {
                total = total
                        + searchComparisonCount(keys[i]);
            }

            return total;
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

            System.out.print(node.key + " ");

            inorder(node.right);
        }
    }

    static BinarySearchTree buildTree(int[] order) {

        BinarySearchTree tree =
                new BinarySearchTree();

        for (int i = 0; i < order.length; i++) {
            tree.insert(order[i]);
        }

        return tree;
    }

    static void printReport(
            String title,
            BinarySearchTree tree,
            int[] allKeys,
            int missingKey) {

        System.out.println(title);

        System.out.print("inorder：");
        tree.inorder();

        System.out.println(
                "height："
                + tree.height()
        );

        System.out.println(
                "全部 key 搜尋比較總數："
                + tree.totalSearchComparisons(allKeys)
        );

        System.out.println(
                "missing key "
                + missingKey
                + " 比較次數："
                + tree.searchComparisonCount(missingKey)
        );

        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        int[] allKeys = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] ascending = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] descending = {
            150, 140, 130, 120, 110,
            100, 90, 80, 70, 60,
            50, 40, 30, 20, 10
        };

        int[] balanced = {
            80,
            40, 120,
            20, 60, 100, 140,
            10, 30, 50, 70,
            90, 110, 130, 150
        };

        BinarySearchTree ascendingTree =
                buildTree(ascending);

        BinarySearchTree descendingTree =
                buildTree(descending);

        BinarySearchTree balancedTree =
                buildTree(balanced);

        int missingKey = 155;

        printReport(
                "升冪順序：",
                ascendingTree,
                allKeys,
                missingKey
        );

        printReport(
                "降冪順序：",
                descendingTree,
                allKeys,
                missingKey
        );

        printReport(
                "接近平衡順序：",
                balancedTree,
                allKeys,
                missingKey
        );
    }
}