import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrganizationTreeReport {

    static class OrgNode {
        String name;
        OrgNode left;
        OrgNode right;

        public OrgNode(String name) {
            this.name = name;
        }
    }

    static OrgNode findParent(OrgNode root, String target) {

        if (root == null) {
            return null;
        }

        if (root.left != null
                && root.left.name.equals(target)) {
            return root;
        }

        if (root.right != null
                && root.right.name.equals(target)) {
            return root;
        }

        OrgNode leftResult =
                findParent(root.left, target);

        if (leftResult != null) {
            return leftResult;
        }

        return findParent(root.right, target);
    }

    static int findDepth(OrgNode root, String target) {
        return findDepth(root, target, 0);
    }

    static int findDepth(
            OrgNode node,
            String target,
            int depth) {

        if (node == null) {
            return -1;
        }

        if (node.name.equals(target)) {
            return depth;
        }

        int leftResult =
                findDepth(node.left, target, depth + 1);

        if (leftResult != -1) {
            return leftResult;
        }

        return findDepth(
                node.right,
                target,
                depth + 1
        );
    }

    static List<String> pathFromRoot(
            OrgNode root,
            String target) {

        List<String> path =
                new ArrayList<String>();

        boolean found =
                pathHelper(root, target, path);

        if (!found) {
            path.clear();
        }

        return path;
    }

    static boolean pathHelper(
            OrgNode node,
            String target,
            List<String> path) {

        if (node == null) {
            return false;
        }

        path.add(node.name);

        if (node.name.equals(target)) {
            return true;
        }

        if (pathHelper(node.left, target, path)) {
            return true;
        }

        if (pathHelper(node.right, target, path)) {
            return true;
        }

        path.remove(path.size() - 1);

        return false;
    }

    static void printByLevel(OrgNode root) {

        if (root == null) {
            System.out.println("Empty organization");
            return;
        }

        Queue<OrgNode> queue =
                new LinkedList<OrgNode>();

        queue.offer(root);

        int level = 0;

        while (!queue.isEmpty()) {

            int count = queue.size();

            System.out.print(
                    "第 " + level + " 層："
            );

            for (int i = 0; i < count; i++) {

                OrgNode current =
                        queue.poll();

                System.out.print(
                        current.name + " "
                );

                if (current.left != null) {
                    queue.offer(current.left);
                }

                if (current.right != null) {
                    queue.offer(current.right);
                }
            }

            System.out.println();

            level++;
        }
    }

    public static void main(String[] args) {

        OrgNode root =
                new OrgNode("總公司");

        root.left =
                new OrgNode("資訊部");

        root.right =
                new OrgNode("業務部");

        root.left.left =
                new OrgNode("開發組");

        root.left.right =
                new OrgNode("維運組");

        root.right.left =
                new OrgNode("北區業務");

        root.right.right =
                new OrgNode("南區業務");

        System.out.println("逐層輸出：");
        printByLevel(root);

        System.out.println("--------------------");

        OrgNode parent =
                findParent(root, "維運組");

        if (parent == null) {
            System.out.println("維運組 parent：找不到");
        } else {
            System.out.println(
                    "維運組 parent：" + parent.name
            );
        }

        System.out.println(
                "南區業務 depth："
                + findDepth(root, "南區業務")
        );

        System.out.println(
                "開發組 path："
                + pathFromRoot(root, "開發組")
        );

        System.out.println("--------------------");

        OrgNode notFound =
                findParent(root, "財務部");

        if (notFound == null) {
            System.out.println("財務部 parent：找不到");
        }

        System.out.println(
                "財務部 depth："
                + findDepth(root, "財務部")
        );

        System.out.println(
                "財務部 path："
                + pathFromRoot(root, "財務部")
        );

        System.out.println("--------------------");

        System.out.println("Empty Tree：");
        printByLevel(null);
    }
}