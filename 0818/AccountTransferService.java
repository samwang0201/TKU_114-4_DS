class Account {
    private String accountId;
    private String owner;
    private int balance;

    public Account(String accountId, String owner, int balance) {
        this.accountId = accountId;
        this.owner = owner;

        if (balance < 0) {
            this.balance = 0;
        } else {
            this.balance = balance;
        }
    }

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        balance -= amount;
    }

    public void deposit(int amount) {
        balance += amount;
    }

    @Override
    public String toString() {
        return "帳戶編號：" + accountId
                + "，擁有者：" + owner
                + "，餘額：" + balance;
    }
}


class TransferService {

    public boolean transfer(Account source, Account target, int amount) {

        if (source == null || target == null) {
            return false;
        }
        if (source == target) {
            return false;
        }
        if (amount <= 0) {
            return false;
        }
        if (source.getBalance() < amount) {
            return false;
        }

        source.withdraw(amount);
        target.deposit(amount);

        return true;
    }
}


public class AccountTransferService {
    public static void main(String[] args) {

        Account account1 = new Account("A001", "小明", 1000);
        Account account2 = new Account("A002", "小華", 500);

        TransferService service = new TransferService();

        System.out.println("=== 成功轉帳 ===");
        System.out.println("轉帳結果："+ service.transfer(account1, account2, 300));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println("\n=== 餘額不足 ===");
        System.out.println("轉帳結果："+ service.transfer(account1, account2, 1000));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println("\n=== 同帳戶轉帳 ===");
        System.out.println("轉帳結果："+ service.transfer(account1, account1, 100));
        System.out.println(account1);
        System.out.println(account2);
        System.out.println("\n=== null 目標 ===");
        System.out.println("轉帳結果："+ service.transfer(account1, null, 100));
        System.out.println(account1);
        System.out.println(account2);
    }
}