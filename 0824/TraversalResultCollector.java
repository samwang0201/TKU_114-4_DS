import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TraversalResultCollector {

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    static List<String> preorder(Node root) {

        List<String> result =
                new ArrayList<String>();

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

        List<String> result =
                new ArrayList<String>();

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

        List<String> result =
                new ArrayList<String>();

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

            Node current =
                    queue.poll();

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

    static void printAll(Node root) {

        System.out.println(
                "Preorder：" + preorder(root)
        );

        System.out.println(
                "Inorder：" + inorder(root)
        );

        System.out.println(
                "Postorder：" + postorder(root)
        );

        System.out.println(
                "Level-order：" + levelOrder(root)
        );
    }

    public static void main(String[] args) {

        System.out.println("Empty Tree：");

        printAll(null);

        System.out.println("--------------------");

        System.out.println("Single-node Tree：");

        Node single =
                new Node("A");

        printAll(single);

        System.out.println("--------------------");

        System.out.println("Left-skewed Tree：");

        Node leftRoot =
                new Node("A");

        leftRoot.left =
                new Node("B");

        leftRoot.left.left =
                new Node("C");

        leftRoot.left.left.left =
                new Node("D");

        printAll(leftRoot);

        System.out.println("--------------------");

        System.out.println("Complete Tree：");

        Node root =
                new Node("A");

        root.left =
                new Node("B");

        root.right =
                new Node("C");

        root.left.left =
                new Node("D");

        root.left.right =
                new Node("E");

        root.right.left =
                new Node("F");

        root.right.right =
                new Node("G");

        printAll(root);
    }
}