public class BinaryTreeStructureReport {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    static class BinaryTree {
        Node root;

        public BinaryTree() {
            root = null;
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

        public int leafCount() {
            return leafCount(root);
        }

        private int leafCount(Node node) {

            if (node == null) {
                return 0;
            }

            if (node.left == null
                    && node.right == null) {
                return 1;
            }

            return leafCount(node.left)
                    + leafCount(node.right);
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

        public void printLeaves() {
            printLeaves(root);
            System.out.println();
        }

        private void printLeaves(Node node) {

            if (node == null) {
                return;
            }

            if (node.left == null
                    && node.right == null) {

                System.out.print(
                        node.value + " "
                );

                return;
            }

            printLeaves(node.left);
            printLeaves(node.right);
        }

        public void printReport() {

            if (root == null) {
                System.out.println("root：null");
            } else {
                System.out.println(
                        "root：" + root.value
                );
            }

            System.out.print("leaf：");
            printLeaves();

            System.out.println(
                    "size：" + size()
            );

            System.out.println(
                    "leaf count：" + leafCount()
            );

            System.out.println(
                    "height：" + height()
            );
        }
    }

    public static void main(String[] args) {

        BinaryTree tree =
                new BinaryTree();

        tree.root = new Node(10);

        tree.root.left =
                new Node(5);

        tree.root.right =
                new Node(20);

        tree.root.left.left =
                new Node(3);

        tree.root.left.right =
                new Node(7);

        tree.root.right.left =
                new Node(15);

        tree.root.right.right =
                new Node(25);

        System.out.println("一般 Binary Tree：");
        tree.printReport();

        System.out.println("--------------------");

        BinaryTree emptyTree =
                new BinaryTree();

        System.out.println("Empty Tree：");
        emptyTree.printReport();

        System.out.println("--------------------");

        BinaryTree singleTree =
                new BinaryTree();

        singleTree.root =
                new Node(100);

        System.out.println("Single-node Tree：");
        singleTree.printReport();
    }
}