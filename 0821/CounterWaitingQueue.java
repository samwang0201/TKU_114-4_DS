import java.util.ArrayDeque;
import java.util.Deque;

public class CounterWaitingQueue {

    static class Customer {
        private int number;
        private String name;

        public Customer(int number, String name) {
            this.number = number;
            this.name = name;
        }

        public int getNumber() {
            return number;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "號碼=" + number + ", 姓名=" + name;
        }
    }

    static void addCustomer(Deque<Customer> queue, Customer customer) {

        if (customer == null) {
            return;
        }

        queue.offerLast(customer);

        System.out.println("加入等候：" + customer);
    }

    static void showNext(Deque<Customer> queue) {

        Customer customer = queue.peekFirst();

        if (customer == null) {
            System.out.println("目前沒有等候顧客");
            return;
        }

        System.out.println("下一位：" + customer);
    }

    static void serveNext(Deque<Customer> queue) {

        Customer customer = queue.pollFirst();

        if (customer == null) {
            System.out.println("沒有顧客可以服務");
            return;
        }

        System.out.println("服務顧客：" + customer);
    }

    static void showWaitingCount(Deque<Customer> queue) {
        System.out.println("等候人數：" + queue.size());
    }

    public static void main(String[] args) {

        Deque<Customer> queue =
                new ArrayDeque<Customer>();

        addCustomer(
                queue,
                new Customer(1, "Amy")
        );

        addCustomer(
                queue,
                new Customer(2, "Bob")
        );

        addCustomer(
                queue,
                new Customer(3, "Cindy")
        );

        System.out.println("--------------------");

        showNext(queue);
        showWaitingCount(queue);

        System.out.println("--------------------");

        serveNext(queue);
        showNext(queue);
        showWaitingCount(queue);

        System.out.println("--------------------");

        serveNext(queue);
        serveNext(queue);

        showWaitingCount(queue);

        System.out.println("--------------------");

        showNext(queue);
        serveNext(queue);
    }
}