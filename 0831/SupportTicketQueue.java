import java.util.PriorityQueue;

public class SupportTicketQueue {

    public static class Ticket {

        private final String id;
        private final int severity;
        private final int createdOrder;

        public Ticket(
                String id,
                int severity,
                int createdOrder) {

            this.id = id;
            this.severity = severity;
            this.createdOrder = createdOrder;
        }

        public String getId() {
            return id;
        }

        public int getSeverity() {
            return severity;
        }

        public int getCreatedOrder() {
            return createdOrder;
        }

        @Override
        public String toString() {
            return id
                    + "|"
                    + severity
                    + "|"
                    + createdOrder;
        }
    }

    public static void main(String[] args) {

        PriorityQueue<Ticket> queue =
                new PriorityQueue<Ticket>(
                        (a, b) -> {

                            if (a.getSeverity()
                                    != b.getSeverity()) {

                                return Integer.compare(
                                        b.getSeverity(),
                                        a.getSeverity()
                                );
                            }

                            return Integer.compare(
                                    a.getCreatedOrder(),
                                    b.getCreatedOrder()
                            );
                        }
                );

        queue.add(
                new Ticket("T1", 3, 1)
        );

        queue.add(
                new Ticket("T2", 5, 2)
        );

        queue.add(
                new Ticket("T3", 5, 3)
        );

        queue.add(
                new Ticket("T4", 2, 4)
        );

        queue.add(
                new Ticket("T5", 3, 5)
        );

        while (!queue.isEmpty()) {

            Ticket ticket = queue.poll();

            System.out.println(ticket);
        }
    }
}