import java.util.LinkedList;
import java.util.Queue;

public class LevelOrderByLine {

    static class Node {
        char value;
        Node left;
        Node right;

        public Node(char value) {
            this.value = value;
            left = null;
            right = null;
        }
    }

    static void levelOrder(Node root) {

        if (root == null) {
            System.out.println("Empty tree");
            return;
        }

        Queue<Node> queue =
                new LinkedList<Node>();

        queue.offer(root);

        int level = 1;

        while (!queue.isEmpty()) {

            int count = queue.size();

            System.out.print(
                    "第 " + level + " 層："
            );

            for (int i = 0; i < count; i++) {

                Node current = queue.poll();

                System.out.print(
                        current.value + " "
                );

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();

            System.out.println(
                    "node count：" + count
            );

            level++;
        }
    }

    public static void main(String[] args) {

        Node root = new Node('M');

        root.left = new Node('F');
        root.right = new Node('T');

        root.left.left = new Node('B');
        root.left.right = new Node('H');

        root.right.left = new Node('R');
        root.right.right = new Node('Z');

        System.out.println("一般 Binary Tree：");

        levelOrder(root);

        System.out.println("--------------------");

        System.out.println("Empty Tree：");

        levelOrder(null);
    }
}