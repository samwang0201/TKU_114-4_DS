public class FolderSizeTree {

    static class FolderNode {
        String name;
        int ownSize;
        FolderNode left;
        FolderNode right;

        public FolderNode(String name, int ownSize) {
            this.name = name;
            this.ownSize = ownSize;
        }
    }

    static int subtreeSize(FolderNode node) {

        if (node == null) {
            return 0;
        }

        int leftSize = subtreeSize(node.left);
        int rightSize = subtreeSize(node.right);

        return node.ownSize + leftSize + rightSize;
    }

    static FolderNode largestSubtree(FolderNode root) {

        if (root == null) {
            return null;
        }

        FolderNode[] best = new FolderNode[1];
        int[] bestSize = new int[1];

        largestSubtreeHelper(root, best, bestSize);

        return best[0];
    }

    static int largestSubtreeHelper(
            FolderNode node,
            FolderNode[] best,
            int[] bestSize) {

        if (node == null) {
            return 0;
        }

        int leftSize =
                largestSubtreeHelper(
                        node.left,
                        best,
                        bestSize
                );

        int rightSize =
                largestSubtreeHelper(
                        node.right,
                        best,
                        bestSize
                );

        int total =
                node.ownSize
                + leftSize
                + rightSize;

        if (best[0] == null || total > bestSize[0]) {
            best[0] = node;
            bestSize[0] = total;
        }

        return total;
    }

    static void printLeafFolders(FolderNode node) {

        if (node == null) {
            return;
        }

        if (node.left == null
                && node.right == null) {

            System.out.println(
                    node.name
                    + "，大小="
                    + node.ownSize
            );

            return;
        }

        printLeafFolders(node.left);
        printLeafFolders(node.right);
    }

    public static void main(String[] args) {

        FolderNode root =
                new FolderNode("Root", 10);

        root.left =
                new FolderNode("Documents", 20);

        root.right =
                new FolderNode("Media", 30);

        root.left.left =
                new FolderNode("Homework", 40);

        root.left.right =
                new FolderNode("Notes", 15);

        root.right.left =
                new FolderNode("Music", 50);

        root.right.right =
                new FolderNode("Videos", 100);

        int total =
                subtreeSize(root);

        FolderNode largest =
                largestSubtree(root);

        System.out.println(
                "總大小：" + total
        );

        System.out.println(
                "最大 subtree："
                + largest.name
        );

        System.out.println(
                "最大 subtree 大小："
                + subtreeSize(largest)
        );

        System.out.println("--------------------");

        System.out.println("Leaf folders：");

        printLeafFolders(root);
    }
}