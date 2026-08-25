import java.util.ArrayList;
import java.util.List;

public class CompleteBstTestSuite {

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
            } else if (key > node.key) {
                node.right = insert(node.right, key);
            }

            return node;
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

        public Integer rootValue() {

            if (root == null) {
                return null;
            }

            return root.key;
        }

        public List<Integer> inorder() {

            List<Integer> result =
                    new ArrayList<Integer>();

            inorder(root, result);

            return result;
        }

        private void inorder(
                Node node,
                List<Integer> result) {

            if (node == null) {
                return;
            }

            inorder(node.left, result);

            result.add(node.key);

            inorder(node.right, result);
        }

        public List<Integer> valuesBetween(
                int low,
                int high) {

            List<Integer> result =
                    new ArrayList<Integer>();

            if (low > high) {
                return result;
            }

            valuesBetween(
                    root,
                    low,
                    high,
                    result
            );

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
    }

    static int passCount = 0;
    static int failCount = 0;

    static void check(
            String description,
            boolean condition) {

        if (condition) {
            System.out.println(
                    "PASS："
                    + description
            );

            passCount++;

        } else {

            System.out.println(
                    "FAIL："
                    + description
            );

            failCount++;
        }
    }

    static List<Integer> list(int... values) {

        List<Integer> result =
                new ArrayList<Integer>();

        for (int i = 0; i < values.length; i++) {
            result.add(values[i]);
        }

        return result;
    }

    public static void main(String[] args) {

        BinarySearchTree empty =
                new BinarySearchTree();

        check(
                "empty tree size = 0",
                empty.size() == 0
        );

        check(
                "empty tree is empty",
                empty.isEmpty()
        );

        check(
                "empty tree contains missing = false",
                !empty.contains(10)
        );

        check(
                "empty tree delete = false",
                !empty.delete(10)
        );

        check(
                "empty tree invariant valid",
                empty.isValid()
        );

        BinarySearchTree tree =
                new BinarySearchTree();

        check(
                "insert root 50",
                tree.insert(50)
        );

        check(
                "root value = 50",
                tree.rootValue() == 50
        );

        check(
                "insert 30",
                tree.insert(30)
        );

        check(
                "insert 70",
                tree.insert(70)
        );

        check(
                "insert 20",
                tree.insert(20)
        );

        check(
                "insert 40",
                tree.insert(40)
        );

        check(
                "insert 60",
                tree.insert(60)
        );

        check(
                "insert 80",
                tree.insert(80)
        );

        check(
                "duplicate 30 rejected",
                !tree.insert(30)
        );

        check(
                "size after duplicate still 7",
                tree.size() == 7
        );

        check(
                "contains root 50",
                tree.contains(50)
        );

        check(
                "contains leaf 20",
                tree.contains(20)
        );

        check(
                "contains internal 70",
                tree.contains(70)
        );

        check(
                "missing 99 not found",
                !tree.contains(99)
        );

        check(
                "inorder sorted",
                tree.inorder().equals(
                        list(
                                20, 30, 40,
                                50, 60, 70, 80
                        )
                )
        );

        check(
                "tree invariant valid",
                tree.isValid()
        );

        check(
                "range 30 to 70",
                tree.valuesBetween(30, 70)
                        .equals(
                                list(
                                        30, 40, 50,
                                        60, 70
                                )
                        )
        );

        check(
                "empty range",
                tree.valuesBetween(90, 100)
                        .isEmpty()
        );

        check(
                "low greater than high",
                tree.valuesBetween(70, 30)
                        .isEmpty()
        );

        check(
                "delete leaf 20",
                tree.delete(20)
        );

        check(
                "leaf 20 removed",
                !tree.contains(20)
        );

        check(
                "size after leaf delete = 6",
                tree.size() == 6
        );

        check(
                "valid after leaf delete",
                tree.isValid()
        );

        BinarySearchTree oneChild =
                new BinarySearchTree();

        oneChild.insert(50);
        oneChild.insert(30);
        oneChild.insert(40);

        check(
                "delete one-child node 30",
                oneChild.delete(30)
        );

        check(
                "child 40 preserved",
                oneChild.contains(40)
        );

        check(
                "size after one-child delete = 2",
                oneChild.size() == 2
        );

        check(
                "valid after one-child delete",
                oneChild.isValid()
        );

        BinarySearchTree twoChild =
                new BinarySearchTree();

        twoChild.insert(50);
        twoChild.insert(30);
        twoChild.insert(70);
        twoChild.insert(60);
        twoChild.insert(80);

        check(
                "delete two-child root 50",
                twoChild.delete(50)
        );

        check(
                "old root 50 removed",
                !twoChild.contains(50)
        );

        check(
                "two-child tree size = 4",
                twoChild.size() == 4
        );

        check(
                "two-child inorder correct",
                twoChild.inorder().equals(
                        list(30, 60, 70, 80)
                )
        );

        check(
                "valid after two-child delete",
                twoChild.isValid()
        );

        check(
                "delete missing 999 false",
                !twoChild.delete(999)
        );

        check(
                "size unchanged after missing delete",
                twoChild.size() == 4
        );

        BinarySearchTree single =
                new BinarySearchTree();

        single.insert(100);

        check(
                "single root delete",
                single.delete(100)
        );

        check(
                "single root becomes empty",
                single.isEmpty()
        );

        check(
                "single root size = 0",
                single.size() == 0
        );

        check(
                "empty after root delete still valid",
                single.isValid()
        );

        System.out.println("--------------------");

        System.out.println(
                "PASS 數量：" + passCount
        );

        System.out.println(
                "FAIL 數量：" + failCount
        );

        System.out.println(
                "總斷言數："
                + (passCount + failCount)
        );
    }
}