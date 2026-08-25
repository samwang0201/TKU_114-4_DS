public class ProductInventoryBst {

    static class Product {
        private String id;
        private String name;
        private int stock;

        public Product(String id, String name, int stock) {
            this.id = id;
            this.name = name;

            if (stock < 0) {
                stock = 0;
            }

            this.stock = stock;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getStock() {
            return stock;
        }

        public void addStock(int amount) {
            if (amount > 0) {
                stock = stock + amount;
            }
        }

        public boolean reduceStock(int amount) {

            if (amount <= 0) {
                return false;
            }

            if (amount > stock) {
                return false;
            }

            stock = stock - amount;

            return true;
        }

        @Override
        public String toString() {
            return "id=" + id
                    + ", name=" + name
                    + ", stock=" + stock;
        }
    }

    static class Node {
        Product product;
        Node left;
        Node right;

        public Node(Product product) {
            this.product = product;
        }
    }

    static class InventoryBst {

        private Node root;

        public boolean insert(Product product) {

            if (product == null) {
                return false;
            }

            if (search(product.getId()) != null) {
                return false;
            }

            root = insert(root, product);

            return true;
        }

        private Node insert(Node node, Product product) {

            if (node == null) {
                return new Node(product);
            }

            int result =
                    product.getId().compareTo(
                            node.product.getId()
                    );

            if (result < 0) {
                node.left = insert(node.left, product);
            } else if (result > 0) {
                node.right = insert(node.right, product);
            }

            return node;
        }

        public Product search(String id) {

            Node current = root;

            while (current != null) {

                int result =
                        id.compareTo(
                                current.product.getId()
                        );

                if (result == 0) {
                    return current.product;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean restock(String id, int amount) {

            Product product = search(id);

            if (product == null) {
                return false;
            }

            if (amount <= 0) {
                return false;
            }

            product.addStock(amount);

            return true;
        }

        public boolean reduceStock(String id, int amount) {

            Product product = search(id);

            if (product == null) {
                return false;
            }

            return product.reduceStock(amount);
        }

        public boolean delete(String id) {

            if (search(id) == null) {
                return false;
            }

            root = delete(root, id);

            return true;
        }

        private Node delete(Node node, String id) {

            if (node == null) {
                return null;
            }

            int result =
                    id.compareTo(
                            node.product.getId()
                    );

            if (result < 0) {

                node.left = delete(node.left, id);

            } else if (result > 0) {

                node.right = delete(node.right, id);

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

                node.product = successor.product;

                node.right =
                        delete(
                                node.right,
                                successor.product.getId()
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

            System.out.println(node.product);

            inorderReport(node.right);
        }
    }

    public static void main(String[] args) {

        InventoryBst inventory =
                new InventoryBst();

        System.out.println(
                "新增 P003："
                + inventory.insert(
                        new Product("P003", "Keyboard", 20)
                )
        );

        System.out.println(
                "新增 P001："
                + inventory.insert(
                        new Product("P001", "Mouse", 30)
                )
        );

        System.out.println(
                "新增 P005："
                + inventory.insert(
                        new Product("P005", "Monitor", 10)
                )
        );

        System.out.println(
                "新增 P002："
                + inventory.insert(
                        new Product("P002", "Webcam", 15)
                )
        );

        System.out.println(
                "新增 P004："
                + inventory.insert(
                        new Product("P004", "Speaker", 8)
                )
        );

        System.out.println(
                "重複新增 P003："
                + inventory.insert(
                        new Product("P003", "Other", 100)
                )
        );

        System.out.println("--------------------");

        System.out.println("Inorder Report：");
        inventory.inorderReport();

        System.out.println("--------------------");

        System.out.println(
                "查詢 P002："
                + inventory.search("P002")
        );

        System.out.println(
                "查詢 P999："
                + inventory.search("P999")
        );

        System.out.println("--------------------");

        System.out.println(
                "P002 補貨 10："
                + inventory.restock("P002", 10)
        );

        System.out.println(
                "P002 扣庫存 5："
                + inventory.reduceStock("P002", 5)
        );

        System.out.println(
                "P002 扣庫存 100："
                + inventory.reduceStock("P002", 100)
        );

        System.out.println(
                "修改後 P002："
                + inventory.search("P002")
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 P003："
                + inventory.delete("P003")
        );

        System.out.println(
                "刪除 P999："
                + inventory.delete("P999")
        );

        System.out.println("--------------------");

        System.out.println("刪除後 Inorder Report：");
        inventory.inorderReport();
    }
}