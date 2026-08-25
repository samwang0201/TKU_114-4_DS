public class BstDeleteTestSuite {

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

        public boolean isEmpty() {
            return root == null;
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

        public void printReport() {

            System.out.print("inorder：");
            inorder();

            System.out.println(
                    "size：" + size()
            );

            System.out.println(
                    "empty：" + isEmpty()
            );

            System.out.println(
                    "valid：" + isValid()
            );
        }
    }

    public static void main(String[] args) {

        System.out.println("===== Test 1：Empty Tree =====");

        BinarySearchTree empty =
                new BinarySearchTree();

        System.out.println(
                "delete 10："
                + empty.delete(10)
        );

        empty.printReport();

        System.out.println();


        System.out.println("===== Test 2：Missing Value =====");

        BinarySearchTree missing =
                new BinarySearchTree();

        missing.insert(50);
        missing.insert(30);
        missing.insert(70);

        System.out.println(
                "delete 99："
                + missing.delete(99)
        );

        missing.printReport();

        System.out.println();


        System.out.println("===== Test 3：Single Root =====");

        BinarySearchTree single =
                new BinarySearchTree();

        single.insert(50);

        System.out.println(
                "delete root 50："
                + single.delete(50)
        );

        single.printReport();

        System.out.println();


        System.out.println("===== Test 4：Root With One Child =====");

        BinarySearchTree oneChild =
                new BinarySearchTree();

        oneChild.insert(50);
        oneChild.insert(30);

        System.out.println(
                "delete root 50："
                + oneChild.delete(50)
        );

        oneChild.printReport();

        System.out.println();


        System.out.println("===== Test 5：Root With Two Children =====");

        BinarySearchTree twoChildren =
                new BinarySearchTree();

        twoChildren.insert(50);
        twoChildren.insert(30);
        twoChildren.insert(70);
        twoChildren.insert(20);
        twoChildren.insert(40);
        twoChildren.insert(60);
        twoChildren.insert(80);

        System.out.println(
                "delete root 50："
                + twoChildren.delete(50)
        );

        twoChildren.printReport();

        System.out.println();


        System.out.println("===== Test 6：Delete Until Empty =====");

        BinarySearchTree tree =
                new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);

        int[] deleteOrder = {
            20, 40, 30, 70, 50
        };

        for (int i = 0; i < deleteOrder.length; i++) {

            int key = deleteOrder[i];

            System.out.println(
                    "delete " + key
                    + "：" + tree.delete(key)
            );

            tree.printReport();

            System.out.println("--------------------");
        }
    }
}