import java.util.ArrayList;
import java.util.List;

public class Q11_BstDeletion {

    private static class Node {
        int value;
        Node left;
        Node right;

        Node(int value) {
            this.value = value;
        }
    }

    private Node root;
    private int nodeCount;

    public Q11_BstDeletion() {
        root = null;
        nodeCount = 0;
    }

    public boolean add(int value) {

        if (root == null) {
            root = new Node(value);
            nodeCount++;
            return true;
        }

        Node current = root;

        while (true) {

            if (value == current.value) {
                return false;
            }

            if (value < current.value) {

                if (current.left == null) {
                    current.left = new Node(value);
                    nodeCount++;
                    return true;
                }

                current = current.left;

            } else {

                if (current.right == null) {
                    current.right = new Node(value);
                    nodeCount++;
                    return true;
                }

                current = current.right;
            }
        }
    }

    public boolean remove(int value) {

        if (!contains(value)) {
            return false;
        }

        root = removeNode(root, value);
        nodeCount--;

        return true;
    }

    private Node removeNode(Node node, int value) {

        if (node == null) {
            return null;
        }

        if (value < node.value) {

            node.left = removeNode(
                    node.left,
                    value
            );

        } else if (value > node.value) {

            node.right = removeNode(
                    node.right,
                    value
            );

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

            Node successorAuditN11 =
                    findMinimum(node.right);

            node.value =
                    successorAuditN11.value;

            node.right =
                    removeNode(
                            node.right,
                            successorAuditN11.value
                    );
        }

        return node;
    }

    private Node findMinimum(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public boolean contains(int value) {

        Node current = root;

        while (current != null) {

            if (value == current.value) {
                return true;
            }

            if (value < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    public int size() {
        return nodeCount;
    }

    public List<Integer> inorder() {

        List<Integer> result =
                new ArrayList<Integer>();

        inorderHelper(root, result);

        return result;
    }

    private void inorderHelper(
            Node node,
            List<Integer> result) {

        if (node == null) {
            return;
        }

        inorderHelper(node.left, result);

        result.add(node.value);

        inorderHelper(node.right, result);
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
            long low,
            long high) {

        if (node == null) {
            return true;
        }

        if (node.value <= low
                || node.value >= high) {
            return false;
        }

        return isValid(
                node.left,
                low,
                node.value
        )
        &&
        isValid(
                node.right,
                node.value,
                high
        );
    }

    public static void main(String[] args) {

        Q11_BstDeletion tree =
                new Q11_BstDeletion();

        int[] values = {
                50, 30, 70,
                20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println(tree.remove(20));
        System.out.println(tree.remove(30));
        System.out.println(tree.remove(50));
        System.out.println(tree.remove(999));

        System.out.println(tree.inorder());
        System.out.println(tree.size());
        System.out.println(tree.isValid());
    }
}