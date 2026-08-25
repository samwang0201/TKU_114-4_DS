public class BstOperationAudit {

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

        public boolean insert(int key) {

            if (contains(key)) {
                return false;
            }

            root = insert(root, key);
            return true;
        }

        private Node insert(Node node, int key) {

            if (node == null) {
                return new Node(key);
            }

            if (key < node.key) {
                node.left = insert(node.left, key);
            } else {
                node.right = insert(node.right, key);
            }

            return node;
        }

        public boolean delete(int key) {

            if (!contains(key)) {
                return false;
            }

            root = delete(root, key);
            return true;
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

                Node successor = findMin(node.right);

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

        public boolean contains(int key) {

            Node current = root;

            while (current != null) {

                if (key == current.key) {
                    return true;
                }

                if (key < current.key) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return false;
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

            int leftHeight = height(node.left);
            int rightHeight = height(node.right);

            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            }

            return rightHeight + 1;
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

        public void audit(
                String operation,
                boolean result) {

            System.out.println(
                    "操作：" + operation
            );

            System.out.println(
                    "結果：" + result
            );

            System.out.print(
                    "有序："
            );

            inorder();

            System.out.println(
                    "大小：" + size()
            );

            System.out.println(
                    "高度：" + height()
            );

            System.out.println(
                    "有效：" + isValid()
            );

            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {

        BinarySearchTree tree =
                new BinarySearchTree();

        boolean result;

        result = tree.insert(50);
        tree.audit("insert 50", result);

        result = tree.insert(30);
        tree.audit("insert 30", result);

        result = tree.insert(70);
        tree.audit("insert 70", result);

        result = tree.insert(20);
        tree.audit("insert 20", result);

        result = tree.insert(40);
        tree.audit("insert 40", result);

        result = tree.insert(60);
        tree.audit("insert 60", result);

        result = tree.insert(80);
        tree.audit("insert 80", result);

        result = tree.insert(65);
        tree.audit("insert 65", result);

        result = tree.insert(30);
        tree.audit("duplicate insert 30", result);

        result = tree.delete(99);
        tree.audit("delete missing 99", result);

        result = tree.delete(20);
        tree.audit("delete leaf 20", result);

        result = tree.delete(60);
        tree.audit("delete single-child 60", result);

        result = tree.delete(70);
        tree.audit("delete two-child 70", result);
    }
}