public class OrderCompositionDemo {

    static class DigitalWallet {
        private String walletId;
        private String owner;
        private double balance;
        private int transactionCount;

        public DigitalWallet(String walletId, String owner, double balance) {
            this.walletId = walletId;
            this.owner = owner;

            if (balance < 0) {
                this.balance = 0;
            } else {
                this.balance = balance;
            }

            transactionCount = 0;
        }

        public boolean deposit(double amount) {
            if (amount <= 0) {
                return false;
            }

            balance += amount;
            transactionCount++;
            return true;
        }

        public boolean pay(double amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }

            balance -= amount;
            transactionCount++;
            return true;
        }

        public boolean refund(double amount) {
            if (amount <= 0) {
                return false;
            }

            balance += amount;
            transactionCount++;
            return true;
        }

        public String toString() {
            return "錢包編號：" + walletId
                    + "，擁有者：" + owner
                    + "，餘額：" + balance
                    + "，交易次數：" + transactionCount;
        }
    }


    public static void main(String[] args) {

        DigitalWallet wallet =
                new DigitalWallet("W001", "王小明", 500);

        System.out.println("=== 初始資料 ===");
        System.out.println(wallet);

        System.out.println("\n=== 正常儲值 ===");
        System.out.println("儲值 300：" + wallet.deposit(300));
        System.out.println(wallet);

        System.out.println("\n=== 正常付款 ===");
        System.out.println("付款 200：" + wallet.pay(200));
        System.out.println(wallet);

        System.out.println("\n=== 餘額不足 ===");
        System.out.println("付款 1000：" + wallet.pay(1000));
        System.out.println(wallet);

        System.out.println("\n=== 負數金額 ===");
        System.out.println("付款 -100：" + wallet.pay(-100));
        System.out.println(wallet);

        System.out.println("\n=== 退款 ===");
        System.out.println("退款 100：" + wallet.refund(100));
        System.out.println(wallet);
    }
}