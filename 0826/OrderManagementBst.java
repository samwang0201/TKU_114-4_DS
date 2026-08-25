public class OrderManagementBst {

    static class Order {
        private String orderId;
        private String customer;
        private double amount;
        private String status;

        public Order(
                String orderId,
                String customer,
                double amount,
                String status) {

            this.orderId = orderId;
            this.customer = customer;

            if (amount < 0) {
                amount = 0;
            }

            this.amount = amount;

            if (status == null || status.trim().isEmpty()) {
                this.status = "NEW";
            } else {
                this.status = status;
            }
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomer() {
            return customer;
        }

        public double getAmount() {
            return amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "orderId=" + orderId
                    + ", customer=" + customer
                    + ", amount=" + amount
                    + ", status=" + status;
        }
    }

    static class Node {
        Order order;
        Node left;
        Node right;

        public Node(Order order) {
            this.order = order;
        }
    }

    static class OrderBst {

        private Node root;

        public boolean add(Order order) {

            if (order == null) {
                return false;
            }

            if (order.getOrderId() == null
                    || order.getOrderId().trim().isEmpty()) {
                return false;
            }

            if (find(order.getOrderId()) != null) {
                return false;
            }

            root = add(root, order);

            return true;
        }

        private Node add(Node node, Order order) {

            if (node == null) {
                return new Node(order);
            }

            int result =
                    order.getOrderId().compareTo(
                            node.order.getOrderId()
                    );

            if (result < 0) {
                node.left = add(node.left, order);
            } else if (result > 0) {
                node.right = add(node.right, order);
            }

            return node;
        }

        public Order find(String orderId) {

            if (orderId == null) {
                return null;
            }

            Node current = root;

            while (current != null) {

                int result =
                        orderId.compareTo(
                                current.order.getOrderId()
                        );

                if (result == 0) {
                    return current.order;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean updateStatus(
                String orderId,
                String newStatus) {

            if (newStatus == null
                    || newStatus.trim().isEmpty()) {
                return false;
            }

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            order.setStatus(newStatus);

            return true;
        }

        public boolean cancel(String orderId) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if (order.getStatus().equals("CANCELED")) {
                return false;
            }

            order.setStatus("CANCELED");

            return true;
        }

        public boolean remove(String orderId) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if (!order.getStatus().equals("CANCELED")) {
                return false;
            }

            root = remove(root, orderId);

            return true;
        }

        private Node remove(
                Node node,
                String orderId) {

            if (node == null) {
                return null;
            }

            int result =
                    orderId.compareTo(
                            node.order.getOrderId()
                    );

            if (result < 0) {

                node.left =
                        remove(node.left, orderId);

            } else if (result > 0) {

                node.right =
                        remove(node.right, orderId);

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

                node.order = successor.order;

                node.right =
                        remove(
                                node.right,
                                successor.order.getOrderId()
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

        public void rangeReport(
                String lowId,
                String highId) {

            if (lowId == null || highId == null) {
                return;
            }

            if (lowId.compareTo(highId) > 0) {
                System.out.println("lowId > highId");
                return;
            }

            rangeReport(
                    root,
                    lowId,
                    highId
            );
        }

        private void rangeReport(
                Node node,
                String lowId,
                String highId) {

            if (node == null) {
                return;
            }

            String id =
                    node.order.getOrderId();

            if (id.compareTo(lowId) > 0) {
                rangeReport(
                        node.left,
                        lowId,
                        highId
                );
            }

            if (id.compareTo(lowId) >= 0
                    && id.compareTo(highId) <= 0) {

                System.out.println(node.order);
            }

            if (id.compareTo(highId) < 0) {
                rangeReport(
                        node.right,
                        lowId,
                        highId
                );
            }
        }

        public double totalAmount() {
            return totalAmount(root);
        }

        private double totalAmount(Node node) {

            if (node == null) {
                return 0;
            }

            return node.order.getAmount()
                    + totalAmount(node.left)
                    + totalAmount(node.right);
        }

        public void inorderReport() {
            inorderReport(root);
        }

        private void inorderReport(Node node) {

            if (node == null) {
                return;
            }

            inorderReport(node.left);

            System.out.println(node.order);

            inorderReport(node.right);
        }
    }

    public static void main(String[] args) {

        OrderBst system =
                new OrderBst();

        System.out.println(
                "新增 O003："
                + system.add(
                        new Order(
                                "O003",
                                "Amy",
                                1500,
                                "NEW"
                        )
                )
        );

        System.out.println(
                "新增 O001："
                + system.add(
                        new Order(
                                "O001",
                                "Bob",
                                800,
                                "PAID"
                        )
                )
        );

        System.out.println(
                "新增 O005："
                + system.add(
                        new Order(
                                "O005",
                                "Cindy",
                                2500,
                                "NEW"
                        )
                )
        );

        System.out.println(
                "新增 O002："
                + system.add(
                        new Order(
                                "O002",
                                "David",
                                1200,
                                "PAID"
                        )
                )
        );

        System.out.println(
                "新增 O004："
                + system.add(
                        new Order(
                                "O004",
                                "Eric",
                                -500,
                                "NEW"
                        )
                )
        );

        System.out.println(
                "重複新增 O003："
                + system.add(
                        new Order(
                                "O003",
                                "Kevin",
                                9999,
                                "NEW"
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("有序報表：");
        system.inorderReport();

        System.out.println("--------------------");

        System.out.println(
                "尋找 O002："
                + system.find("O002")
        );

        System.out.println(
                "尋找 O999："
                + system.find("O999")
        );

        System.out.println("--------------------");

        System.out.println(
                "更新 O003 狀態："
                + system.updateStatus(
                        "O003",
                        "PAID"
                )
        );

        System.out.println(
                "更新後 O003："
                + system.find("O003")
        );

        System.out.println("--------------------");

        System.out.println(
                "取消 O002："
                + system.cancel("O002")
        );

        System.out.println(
                "再次取消 O002："
                + system.cancel("O002")
        );

        System.out.println(
                "取消不存在 O999："
                + system.cancel("O999")
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除未取消 O001："
                + system.remove("O001")
        );

        System.out.println(
                "刪除已取消 O002："
                + system.remove("O002")
        );

        System.out.println("--------------------");

        System.out.println(
                "範圍 O001 到 O004："
        );

        system.rangeReport(
                "O001",
                "O004"
        );

        System.out.println("--------------------");

        System.out.println(
                "總金額："
                + system.totalAmount()
        );

        System.out.println("--------------------");

        System.out.println("最後有序報表：");
        system.inorderReport();
    }
}