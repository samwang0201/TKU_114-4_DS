public class BinaryTreeStatistics {

    static class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class BinaryTree {
        Node root;

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

        public int sum() {
            return sum(root);
        }

        private int sum(Node node) {

            if (node == null) {
                return 0;
            }

            return node.value
                    + sum(node.left)
                    + sum(node.right);
        }

        public int maximum() {

            if (root == null) {
                throw new IllegalStateException(
                        "empty tree 沒有最大值"
                );
            }

            return maximum(root);
        }

        private int maximum(Node node) {

            int max = node.value;

            if (node.left != null) {
                int leftMax = maximum(node.left);

                if (leftMax > max) {
                    max = leftMax;
                }
            }

            if (node.right != null) {
                int rightMax = maximum(node.right);

                if (rightMax > max) {
                    max = rightMax;
                }
            }

            return max;
        }

        public int leafCount() {
            return leafCount(root);
        }

        private int leafCount(Node node) {

            if (node == null) {
                return 0;
            }

            if (node.left == null
                    && node.right == null) {
                return 1;
            }

            return leafCount(node.left)
                    + leafCount(node.right);
        }

        public int height() {
            return height(root);
        }

        private int height(Node node) {

            if (node == null) {
                return 0;
            }

            int leftHeight =
                    height(node.left);

            int rightHeight =
                    height(node.right);

            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            }

            return rightHeight + 1;
        }

        public boolean contains(int target) {
            return contains(root, target);
        }

        private boolean contains(
                Node node,
                int target) {

            if (node == null) {
                return false;
            }

            if (node.value == target) {
                return true;
            }

            return contains(node.left, target)
                    || contains(node.right, target);
        }
    }

    public static void main(String[] args) {

        BinaryTree tree =
                new BinaryTree();

        tree.root = new Node(10);

        tree.root.left =
                new Node(5);

        tree.root.right =
                new Node(20);

        tree.root.left.left =
                new Node(3);

        tree.root.left.right =
                new Node(7);

        tree.root.right.left =
                new Node(15);

        tree.root.right.right =
                new Node(25);

        System.out.println(
                "size：" + tree.size()
        );

        System.out.println(
                "sum：" + tree.sum()
        );

        System.out.println(
                "maximum：" + tree.maximum()
        );

        System.out.println(
                "leaf count：" + tree.leafCount()
        );

        System.out.println(
                "height：" + tree.height()
        );

        System.out.println(
                "contains 15："
                + tree.contains(15)
        );

        System.out.println(
                "contains 99："
                + tree.contains(99)
        );

        System.out.println("--------------------");

        BinaryTree emptyTree =
                new BinaryTree();

        System.out.println(
                "empty size："
                + emptyTree.size()
        );

        System.out.println(
                "empty sum："
                + emptyTree.sum()
        );

        System.out.println(
                "empty leaf count："
                + emptyTree.leafCount()
        );

        System.out.println(
                "empty height："
                + emptyTree.height()
        );

        System.out.println(
                "empty contains 10："
                + emptyTree.contains(10)
        );

        try {
            System.out.println(
                    "empty maximum："
                    + emptyTree.maximum()
            );
        } catch (IllegalStateException e) {
            System.out.println(
                    "empty maximum："
                    + e.getMessage()
            );
        }
    }
}