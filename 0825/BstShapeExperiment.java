public class BstShapeExperiment {

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

        public int totalSearchComparisons(int[] values) {

            int total = 0;

            for (int i = 0; i < values.length; i++) {
                total = total
                        + searchComparisonCount(values[i]);
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
            String name,
            BinarySearchTree tree,
            int[] allValues) {

        System.out.println(name);

        System.out.print("inorder：");
        tree.inorder();

        System.out.println(
                "height："
                + tree.height()
        );

        System.out.println(
                "全部 search comparison count："
                + tree.totalSearchComparisons(allValues)
        );

        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        int[] allValues = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] order1 = {
            10, 20, 30, 40, 50,
            60, 70, 80, 90, 100,
            110, 120, 130, 140, 150
        };

        int[] order2 = {
            80,
            40, 120,
            20, 60, 100, 140,
            10, 30, 50, 70,
            90, 110, 130, 150
        };

        int[] order3 = {
            80, 20, 140, 40, 100,
            10, 150, 60, 120, 30,
            90, 50, 130, 70, 110
        };

        BinarySearchTree tree1 =
                buildTree(order1);

        BinarySearchTree tree2 =
                buildTree(order2);

        BinarySearchTree tree3 =
                buildTree(order3);

        printReport(
                "順序一：排序順序",
                tree1,
                allValues
        );

        printReport(
                "順序二：平衡順序",
                tree2,
                allValues
        );

        printReport(
                "順序三：混合順序",
                tree3,
                allValues
        );
    }
}