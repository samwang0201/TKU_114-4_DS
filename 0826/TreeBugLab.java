public class TreeBugLab {

    static class Node {
        int key;
        Node left;
        Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    static boolean wrongSearch(Node root, int target) {

        Node current = root;

        while (current != null) {

            if (current.key == target) {
                return true;
            }

            if (target < current.key) {
                current = current.right;
            } else {
                current = current.left;
            }
        }

        return false;
    }

    static boolean correctSearch(Node root, int target) {

        Node current = root;

        while (current != null) {

            if (current.key == target) {
                return true;
            }

            if (target < current.key) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return false;
    }

    static void wrongInorder(Node node) {

        if (node == null) {
            return;
        }

        System.out.print(node.key + " ");
        wrongInorder(node.left);
        wrongInorder(node.right);
    }

    static void correctInorder(Node node) {

        if (node == null) {
            return;
        }

        correctInorder(node.left);

        System.out.print(node.key + " ");

        correctInorder(node.right);
    }

    static Node wrongDelete(Node node, int key) {

        if (node == null) {
            return null;
        }

        if (key < node.key) {

            node.left = wrongDelete(node.left, key);

        } else if (key > node.key) {

            node.right = wrongDelete(node.right, key);

        } else {

            if (node.left == null) {
                return null;
            }

            if (node.right == null) {
                return null;
            }
        }

        return node;
    }

    static Node correctDelete(Node node, int key) {

        if (node == null) {
            return null;
        }

        if (key < node.key) {

            node.left = correctDelete(node.left, key);

        } else if (key > node.key) {

            node.right = correctDelete(node.right, key);

        } else {

            if (node.left == null) {
                return node.right;
            }

            if (node.right == null) {
                return node.left;
            }

            Node successor = findMin(node.right);

            node.key = successor.key;

            node.right =
                    correctDelete(
                            node.right,
                            successor.key
                    );
        }

        return node;
    }

    static Node findMin(Node node) {

        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    static boolean wrongValidation(Node node) {

        if (node == null) {
            return true;
        }

        if (node.left != null
                && node.left.key >= node.key) {
            return false;
        }

        if (node.right != null
                && node.right.key <= node.key) {
            return false;
        }

        return wrongValidation(node.left)
                && wrongValidation(node.right);
    }

    static boolean correctValidation(Node root) {

        return correctValidation(
                root,
                Long.MIN_VALUE,
                Long.MAX_VALUE
        );
    }

    static boolean correctValidation(
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

        return correctValidation(
                node.left,
                min,
                node.key
        )
        &&
        correctValidation(
                node.right,
                node.key,
                max
        );
    }

    public static void main(String[] args) {

        System.out.println("===== Bug 1：搜尋方向錯誤 =====");

        Node searchTree = new Node(50);
        searchTree.left = new Node(30);

        System.out.println(
                "錯誤搜尋 30："
                + wrongSearch(searchTree, 30)
        );

        System.out.println(
                "正確搜尋 30："
                + correctSearch(searchTree, 30)
        );

        System.out.println("--------------------");

        System.out.println("===== Bug 2：Inorder 順序錯誤 =====");

        Node inorderTree = new Node(20);
        inorderTree.left = new Node(10);
        inorderTree.right = new Node(30);

        System.out.print("錯誤 inorder：");
        wrongInorder(inorderTree);
        System.out.println();

        System.out.print("正確 inorder：");
        correctInorder(inorderTree);
        System.out.println();

        System.out.println("--------------------");

        System.out.println("===== Bug 3：刪除遺失子節點 =====");

        Node deleteTree1 = new Node(50);
        deleteTree1.right = new Node(70);

        deleteTree1 =
                wrongDelete(deleteTree1, 50);

        System.out.print("錯誤刪除後：");
        correctInorder(deleteTree1);
        System.out.println();

        Node deleteTree2 = new Node(50);
        deleteTree2.right = new Node(70);

        deleteTree2 =
                correctDelete(deleteTree2, 50);

        System.out.print("正確刪除後：");
        correctInorder(deleteTree2);
        System.out.println();

        System.out.println("--------------------");

        System.out.println("===== Bug 4：只檢查直接子節點 =====");

        Node invalidTree = new Node(50);

        invalidTree.left = new Node(30);
        invalidTree.right = new Node(70);

        invalidTree.left.right = new Node(60);

        System.out.println(
                "錯誤 validation："
                + wrongValidation(invalidTree)
        );

        System.out.println(
                "正確 validation："
                + correctValidation(invalidTree)
        );
    }
}