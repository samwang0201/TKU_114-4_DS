public class BstDuplicateCounter {

    static class Node {
        int key;
        int count;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
            this.count = 1;
            this.left = null;
            this.right = null;
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

            } else {

                node.count++;
            }

            return node;
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

            System.out.print(
                    node.key + "(" + node.count + ") "
            );

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        BinarySearchTree tree =
                new BinarySearchTree();

        tree.insert(50);
        tree.insert(30);
        tree.insert(70);
        tree.insert(20);
        tree.insert(40);
        tree.insert(60);
        tree.insert(80);

        tree.insert(30);
        tree.insert(30);

        tree.insert(70);

        tree.insert(20);
        tree.insert(20);
        tree.insert(20);

        System.out.println("Inorder：");
        tree.inorder();
    }
}