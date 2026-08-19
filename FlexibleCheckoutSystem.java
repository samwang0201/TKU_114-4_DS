public class FlexibleCheckoutSystem {

    interface PricingPolicy {
        double calculatePrice(double originalPrice);
        String getName();
    }

    static class OriginalPricePolicy implements PricingPolicy {

        @Override
        public double calculatePrice(double originalPrice) {
            if (originalPrice < 0) {
                originalPrice = 0;
            }
            return originalPrice;
        }

        @Override
        public String getName() {
            return "原價";
        }
    }

    static class VipDiscountPolicy implements PricingPolicy {

        @Override
        public double calculatePrice(double originalPrice) {
            if (originalPrice < 0) {
                originalPrice = 0;
            }
            return originalPrice * 0.85;
        }

        @Override
        public String getName() {
            return "VIP八五折";
        }
    }

    static class Over2000DiscountPolicy implements PricingPolicy {

        @Override
        public double calculatePrice(double originalPrice) {
            if (originalPrice < 0) {
                originalPrice = 0;
            }

            if (originalPrice >= 2000) {
                return originalPrice - 300;
            }

            return originalPrice;
        }

        @Override
        public String getName() {
            return "滿2000折300";
        }
    }

    interface NotificationChannel {
        boolean send(String receiver, String message);
        String getName();
    }

    static class EmailNotification implements NotificationChannel {

        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.trim().isEmpty()) {
                return false;
            }

            System.out.println("Email 通知：" + receiver);
            System.out.println(message);
            return true;
        }

        @Override
        public String getName() {
            return "電子郵件";
        }
    }

    static class SmsNotification implements NotificationChannel {

        @Override
        public boolean send(String receiver, String message) {
            if (receiver == null || receiver.trim().isEmpty()) {
                return false;
            }

            System.out.println("SMS 通知：" + receiver);
            System.out.println(message);
            return true;
        }

        @Override
        public String getName() {
            return "簡訊";
        }
    }

    static class ConsoleNotification implements NotificationChannel {

        @Override
        public boolean send(String receiver, String message) {
            System.out.println("Console 通知");
            System.out.println(message);
            return true;
        }

        @Override
        public String getName() {
            return "控制台";
        }
    }

    static class CheckoutResult {
        private String orderNumber;
        private double originalPrice;
        private double finalPrice;
        private boolean notificationSuccess;

        public CheckoutResult(String orderNumber,
                              double originalPrice,
                              double finalPrice,
                              boolean notificationSuccess) {

            this.orderNumber = orderNumber;
            this.originalPrice = originalPrice;
            this.finalPrice = finalPrice;
            this.notificationSuccess = notificationSuccess;
        }

        public void printResult() {
            System.out.println("訂單號碼：" + orderNumber);
            System.out.println("原價：" + originalPrice);
            System.out.println("最終價格：" + finalPrice);
            System.out.println("通知狀態："
                    + (notificationSuccess ? "成功" : "失敗"));
        }
    }

    static class CheckoutService {
        private PricingPolicy pricingPolicy;
        private NotificationChannel notificationChannel;

        public CheckoutService(PricingPolicy pricingPolicy,
                               NotificationChannel notificationChannel) {

            this.pricingPolicy = pricingPolicy;
            this.notificationChannel = notificationChannel;
        }

        public CheckoutResult checkout(String orderNumber,
                                       double originalPrice,
                                       String receiver) {

            if (originalPrice < 0) {
                originalPrice = 0;
            }

            double finalPrice =
                    pricingPolicy.calculatePrice(originalPrice);

            String message =
                    "訂單：" + orderNumber
                    + "，定價方式：" + pricingPolicy.getName()
                    + "，最終價格：" + finalPrice;

            boolean success =
                    notificationChannel.send(receiver, message);

            return new CheckoutResult(
                    orderNumber,
                    originalPrice,
                    finalPrice,
                    success
            );
        }
    }

    public static void main(String[] args) {

        CheckoutService service1 =
                new CheckoutService(
                        new OriginalPricePolicy(),
                        new EmailNotification()
                );

        CheckoutResult result1 =
                service1.checkout("A001", 1000, "user@test.com");

        result1.printResult();

        System.out.println("--------------------");

        CheckoutService service2 =
                new CheckoutService(
                        new OriginalPricePolicy(),
                        new SmsNotification()
                );

        CheckoutResult result2 =
                service2.checkout("A002", 1500, "0912345678");

        result2.printResult();

        System.out.println("--------------------");

        CheckoutService service3 =
                new CheckoutService(
                        new VipDiscountPolicy(),
                        new EmailNotification()
                );

        CheckoutResult result3 =
                service3.checkout("A003", 2000, "vip@test.com");

        result3.printResult();

        System.out.println("--------------------");

        CheckoutService service4 =
                new CheckoutService(
                        new VipDiscountPolicy(),
                        new ConsoleNotification()
                );

        CheckoutResult result4 =
                service4.checkout("A004", 3000, "");

        result4.printResult();

        System.out.println("--------------------");

        CheckoutService service5 =
                new CheckoutService(
                        new Over2000DiscountPolicy(),
                        new SmsNotification()
                );

        CheckoutResult result5 =
                service5.checkout("A005", 2500, "0987654321");

        result5.printResult();

        System.out.println("--------------------");

        CheckoutService service6 =
                new CheckoutService(
                        new Over2000DiscountPolicy(),
                        new EmailNotification()
                );

        CheckoutResult result6 =
                service6.checkout("A006", 1800, "test@test.com");

        result6.printResult();
    }
}