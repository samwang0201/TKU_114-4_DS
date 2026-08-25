public class DirectoryTreeReport {

    static class FileNode {
        String name;
        boolean isFile;
        int ownSize;
        FileNode left;
        FileNode right;

        public FileNode(String name, boolean isFile, int ownSize) {
            this.name = name;
            this.isFile = isFile;

            if (ownSize < 0) {
                ownSize = 0;
            }

            this.ownSize = ownSize;
        }
    }

    static int totalSize(FileNode node) {

        if (node == null) {
            return 0;
        }

        int leftSize = totalSize(node.left);
        int rightSize = totalSize(node.right);

        int total = node.ownSize
                + leftSize
                + rightSize;

        if (!node.isFile) {
            System.out.println(
                    "目錄 " + node.name
                    + " 總容量：" + total
            );
        }

        return total;
    }

    static int countNodes(FileNode node) {

        if (node == null) {
            return 0;
        }

        return 1
                + countNodes(node.left)
                + countNodes(node.right);
    }

    static int countFiles(FileNode node) {

        if (node == null) {
            return 0;
        }

        int current = 0;

        if (node.isFile) {
            current = 1;
        }

        return current
                + countFiles(node.left)
                + countFiles(node.right);
    }

    static int countDirectories(FileNode node) {

        if (node == null) {
            return 0;
        }

        int current = 0;

        if (!node.isFile) {
            current = 1;
        }

        return current
                + countDirectories(node.left)
                + countDirectories(node.right);
    }

    static int height(FileNode node) {

        if (node == null) {
            return 0;
        }

        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        }

        return rightHeight + 1;
    }

    static FileNode largestFile(FileNode node) {

        if (node == null) {
            return null;
        }

        FileNode best = null;

        if (node.isFile) {
            best = node;
        }

        FileNode leftBest =
                largestFile(node.left);

        FileNode rightBest =
                largestFile(node.right);

        if (leftBest != null) {

            if (best == null
                    || leftBest.ownSize > best.ownSize) {
                best = leftBest;
            }
        }

        if (rightBest != null) {

            if (best == null
                    || rightBest.ownSize > best.ownSize) {
                best = rightBest;
            }
        }

        return best;
    }

    public static void main(String[] args) {

        FileNode root =
                new FileNode("Root", false, 0);

        root.left =
                new FileNode("Documents", false, 0);

        root.right =
                new FileNode("Media", false, 0);

        root.left.left =
                new FileNode("report.pdf", true, 120);

        root.left.right =
                new FileNode("notes.txt", true, 30);

        root.right.left =
                new FileNode("music.mp3", true, 200);

        root.right.right =
                new FileNode("Videos", false, 0);

        root.right.right.left =
                new FileNode("movie.mp4", true, 500);

        root.right.right.right =
                new FileNode("clip.mp4", true, 150);

        System.out.println("目錄容量報告：");

        int total =
                totalSize(root);

        System.out.println("--------------------");

        System.out.println(
                "總容量：" + total
        );

        System.out.println(
                "總節點：" + countNodes(root)
        );

        System.out.println(
                "檔案數：" + countFiles(root)
        );

        System.out.println(
                "目錄數：" + countDirectories(root)
        );

        System.out.println(
                "高度：" + height(root)
        );

        FileNode largest =
                largestFile(root);

        if (largest == null) {

            System.out.println("最大檔案：無");

        } else {

            System.out.println(
                    "最大檔案："
                    + largest.name
                    + "，大小="
                    + largest.ownSize
            );
        }
    }
}