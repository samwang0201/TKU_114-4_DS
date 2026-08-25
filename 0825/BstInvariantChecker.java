public class BstInvariantChecker {

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    static boolean isValidBst(Node root) {
        return isValidBst(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
        );
    }

    static boolean isValidBst(
            Node node,
            long min,
            long max) {

        if (node == null) {
            return true;
        }

        if (node.key <= min || node.key >= max) {
            return false;
        }

        return isValidBst(
                node.left,
                min,
                node.key
        )
        &&
        isValidBst(
                node.right,
                node.key,
                max
        );
    }

    public static void main(String[] args) {

        Node valid = new Node(50);

        valid.left = new Node(30);
        valid.right = new Node(70);

        valid.left.left = new Node(20);
        valid.left.right = new Node(40);

        valid.right.left = new Node(60);
        valid.right.right = new Node(80);

        System.out.println(
                "Valid Tree："
                + isValidBst(valid)
        );

        System.out.println("--------------------");

        Node invalid1 = new Node(50);

        invalid1.left = new Node(30);
        invalid1.right = new Node(70);

        invalid1.left.right = new Node(60);

        System.out.println(
                "Invalid Tree 1："
                + isValidBst(invalid1)
        );

        System.out.println("--------------------");

        Node invalid2 = new Node(50);

        invalid2.left = new Node(30);
        invalid2.right = new Node(70);

        invalid2.right.left = new Node(40);

        System.out.println(
                "Invalid Tree 2："
                + isValidBst(invalid2)
        );

        System.out.println("--------------------");

        Node invalid3 = new Node(50);

        invalid3.left = new Node(30);
        invalid3.right = new Node(70);

        invalid3.left.left = new Node(20);
        invalid3.left.left.right = new Node(55);

        System.out.println(
                "Invalid Tree 3："
                + isValidBst(invalid3)
        );
    }
}