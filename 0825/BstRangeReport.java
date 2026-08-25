public class BstRangeReport {

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

        public Integer min() {

            if (root == null) {
                return null;
            }

            Node current = root;

            while (current.left != null) {
                current = current.left;
            }

            return current.key;
        }

        public Integer max() {

            if (root == null) {
                return null;
            }

            Node current = root;

            while (current.right != null) {
                current = current.right;
            }

            return current.key;
        }

        public void printRange(int low, int high) {

            if (low > high) {
                System.out.println("low > high，無資料");
                return;
            }

            printRange(root, low, high);
            System.out.println();
        }

        private void printRange(
                Node node,
                int low,
                int high) {

            if (node == null) {
                return;
            }

            if (node.key > low) {
                printRange(node.left, low, high);
            }

            if (node.key >= low && node.key <= high) {
                System.out.print(node.key + " ");
            }

            if (node.key < high) {
                printRange(node.right, low, high);
            }
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

        System.out.println("min：" + tree.min());
        System.out.println("max：" + tree.max());

        System.out.println("--------------------");

        System.out.print("範圍 30 到 70：");
        tree.printRange(30, 70);

        System.out.print("範圍 20 到 40：");
        tree.printRange(20, 40);

        System.out.print("範圍 35 到 65：");
        tree.printRange(35, 65);

        System.out.print("low > high 測試：");
        tree.printRange(70, 30);

        System.out.println("--------------------");

        BinarySearchTree emptyTree =
                new BinarySearchTree();

        System.out.println("Empty Tree min：" + emptyTree.min());
        System.out.println("Empty Tree max：" + emptyTree.max());
    }
}