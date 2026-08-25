public class TraversalSelector {

    static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    static String preorder(Node node) {

        if (node == null) {
            return "";
        }

        String left = preorder(node.left);
        String right = preorder(node.right);

        String result = node.value;

        if (!left.isEmpty()) {
            result = result + " " + left;
        }

        if (!right.isEmpty()) {
            result = result + " " + right;
        }

        return result;
    }

    static String inorder(Node node) {

        if (node == null) {
            return "";
        }

        if (node.left == null && node.right == null) {
            return node.value;
        }

        return "("
                + inorder(node.left)
                + " " + node.value + " "
                + inorder(node.right)
                + ")";
    }

    static String postorder(Node node) {

        if (node == null) {
            return "";
        }

        String left = postorder(node.left);
        String right = postorder(node.right);

        String result = "";

        if (!left.isEmpty()) {
            result = left;
        }

        if (!right.isEmpty()) {

            if (!result.isEmpty()) {
                result = result + " ";
            }

            result = result + right;
        }

        if (!result.isEmpty()) {
            result = result + " ";
        }

        result = result + node.value;

        return result;
    }

    public static void main(String[] args) {

        Node root = new Node("*");

        root.left = new Node("+");
        root.right = new Node("-");

        root.left.left = new Node("A");
        root.left.right = new Node("B");

        root.right.left = new Node("C");
        root.right.right = new Node("D");

        System.out.println(
                "前綴 Prefix："
                + preorder(root)
        );

        System.out.println(
                "中綴 Infix："
                + inorder(root)
        );

        System.out.println(
                "後綴 Postfix："
                + postorder(root)
        );
    }
}