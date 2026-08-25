public class SkewedBstReport {

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

        public int size() {
            return size(root);
        }

        private int size(Node node) {

            if (node == null) {
                return 0;
            }

            return 1
                    + size(node.left)
                    + size(node.right);
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {

            if (node == null) {
                return 0;
            }

            int leftHeight =
                    height(node.left);

            int rightHeight =
                    height(node.right);

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

    public static void main(String[] args) {

        BinarySearchTree skewedTree =
                new BinarySearchTree();

        skewedTree.insert(10);
        skewedTree.insert(20);
        skewedTree.insert(30);
        skewedTree.insert(40);
        skewedTree.insert(50);
        skewedTree.insert(60);
        skewedTree.insert(70);

        BinarySearchTree balancedTree =
                new BinarySearchTree();

        balancedTree.insert(40);
        balancedTree.insert(20);
        balancedTree.insert(60);
        balancedTree.insert(10);
        balancedTree.insert(30);
        balancedTree.insert(50);
        balancedTree.insert(70);

        System.out.println("排序資料建立的 Tree：");

        System.out.print("inorder：");
        skewedTree.inorder();

        System.out.println(
                "size：" + skewedTree.size()
        );

        System.out.println(
                "height：" + skewedTree.height()
        );

        System.out.println(
                "搜尋 70 comparison count："
                + skewedTree.searchComparisonCount(70)
        );

        System.out.println("--------------------");

        System.out.println("平衡順序建立的 Tree：");

        System.out.print("inorder：");
        balancedTree.inorder();

        System.out.println(
                "size：" + balancedTree.size()
        );

        System.out.println(
                "height：" + balancedTree.height()
        );

        System.out.println(
                "搜尋 70 comparison count："
                + balancedTree.searchComparisonCount(70)
        );

        System.out.println("--------------------");

        System.out.println("比較結果：");

        System.out.println(
                "Skewed Tree height："
                + skewedTree.height()
        );

        System.out.println(
                "Balanced Tree height："
                + balancedTree.height()
        );

        System.out.println(
                "Skewed 搜尋 70 次數："
                + skewedTree.searchComparisonCount(70)
        );

        System.out.println(
                "Balanced 搜尋 70 次數："
                + balancedTree.searchComparisonCount(70)
        );
    }
}