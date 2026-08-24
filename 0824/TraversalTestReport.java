import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalTestReport {

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    static List<String> preorder(Node root) {
        List<String> result = new ArrayList<String>();
        preorderHelper(root, result);
        return result;
    }

    static void preorderHelper(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        result.add(node.value);

        preorderHelper(node.left, result);
        preorderHelper(node.right, result);
    }

    static List<String> inorder(Node root) {
        List<String> result = new ArrayList<String>();
        inorderHelper(root, result);
        return result;
    }

    static void inorderHelper(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        inorderHelper(node.left, result);

        result.add(node.value);

        inorderHelper(node.right, result);
    }

    static List<String> postorder(Node root) {
        List<String> result = new ArrayList<String>();
        postorderHelper(root, result);
        return result;
    }

    static void postorderHelper(
            Node node,
            List<String> result) {

        if (node == null) {
            return;
        }

        postorderHelper(node.left, result);
        postorderHelper(node.right, result);

        result.add(node.value);
    }

    static List<String> levelOrder(Node root) {

        List<String> result =
                new ArrayList<String>();

        if (root == null) {
            return result;
        }

        Queue<Node> queue =
                new LinkedList<Node>();

        queue.offer(root);

        while (!queue.isEmpty()) {

            Node current = queue.poll();

            result.add(current.value);

            if (current.left != null) {
                queue.offer(current.left);
            }

            if (current.right != null) {
                queue.offer(current.right);
            }
        }

        return result;
    }

    static List<String> makeList(String... values) {

        List<String> result =
                new ArrayList<String>();

        for (int i = 0; i < values.length; i++) {
            result.add(values[i]);
        }

        return result;
    }

    static void printResult(
            String name,
            List<String> expected,
            List<String> actual) {

        System.out.println(name);

        System.out.println(
                "預期：" + expected
        );

        System.out.println(
                "實際：" + actual
        );

        System.out.println(
                "是否相同："
                + expected.equals(actual)
        );
    }

    static void testTree(
            String title,
            Node root,
            List<String> preExpected,
            List<String> inExpected,
            List<String> postExpected,
            List<String> levelExpected) {

        System.out.println("==========");
        System.out.println(title);
        System.out.println("==========");

        printResult(
                "Preorder",
                preExpected,
                preorder(root)
        );

        System.out.println("--------------------");

        printResult(
                "Inorder",
                inExpected,
                inorder(root)
        );

        System.out.println("--------------------");

        printResult(
                "Postorder",
                postExpected,
                postorder(root)
        );

        System.out.println("--------------------");

        printResult(
                "Level-order",
                levelExpected,
                levelOrder(root)
        );

        System.out.println();
    }

    public static void main(String[] args) {

        testTree(
                "Empty Tree",
                null,
                makeList(),
                makeList(),
                makeList(),
                makeList()
        );

        Node single = new Node("A");

        testTree(
                "Single-node Tree",
                single,
                makeList("A"),
                makeList("A"),
                makeList("A"),
                makeList("A")
        );

        Node onlyLeft = new Node("A");

        onlyLeft.left = new Node("B");
        onlyLeft.left.left = new Node("C");

        testTree(
                "Only-left Tree",
                onlyLeft,
                makeList("A", "B", "C"),
                makeList("C", "B", "A"),
                makeList("C", "B", "A"),
                makeList("A", "B", "C")
        );

        Node onlyRight = new Node("A");

        onlyRight.right = new Node("B");
        onlyRight.right.right = new Node("C");

        testTree(
                "Only-right Tree",
                onlyRight,
                makeList("A", "B", "C"),
                makeList("A", "B", "C"),
                makeList("C", "B", "A"),
                makeList("A", "B", "C")
        );

        Node complete = new Node("A");

        complete.left = new Node("B");
        complete.right = new Node("C");

        complete.left.left = new Node("D");
        complete.left.right = new Node("E");

        complete.right.left = new Node("F");
        complete.right.right = new Node("G");

        testTree(
                "Complete Tree",
                complete,
                makeList("A", "B", "D", "E", "C", "F", "G"),
                makeList("D", "B", "E", "A", "F", "C", "G"),
                makeList("D", "E", "B", "F", "G", "C", "A"),
                makeList("A", "B", "C", "D", "E", "F", "G")
        );

        Node irregular = new Node("A");

        irregular.left = new Node("B");
        irregular.right = new Node("C");

        irregular.left.right = new Node("D");
        irregular.right.left = new Node("E");

        irregular.right.left.right = new Node("F");

        testTree(
                "Irregular Tree",
                irregular,
                makeList("A", "B", "D", "C", "E", "F"),
                makeList("B", "D", "A", "E", "F", "C"),
                makeList("D", "B", "F", "E", "C", "A"),
                makeList("A", "B", "C", "D", "E", "F")
        );
    }
}