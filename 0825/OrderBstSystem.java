public class OrderBstSystem {

    static class Order {
        private String orderId;
        private String customerName;
        private double amount;
        private boolean cancelled;

        public Order(String orderId, String customerName, double amount) {
            this.orderId = orderId;
            this.customerName = customerName;

            if (amount < 0) {
                amount = 0;
            }

            this.amount = amount;
            this.cancelled = false;
        }

        public String getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public double getAmount() {
            return amount;
        }

        public boolean isCancelled() {
            return cancelled;
        }

        public void setAmount(double amount) {
            if (amount < 0) {
                amount = 0;
            }

            this.amount = amount;
        }

        public void cancel() {
            cancelled = true;
        }

        @Override
        public String toString() {
            return "orderId=" + orderId
                    + ", customer=" + customerName
                    + ", amount=" + amount
                    + ", status="
                    + (cancelled ? "已取消" : "有效");
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
                    order.getOrderId()
                            .compareTo(node.order.getOrderId());

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

        public boolean cancel(String orderId) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if (order.isCancelled()) {
                return false;
            }

            order.cancel();

            return true;
        }

        public boolean updateAmount(
                String orderId,
                double amount) {

            Order order = find(orderId);

            if (order == null) {
                return false;
            }

            if (order.isCancelled()) {
                return false;
            }

            order.setAmount(amount);

            return true;
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

            rangeReport(root, lowId, highId);
        }

        private void rangeReport(
                Node node,
                String lowId,
                String highId) {

            if (node == null) {
                return;
            }

            String id = node.order.getOrderId();

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

        public void summary() {

            int totalCount = count(root);
            int cancelledCount = countCancelled(root);
            int activeCount = totalCount - cancelledCount;
            double activeAmount = sumActiveAmount(root);

            System.out.println("總訂單數：" + totalCount);
            System.out.println("有效訂單數：" + activeCount);
            System.out.println("取消訂單數：" + cancelledCount);
            System.out.println("有效訂單總金額：" + activeAmount);
        }

        private int count(Node node) {

            if (node == null) {
                return 0;
            }

            return 1
                    + count(node.left)
                    + count(node.right);
        }

        private int countCancelled(Node node) {

            if (node == null) {
                return 0;
            }

            int current = 0;

            if (node.order.isCancelled()) {
                current = 1;
            }

            return current
                    + countCancelled(node.left)
                    + countCancelled(node.right);
        }

        private double sumActiveAmount(Node node) {

            if (node == null) {
                return 0;
            }

            double current = 0;

            if (!node.order.isCancelled()) {
                current = node.order.getAmount();
            }

            return current
                    + sumActiveAmount(node.left)
                    + sumActiveAmount(node.right);
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
                                1500
                        )
                )
        );

        System.out.println(
                "新增 O001："
                + system.add(
                        new Order(
                                "O001",
                                "Bob",
                                800
                        )
                )
        );

        System.out.println(
                "新增 O005："
                + system.add(
                        new Order(
                                "O005",
                                "Cindy",
                                2500
                        )
                )
        );

        System.out.println(
                "新增 O002："
                + system.add(
                        new Order(
                                "O002",
                                "David",
                                1200
                        )
                )
        );

        System.out.println(
                "新增 O004："
                + system.add(
                        new Order(
                                "O004",
                                "Eric",
                                1800
                        )
                )
        );

        System.out.println(
                "重複新增 O003："
                + system.add(
                        new Order(
                                "O003",
                                "Kevin",
                                9999
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("Inorder Report：");
        system.inorderReport();

        System.out.println("--------------------");

        System.out.println(
                "查詢 O002："
                + system.find("O002")
        );

        System.out.println(
                "查詢 O999："
                + system.find("O999")
        );

        System.out.println("--------------------");

        System.out.println(
                "修改 O002 金額："
                + system.updateAmount(
                        "O002",
                        2000
                )
        );

        System.out.println(
                "修改後 O002："
                + system.find("O002")
        );

        System.out.println("--------------------");

        System.out.println(
                "取消 O004："
                + system.cancel("O004")
        );

        System.out.println(
                "再次取消 O004："
                + system.cancel("O004")
        );

        System.out.println(
                "取消不存在 O999："
                + system.cancel("O999")
        );

        System.out.println("--------------------");

        System.out.println("範圍 O002 到 O004：");
        system.rangeReport("O002", "O004");

        System.out.println("--------------------");

        System.out.println("Summary：");
        system.summary();
    }
}