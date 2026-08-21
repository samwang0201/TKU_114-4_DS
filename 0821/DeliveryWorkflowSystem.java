import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class DeliveryWorkflowSystem {

    static class DeliveryJob {
        private String id;
        private String name;
        private String status;

        public DeliveryJob(String id, String name) {
            this.id = id;
            this.name = name;
            this.status = "等待中";
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
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
                    + ", 名稱=" + name
                    + ", 狀態=" + status;
        }
    }

    static class DeliveryWorkflow {

        private Map<String, DeliveryJob> jobMap;
        private Queue<DeliveryJob> waitingQueue;
        private Deque<DeliveryJob> completedStack;

        public DeliveryWorkflow() {
            jobMap = new HashMap<String, DeliveryJob>();
            waitingQueue = new ArrayDeque<DeliveryJob>();
            completedStack = new ArrayDeque<DeliveryJob>();
        }

        public boolean add(DeliveryJob job) {

            if (job == null) {
                return false;
            }

            if (jobMap.containsKey(job.getId())) {
                return false;
            }

            jobMap.put(job.getId(), job);
            waitingQueue.offer(job);

            return true;
        }

        public DeliveryJob processNext() {

            DeliveryJob job = waitingQueue.poll();

            if (job == null) {
                return null;
            }

            job.setStatus("已完成");
            completedStack.push(job);

            return job;
        }

        public DeliveryJob undo() {

            if (completedStack.isEmpty()) {
                return null;
            }

            DeliveryJob job = completedStack.pop();

            job.setStatus("等待中");
            waitingQueue.offer(job);

            return job;
        }

        public DeliveryJob findById(String id) {
            return jobMap.get(id);
        }

        public void printStatistics() {
            System.out.println("總工作數：" + jobMap.size());
            System.out.println("等待數：" + waitingQueue.size());
            System.out.println("已完成數：" + completedStack.size());
        }

        public void printWaiting() {

            System.out.println("等待隊列：");

            if (waitingQueue.isEmpty()) {
                System.out.println("目前沒有等待工作");
                return;
            }

            for (DeliveryJob job : waitingQueue) {
                System.out.println(job);
            }
        }

        public void printCompleted() {

            System.out.println("已完成 Stack：");

            if (completedStack.isEmpty()) {
                System.out.println("目前沒有已完成工作");
                return;
            }

            for (DeliveryJob job : completedStack) {
                System.out.println(job);
            }
        }
    }

    public static void main(String[] args) {

        DeliveryWorkflow workflow =
                new DeliveryWorkflow();

        System.out.println(
                "新增 D001："
                + workflow.add(
                        new DeliveryJob("D001", "台北配送")
                )
        );

        System.out.println(
                "新增 D002："
                + workflow.add(
                        new DeliveryJob("D002", "桃園配送")
                )
        );

        System.out.println(
                "新增 D003："
                + workflow.add(
                        new DeliveryJob("D003", "新竹配送")
                )
        );

        System.out.println(
                "重複新增 D001："
                + workflow.add(
                        new DeliveryJob("D001", "重複工作")
                )
        );

        System.out.println("--------------------");

        workflow.printWaiting();

        System.out.println("--------------------");

        System.out.println(
                "處理："
                + workflow.processNext()
        );

        System.out.println(
                "處理："
                + workflow.processNext()
        );

        System.out.println("--------------------");

        workflow.printStatistics();

        System.out.println("--------------------");

        System.out.println(
                "查詢 D002："
                + workflow.findById("D002")
        );

        System.out.println("--------------------");

        System.out.println(
                "undo："
                + workflow.undo()
        );

        System.out.println("--------------------");

        workflow.printWaiting();

        System.out.println("--------------------");

        workflow.printCompleted();

        System.out.println("--------------------");

        workflow.printStatistics();
    }
}