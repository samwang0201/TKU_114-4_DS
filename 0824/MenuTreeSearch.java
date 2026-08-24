public class MenuTreeSearch {

    static class MenuNode {
        String name;
        MenuNode left;
        MenuNode right;

        public MenuNode(String name) {
            this.name = name;
            left = null;
            right = null;
        }
    }

    static boolean contains(MenuNode node, String target) {

        if (node == null) {
            return false;
        }

        if (node.name.equals(target)) {
            return true;
        }

        return contains(node.left, target)
                || contains(node.right, target);
    }

    static int findDepth(MenuNode node, String target) {
        return findDepth(node, target, 0);
    }

    static int findDepth(
            MenuNode node,
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

    static int countLeaves(MenuNode node) {

        if (node == null) {
            return 0;
        }

        if (node.left == null
                && node.right == null) {
            return 1;
        }

        return countLeaves(node.left)
                + countLeaves(node.right);
    }

    static void preorderDisplay(MenuNode node) {

        if (node == null) {
            return;
        }

        System.out.print(node.name + " ");

        preorderDisplay(node.left);
        preorderDisplay(node.right);
    }

    public static void main(String[] args) {

        MenuNode root =
                new MenuNode("主選單");

        root.left =
                new MenuNode("會員");

        root.right =
                new MenuNode("商品");

        root.left.left =
                new MenuNode("登入");

        root.left.right =
                new MenuNode("註冊");

        root.right.left =
                new MenuNode("搜尋");

        root.right.right =
                new MenuNode("購物車");

        System.out.print("Preorder：");
        preorderDisplay(root);
        System.out.println();

        System.out.println("--------------------");

        System.out.println(
                "contains 搜尋："
                + contains(root, "搜尋")
        );

        System.out.println(
                "contains 結帳："
                + contains(root, "結帳")
        );

        System.out.println("--------------------");

        System.out.println(
                "搜尋 depth："
                + findDepth(root, "搜尋")
        );

        System.out.println(
                "主選單 depth："
                + findDepth(root, "主選單")
        );

        System.out.println(
                "結帳 depth："
                + findDepth(root, "結帳")
        );

        System.out.println("--------------------");

        System.out.println(
                "leaf count："
                + countLeaves(root)
        );
    }
}