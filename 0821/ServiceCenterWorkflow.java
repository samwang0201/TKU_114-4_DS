import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceCenterWorkflow {

    static class ServiceTicket {
        private String id;
        private String customerName;
        private String status;

        public ServiceTicket(String id, String customerName) {
            this.id = id;
            this.customerName = customerName;
            this.status = "等待中";
        }

        public String getId() {
            return id;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return "id=" + id
                    + ", 客戶=" + customerName
                    + ", 狀態=" + status;
        }
    }

    static class ServiceCenter {

        private Map<String, ServiceTicket> ticketMap;
        private Deque<ServiceTicket> waitingQueue;
        private Deque<ServiceTicket> completedStack;
        private Set<String> ticketIds;

        public ServiceCenter() {
            ticketMap = new HashMap<String, ServiceTicket>();
            waitingQueue = new ArrayDeque<ServiceTicket>();
            completedStack = new ArrayDeque<ServiceTicket>();
            ticketIds = new HashSet<String>();
        }

        public boolean createTicket(String id, String customerName) {

            if (id == null || id.trim().isEmpty()) {
                return false;
            }

            if (ticketIds.contains(id)) {
                return false;
            }

            ServiceTicket ticket =
                    new ServiceTicket(id, customerName);

            ticketMap.put(id, ticket);
            ticketIds.add(id);
            waitingQueue.offerLast(ticket);

            return true;
        }

        public ServiceTicket processNext() {

            ServiceTicket ticket =
                    waitingQueue.pollFirst();

            if (ticket == null) {
                return null;
            }

            ticket.setStatus("已完成");
            completedStack.push(ticket);

            return ticket;
        }

        public boolean cancelWaiting(String id) {

            ServiceTicket ticket =
                    ticketMap.get(id);

            if (ticket == null) {
                return false;
            }

            if (!ticket.getStatus().equals("等待中")) {
                return false;
            }

            boolean removed =
                    waitingQueue.remove(ticket);

            if (!removed) {
                return false;
            }

            ticket.setStatus("已取消");

            return true;
        }

        public ServiceTicket undoLastCompletion() {

            if (completedStack.isEmpty()) {
                return null;
            }

            ServiceTicket ticket =
                    completedStack.pop();

            ticket.setStatus("等待中");

            waitingQueue.offerFirst(ticket);

            return ticket;
        }

        public ServiceTicket findById(String id) {
            return ticketMap.get(id);
        }

        public void printSummary() {

            System.out.println(
                    "總工單數：" + ticketMap.size()
            );

            System.out.println(
                    "等待數：" + waitingQueue.size()
            );

            System.out.println(
                    "完成 Stack 數：" + completedStack.size()
            );

            int cancelled = 0;

            for (ServiceTicket ticket : ticketMap.values()) {
                if (ticket.getStatus().equals("已取消")) {
                    cancelled++;
                }
            }

            System.out.println(
                    "取消數：" + cancelled
            );
        }

        public void printWaiting() {

            System.out.println("等待隊列：");

            if (waitingQueue.isEmpty()) {
                System.out.println("目前沒有等待工單");
                return;
            }

            for (ServiceTicket ticket : waitingQueue) {
                System.out.println(ticket);
            }
        }

        public void printCompleted() {

            System.out.println("完成 Stack：");

            if (completedStack.isEmpty()) {
                System.out.println("目前沒有完成工單");
                return;
            }

            for (ServiceTicket ticket : completedStack) {
                System.out.println(ticket);
            }
        }
    }

    public static void main(String[] args) {

        ServiceCenter center =
                new ServiceCenter();

        System.out.println(
                "建立 T001："
                + center.createTicket(
                        "T001", "Amy"
                )
        );

        System.out.println(
                "建立 T002："
                + center.createTicket(
                        "T002", "Bob"
                )
        );

        System.out.println(
                "建立 T003："
                + center.createTicket(
                        "T003", "Cindy"
                )
        );

        System.out.println(
                "建立 T004："
                + center.createTicket(
                        "T004", "David"
                )
        );

        System.out.println(
                "重複建立 T001："
                + center.createTicket(
                        "T001", "Kevin"
                )
        );

        System.out.println("--------------------");

        center.printWaiting();

        System.out.println("--------------------");

        System.out.println(
                "處理："
                + center.processNext()
        );

        System.out.println(
                "處理："
                + center.processNext()
        );

        System.out.println("--------------------");

        System.out.println(
                "取消等待 T003："
                + center.cancelWaiting("T003")
        );

        System.out.println(
                "取消已完成 T001："
                + center.cancelWaiting("T001")
        );

        System.out.println(
                "取消不存在 T999："
                + center.cancelWaiting("T999")
        );

        System.out.println("--------------------");

        System.out.println(
                "查詢 T002："
                + center.findById("T002")
        );

        System.out.println("--------------------");

        System.out.println(
                "第一次 undo："
                + center.undoLastCompletion()
        );

        System.out.println(
                "第二次 undo："
                + center.undoLastCompletion()
        );

        System.out.println("--------------------");

        center.printWaiting();

        System.out.println("--------------------");

        center.printCompleted();

        System.out.println("--------------------");

        center.printSummary();

        System.out.println("--------------------");

        System.out.println("繼續處理：");

        System.out.println(center.processNext());
        System.out.println(center.processNext());
        System.out.println(center.processNext());

        System.out.println("--------------------");

        System.out.println(
                "空隊列處理："
                + center.processNext()
        );
    }
}