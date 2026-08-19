interface DeliveryMethod {
    double calculateFee();
    String getDescription();
}

class HomeDelivery implements DeliveryMethod {

    @Override
    public double calculateFee() {
        return 100;
    }

    @Override
    public String getDescription() {
        return "宅配";
    }
}

class StorePickup implements DeliveryMethod {

    @Override
    public double calculateFee() {
        return 60;
    }

    @Override
    public String getDescription() {
        return "超商取貨";
    }
}

class SelfPickup implements DeliveryMethod {

    @Override
    public double calculateFee() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "自取";
    }
}

class OrderService {
    private DeliveryMethod deliveryMethod;

    public OrderService(DeliveryMethod deliveryMethod) {
        this.deliveryMethod = deliveryMethod;
    }

    public double calculateDeliveryFee() {
        return deliveryMethod.calculateFee();
    }

    public void printInvoice(double productPrice) {
        double deliveryFee = calculateDeliveryFee();
        double total = productPrice + deliveryFee;

        System.out.println("商品金額：" + productPrice);
        System.out.println("配送方式：" + deliveryMethod.getDescription());
        System.out.println("運費：" + deliveryFee);
        System.out.println("總金額：" + total);
    }
}

public class DeliveryStrategySystem {
    public static void main(String[] args) {

        OrderService order1 =
                new OrderService(new HomeDelivery());

        OrderService order2 =
                new OrderService(new StorePickup());

        OrderService order3 =
                new OrderService(new SelfPickup());

        System.out.println("訂單一");
        order1.printInvoice(1000);

        System.out.println("--------------------");

        System.out.println("訂單二");
        order2.printInvoice(1000);

        System.out.println("--------------------");

        System.out.println("訂單三");
        order3.printInvoice(1000);
    }
}