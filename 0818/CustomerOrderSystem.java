class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "顧客編號：" + customerId
                + "，姓名：" + name;
    }
}


class OrderItem {
    private String productName;
    private double price;
    private int quantity;

    public OrderItem(String productName, double price, int quantity) {
        this.productName = productName;

        if (price < 0) {
            this.price = 0;
        } else {
            this.price = price;
        }

        if (quantity < 0) {
            this.quantity = 0;
        } else {
            this.quantity = quantity;
        }
    }

    public double subtotal() {
        return price * quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "商品：" + productName
                + "，單價：" + price
                + "，數量：" + quantity
                + "，小計：" + subtotal();
    }
}


class CustomerOrder {
    private String orderId;

    private Customer customer;

    private OrderItem[] items;

    private int itemCount;

    public CustomerOrder(String orderId, Customer customer, int maxItems) {
        this.orderId = orderId;
        this.customer = customer;

        if (maxItems < 0) {
            maxItems = 0;
        }

        items = new OrderItem[maxItems];
        itemCount = 0;
    }

    public boolean addItem(OrderItem item) {
        if (item == null) {
            return false;
        }

        if (itemCount >= items.length) {
            return false;
        }

        items[itemCount] = item;
        itemCount++;

        return true;
    }

    public double totalAmount() {
        double total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].subtotal();
        }

        return total;
    }

    public int totalQuantity() {
        int total = 0;

        for (int i = 0; i < itemCount; i++) {
            total += items[i].getQuantity();
        }

        return total;
    }

    public String summary() {
        String result = "";

        result += "訂單編號：" + orderId + "\n";
        result += customer.toString() + "\n";
        result += "=== 訂單品項 ===\n";

        for (int i = 0; i < itemCount; i++) {
            result += items[i].toString() + "\n";
        }

        result += "商品總數量：" + totalQuantity() + "\n";
        result += "訂單總額：" + totalAmount();

        return result;
    }
}


public class CustomerOrderSystem {
    public static void main(String[] args) {

        Customer customer = new Customer("C001", "王小明");
        CustomerOrder order = new CustomerOrder("O001", customer, 3);

        OrderItem item1 =new OrderItem("鍵盤", 1000, 1);
        OrderItem item2 =new OrderItem("滑鼠", 500, 2);
        OrderItem item3 =new OrderItem("耳機", 800, 1);
        order.addItem(item1);
        order.addItem(item2);
        order.addItem(item3);
        System.out.println(order.summary());
    }
}