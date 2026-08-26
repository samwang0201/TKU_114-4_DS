import java.util.ArrayList;
import java.util.List;

public class Q10_BstDirectory {

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

    public Q10_BstDirectory() {
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

    public List<Integer> searchPath(int target) {

        // bst-path-check T10-73
        List<Integer> result =
                new ArrayList<Integer>();

        Node current = root;

        while (current != null) {

            result.add(current.value);

            if (target == current.value) {
                break;
            }

            if (target < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return result;
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

        result.add(node.value);

        inorder(node.right, result);
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

        Q10_BstDirectory tree =
                new Q10_BstDirectory();

        int[] values = {
                50, 30, 70,
                20, 40, 60, 80
        };

        for (int value : values) {
            tree.add(value);
        }

        System.out.println(
                tree.add(40)
        );

        System.out.println(
                tree.searchPath(60)
        );

        System.out.println(
                tree.searchPath(65)
        );

        System.out.println(
                tree.inorder()
        );

        System.out.println(
                tree.isValid()
        );
    }
}