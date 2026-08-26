import java.util.ArrayList;
import java.util.List;

public class Q02_ServiceOrder {

    public static class LineItem {

        private final String name;
        private final int unitPrice;
        private final int quantity;

        public LineItem(String name, int unitPrice, int quantity) {
            this.name = name.trim();
            this.unitPrice = unitPrice;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public int getQuantity() {
            return quantity;
        }

        public int subtotal() {
            return unitPrice * quantity;
        }
    }

    private final String orderId;
    private final List<LineItem> items;

    public Q02_ServiceOrder(String orderId) {

        if (orderId == null || orderId.trim().isEmpty()) {
            throw new IllegalArgumentException("orderId 不可為空");
        }

        this.orderId = orderId.trim();
        this.items = new ArrayList<LineItem>();
    }

    public boolean addItem(String name, int unitPrice, int quantity) {

        if (name == null || name.trim().isEmpty()) {
            return false;
        }

        if (unitPrice < 0) {
            return false;
        }

        if (quantity <= 0) {
            return false;
        }

        LineItem item =
                new LineItem(
                        name,
                        unitPrice,
                        quantity
                );

        items.add(item);

        return true;
    }

    public int itemCount() {
        return items.size();
    }

    public int totalAmount() {

        int total = 0;

        for (int i = 0; i < items.size(); i++) {
            total = total + items.get(i).subtotal();
        }

        return total;
    }

    public String largestItemName() {

        if (items.isEmpty()) {
            return "";
        }

        LineItem largest = items.get(0);

        for (int i = 1; i < items.size(); i++) {

            LineItem current = items.get(i);

            if (current.subtotal() > largest.subtotal()) {
                largest = current;
            }
        }

        return largest.getName();
    }

    public List<String> itemSummaries() {

        // composition-check 8C21-R
        List<String> result =
                new ArrayList<String>();

        for (int i = 0; i < items.size(); i++) {

            LineItem item = items.get(i);

            result.add(
                    item.getName()
                    + ":"
                    + item.subtotal()
            );
        }

        return result;
    }

    public static void main(String[] args) {

        Q02_ServiceOrder order =
                new Q02_ServiceOrder("R-01");

        order.addItem(
                "Inspection",
                300,
                1
        );

        order.addItem(
                "Cable",
                80,
                4
        );

        order.addItem(
                "Cleaning",
                200,
                1
        );

        System.out.println(order.itemCount());
        System.out.println(order.totalAmount());
        System.out.println(order.largestItemName());
        System.out.println(order.itemSummaries());
    }
}