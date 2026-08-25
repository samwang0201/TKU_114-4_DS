public class MemberBstIndex {

    static class Member {
        private String memberId;
        private String name;
        private String email;

        public Member(String memberId, String name, String email) {
            this.memberId = memberId;
            this.name = name;
            this.email = email;
        }

        public String getMemberId() {
            return memberId;
        }

        public String getName() {
            return name;
        }

        public String getEmail() {
            return email;
        }

        public boolean setEmail(String email) {

            if (email == null || email.trim().isEmpty()) {
                return false;
            }

            this.email = email;
            return true;
        }

        @Override
        public String toString() {
            return "memberId=" + memberId
                    + ", name=" + name
                    + ", email=" + email;
        }
    }

    static class Node {
        Member member;
        Node left;
        Node right;

        public Node(Member member) {
            this.member = member;
        }
    }

    static class MemberBst {

        private Node root;

        public boolean add(Member member) {

            if (member == null) {
                return false;
            }

            if (member.getMemberId() == null
                    || member.getMemberId().trim().isEmpty()) {
                return false;
            }

            if (member.getEmail() == null
                    || member.getEmail().trim().isEmpty()) {
                return false;
            }

            if (find(member.getMemberId()) != null) {
                return false;
            }

            root = add(root, member);

            return true;
        }

        private Node add(Node node, Member member) {

            if (node == null) {
                return new Node(member);
            }

            int result =
                    member.getMemberId()
                            .compareTo(node.member.getMemberId());

            if (result < 0) {
                node.left = add(node.left, member);
            } else if (result > 0) {
                node.right = add(node.right, member);
            }

            return node;
        }

        public Member find(String memberId) {

            if (memberId == null) {
                return null;
            }

            Node current = root;

            while (current != null) {

                int result =
                        memberId.compareTo(
                                current.member.getMemberId()
                        );

                if (result == 0) {
                    return current.member;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean updateEmail(
                String memberId,
                String newEmail) {

            if (newEmail == null
                    || newEmail.trim().isEmpty()) {
                return false;
            }

            Member member = find(memberId);

            if (member == null) {
                return false;
            }

            return member.setEmail(newEmail);
        }

        public boolean delete(String memberId) {

            if (find(memberId) == null) {
                return false;
            }

            root = delete(root, memberId);

            return true;
        }

        private Node delete(Node node, String memberId) {

            if (node == null) {
                return null;
            }

            int result =
                    memberId.compareTo(
                            node.member.getMemberId()
                    );

            if (result < 0) {

                node.left =
                        delete(node.left, memberId);

            } else if (result > 0) {

                node.right =
                        delete(node.right, memberId);

            } else {

                if (node.left == null
                        && node.right == null) {
                    return null;
                }

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.member = successor.member;

                node.right =
                        delete(
                                node.right,
                                successor.member.getMemberId()
                        );
            }

            return node;
        }

        private Node findMin(Node node) {

            Node current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        public void inorderReport() {
            inorderReport(root);
        }

        private void inorderReport(Node node) {

            if (node == null) {
                return;
            }

            inorderReport(node.left);

            System.out.println(node.member);

            inorderReport(node.right);
        }
    }

    public static void main(String[] args) {

        MemberBst tree =
                new MemberBst();

        System.out.println(
                "新增 M003："
                + tree.add(
                        new Member(
                                "M003",
                                "Amy",
                                "amy@test.com"
                        )
                )
        );

        System.out.println(
                "新增 M001："
                + tree.add(
                        new Member(
                                "M001",
                                "Bob",
                                "bob@test.com"
                        )
                )
        );

        System.out.println(
                "新增 M005："
                + tree.add(
                        new Member(
                                "M005",
                                "Cindy",
                                "cindy@test.com"
                        )
                )
        );

        System.out.println(
                "新增 M002："
                + tree.add(
                        new Member(
                                "M002",
                                "David",
                                "david@test.com"
                        )
                )
        );

        System.out.println(
                "新增 M004："
                + tree.add(
                        new Member(
                                "M004",
                                "Eric",
                                "eric@test.com"
                        )
                )
        );

        System.out.println(
                "重複新增 M003："
                + tree.add(
                        new Member(
                                "M003",
                                "Kevin",
                                "kevin@test.com"
                        )
                )
        );

        System.out.println(
                "空白 Email："
                + tree.add(
                        new Member(
                                "M006",
                                "Fiona",
                                ""
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("有序報告：");
        tree.inorderReport();

        System.out.println("--------------------");

        System.out.println(
                "尋找 M002："
                + tree.find("M002")
        );

        System.out.println(
                "尋找 M999："
                + tree.find("M999")
        );

        System.out.println("--------------------");

        System.out.println(
                "更新 M002 Email："
                + tree.updateEmail(
                        "M002",
                        "newdavid@test.com"
                )
        );

        System.out.println(
                "更新後 M002："
                + tree.find("M002")
        );

        System.out.println(
                "更新成空白 Email："
                + tree.updateEmail(
                        "M002",
                        ""
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 M003："
                + tree.delete("M003")
        );

        System.out.println(
                "刪除 M999："
                + tree.delete("M999")
        );

        System.out.println("--------------------");

        System.out.println("刪除後有序報告：");
        tree.inorderReport();
    }
}