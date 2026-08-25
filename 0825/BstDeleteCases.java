public class BstDeleteCases {

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

        public void delete(int key) {
            root = delete(root, key);
        }

        private Node delete(Node node, int key) {

            if (node == null) {
                return null;
            }

            if (key < node.key) {

                node.left = delete(node.left, key);

            } else if (key > node.key) {

                node.right = delete(node.right, key);

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

                node.key = successor.key;

                node.right =
                        delete(
                                node.right,
                                successor.key
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

        public boolean isValid() {
            return isValid(
                    root,
                    Long.MIN_VALUE,
                    Long.MAX_VALUE
            );
        }

        private boolean isValid(
                Node node,
                long min,
                long max) {

            if (node == null) {
                return true;
            }

            if (node.key <= min
                    || node.key >= max) {
                return false;
            }

            return isValid(
                    node.left,
                    min,
                    node.key
            )
            &&
            isValid(
                    node.right,
                    node.key,
                    max
            );
        }

        public void printReport() {

            System.out.print("inorder：");
            inorder();

            System.out.println(
                    "size：" + size()
            );

            System.out.println(
                    "valid result：" + isValid()
            );

            System.out.println("--------------------");
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
        tree.insert(65);

        System.out.println("原始 BST：");
        tree.printReport();

        System.out.println("刪除 leaf：20");
        tree.delete(20);
        tree.printReport();

        System.out.println("刪除 single-child：60");
        tree.delete(60);
        tree.printReport();

        System.out.println("刪除 two-child：70");
        tree.delete(70);
        tree.printReport();
    }
}