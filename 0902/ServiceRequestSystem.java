import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ServiceRequestSystem {

    public static class Request {

        private final String id;
        private final String description;
        private final int priority;
        private final int createdOrder;

        public Request(
                String id,
                String description,
                int priority,
                int createdOrder) {

            if (id == null || id.trim().isEmpty()) {
                throw new IllegalArgumentException("id 不可為空");
            }

            if (description == null
                    || description.trim().isEmpty()) {
                throw new IllegalArgumentException(
                        "description 不可為空"
                );
            }

            this.id = id.trim();
            this.description = description.trim();
            this.priority = priority;
            this.createdOrder = createdOrder;
        }

        public String getId() {
            return id;
        }

        public String getDescription() {
            return description;
        }

        public int getPriority() {
            return priority;
        }

        public int getCreatedOrder() {
            return createdOrder;
        }

        @Override
        public String toString() {
            return id
                    + "|"
                    + description
                    + "|"
                    + priority
                    + "|"
                    + createdOrder;
        }
    }

    private final Map<String, Request> requestMap;
    private final PriorityQueue<Request> queue;

    public ServiceRequestSystem() {

        requestMap =
                new HashMap<String, Request>();

        queue =
                new PriorityQueue<Request>(
                        (a, b) -> {

                            if (a.getPriority()
                                    != b.getPriority()) {

                                return Integer.compare(
                                        b.getPriority(),
                                        a.getPriority()
                                );
                            }

                            return Integer.compare(
                                    a.getCreatedOrder(),
                                    b.getCreatedOrder()
                            );
                        }
                );
    }

    public boolean addRequest(Request request) {

        if (request == null) {
            return false;
        }

        if (requestMap.containsKey(request.getId())) {
            return false;
        }

        requestMap.put(
                request.getId(),
                request
        );

        queue.offer(request);

        return true;
    }

    public Request find(String id) {

        if (id == null) {
            return null;
        }

        return requestMap.get(id.trim());
    }

    public Request peekNext() {
        return queue.peek();
    }

    public Request processNext() {

        Request request = queue.poll();

        if (request == null) {
            return null;
        }

        requestMap.remove(
                request.getId()
        );

        return request;
    }

    public boolean cancel(String id) {

        if (id == null) {
            return false;
        }

        id = id.trim();

        Request request =
                requestMap.get(id);

        if (request == null) {
            return false;
        }

        boolean removedFromQueue =
                queue.remove(request);

        if (!removedFromQueue) {
            return false;
        }

        requestMap.remove(id);

        return true;
    }

    public int size() {
        return requestMap.size();
    }

    public boolean contains(String id) {
        return find(id) != null;
    }

    public boolean isConsistent() {

        if (requestMap.size() != queue.size()) {
            return false;
        }

        for (Request request : queue) {

            if (requestMap.get(request.getId()) != request) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        ServiceRequestSystem system =
                new ServiceRequestSystem();

        System.out.println("===== 新增 =====");

        System.out.println(
                system.addRequest(
                        new Request(
                                "R1",
                                "Printer problem",
                                2,
                                1
                        )
                )
        );

        System.out.println(
                system.addRequest(
                        new Request(
                                "R2",
                                "Server down",
                                5,
                                2
                        )
                )
        );

        System.out.println(
                system.addRequest(
                        new Request(
                                "R3",
                                "Password reset",
                                3,
                                3
                        )
                )
        );

        System.out.println(
                system.addRequest(
                        new Request(
                                "R4",
                                "Network problem",
                                5,
                                4
                        )
                )
        );

        System.out.println(
                "重複 R1："
                + system.addRequest(
                        new Request(
                                "R1",
                                "Duplicate",
                                9,
                                5
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "find R3 = "
                + system.find("R3")
        );

        System.out.println(
                "下一筆 = "
                + system.peekNext()
        );

        System.out.println(
                "目前數量 = "
                + system.size()
        );

        System.out.println(
                "資料一致 = "
                + system.isConsistent()
        );

        System.out.println("--------------------");

        System.out.println(
                "取消 R3 = "
                + system.cancel("R3")
        );

        System.out.println(
                "取消不存在 R99 = "
                + system.cancel("R99")
        );

        System.out.println(
                "取消後 contains R3 = "
                + system.contains("R3")
        );

        System.out.println(
                "資料一致 = "
                + system.isConsistent()
        );

        System.out.println("--------------------");

        System.out.println("===== 依序處理 =====");

        while (system.size() > 0) {

            System.out.println(
                    "處理："
                    + system.processNext()
            );

            System.out.println(
                    "剩餘數量："
                    + system.size()
            );

            System.out.println(
                    "資料一致："
                    + system.isConsistent()
            );
        }

        System.out.println("--------------------");

        System.out.println(
                "空 Queue processNext = "
                + system.processNext()
        );
    }
}