public class WalletHistoryManager {

    static class WalletTransaction {
        private int sequence;
        private String type;
        private int amount;
        private int balanceAfter;

        public WalletTransaction(int sequence, String type,
                                 int amount, int balanceAfter) {
            this.sequence = sequence;
            this.type = type;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }

        public int getSequence() {
            return sequence;
        }

        public String getType() {
            return type;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "序號：" + sequence
                    + "，類型：" + type
                    + "，金額：" + amount
                    + "，交易後餘額：" + balanceAfter;
        }
    }

    static class Wallet {
        private String walletId;
        private String owner;
        private int balance;

        private WalletTransaction[] transactions;
        private int transactionCount;
        private int nextSequence;

        public Wallet(String walletId, String owner,
                      int balance, int maxTransactions) {

            this.walletId = walletId;
            this.owner = owner;

            if (balance < 0) {
                this.balance = 0;
            } else {
                this.balance = balance;
            }

            if (maxTransactions < 0) {
                maxTransactions = 0;
            }

            transactions = new WalletTransaction[maxTransactions];
            transactionCount = 0;
            nextSequence = 1;
        }

        private boolean hasTransactionSpace() {
            return transactionCount < transactions.length;
        }

        private void addTransaction(String type, int amount) {

            transactions[transactionCount] =
                    new WalletTransaction(
                            nextSequence,
                            type,
                            amount,
                            balance
                    );

            transactionCount++;
            nextSequence++;
        }

        public boolean deposit(int amount) {

            if (amount <= 0) {
                return false;
            }

            if (!hasTransactionSpace()) {
                return false;
            }

            balance += amount;
            addTransaction("DEPOSIT", amount);

            return true;
        }

        public boolean pay(int amount) {

            if (amount <= 0) {
                return false;
            }

            if (balance < amount) {
                return false;
            }

            if (!hasTransactionSpace()) {
                return false;
            }

            balance -= amount;
            addTransaction("PAY", amount);

            return true;
        }

        public boolean refund(int amount) {

            if (amount <= 0) {
                return false;
            }

            if (!hasTransactionSpace()) {
                return false;
            }

            balance += amount;
            addTransaction("REFUND", amount);

            return true;
        }

        public WalletTransaction findTransaction(int sequence) {

            for (int i = 0; i < transactionCount; i++) {

                if (transactions[i].getSequence() == sequence) {
                    return transactions[i];
                }
            }

            return null;
        }

        public int totalByType(String type) {

            int total = 0;

            if (type == null) {
                return 0;
            }

            for (int i = 0; i < transactionCount; i++) {

                if (transactions[i].getType().equalsIgnoreCase(type)) {
                    total += transactions[i].getAmount();
                }
            }

            return total;
        }

        public boolean transferTo(Wallet target, int amount) {

            if (target == null) {
                return false;
            }

            if (target == this) {
                return false;
            }

            if (amount <= 0) {
                return false;
            }

            if (balance < amount) {
                return false;
            }

            if (!this.hasTransactionSpace()
                    || !target.hasTransactionSpace()) {
                return false;
            }

            this.balance -= amount;
            target.balance += amount;

            this.addTransaction("TRANSFER_OUT", amount);
            target.addTransaction("TRANSFER_IN", amount);

            return true;
        }

        public String statement() {

            String result = "";

            result += "============================\n";
            result += "錢包編號：" + walletId + "\n";
            result += "持有人：" + owner + "\n";
            result += "目前餘額：" + balance + "\n";
            result += "------ 交易紀錄 ------\n";

            if (transactionCount == 0) {
                result += "目前沒有交易紀錄\n";
            } else {

                for (int i = 0; i < transactionCount; i++) {
                    result += transactions[i].toString() + "\n";
                }
            }

            result += "============================";

            return result;
        }
    }

    public static void main(String[] args) {

        Wallet wallet1 =
                new Wallet("W001", "小明", 1000, 10);
        Wallet wallet2 =
                new Wallet("W002", "小華", 500, 10);
        wallet1.deposit(500);
        wallet1.pay(200);
        wallet1.refund(100);
        wallet2.deposit(300);
        System.out.println("=== 轉帳測試 ===");

        boolean result = wallet1.transferTo(wallet2, 400);

        System.out.println("轉帳結果：" + result);
        System.out.println("\n=== 尋找交易 ===");

        WalletTransaction t = wallet1.findTransaction(2);

        if (t != null) {
            System.out.println("找到：" + t);
        } else {
            System.out.println("找不到交易");
        }


        WalletTransaction t2 = wallet1.findTransaction(99);

        if (t2 != null) {
            System.out.println("找到：" + t2);
        } else {
            System.out.println("序號 99 找不到交易");}
        System.out.println("\n=== 各類型交易總額 ===");

        System.out.println(
                "wallet1 DEPOSIT 總額："
                        + wallet1.totalByType("DEPOSIT")
        );

        System.out.println(
                "wallet1 PAY 總額："
                        + wallet1.totalByType("PAY")
        );

        System.out.println(
                "wallet1 TRANSFER_OUT 總額："
                        + wallet1.totalByType("TRANSFER_OUT")
        );

        System.out.println("\n=== Wallet 1 Statement ===");
        System.out.println(wallet1.statement());
        System.out.println("\n=== Wallet 2 Statement ===");
        System.out.println(wallet2.statement());
    }
}