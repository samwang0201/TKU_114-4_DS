public class LinkedTaskListSystem {

    static class Task {
        private String id;
        private String title;

        public Task(String id, String title) {
            this.id = id;
            this.title = title;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return "id=" + id + ", title=" + title;
        }
    }

    static class TaskNode {
        private Task task;
        private TaskNode next;

        public TaskNode(Task task) {
            this.task = task;
            this.next = null;
        }
    }

    static class TaskLinkedList {
        private TaskNode head;
        private int size;

        public TaskLinkedList() {
            head = null;
            size = 0;
        }

        public boolean addFirst(Task task) {

            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode newNode = new TaskNode(task);

            newNode.next = head;
            head = newNode;

            size++;

            return true;
        }

        public boolean addLast(Task task) {

            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode newNode = new TaskNode(task);

            if (head == null) {
                head = newNode;
                size++;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;

            size++;

            return true;
        }

        public Task findById(String id) {

            TaskNode current = head;

            while (current != null) {

                if (current.task.getId().equals(id)) {
                    return current.task;
                }

                current = current.next;
            }

            return null;
        }

        public boolean removeById(String id) {

            if (head == null) {
                return false;
            }

            if (head.task.getId().equals(id)) {
                head = head.next;
                size--;
                return true;
            }

            TaskNode current = head;

            while (current.next != null) {

                if (current.next.task.getId().equals(id)) {
                    current.next = current.next.next;
                    size--;
                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public boolean insertAfter(String existingId, Task task) {

            if (task == null || findById(task.getId()) != null) {
                return false;
            }

            TaskNode current = head;

            while (current != null) {

                if (current.task.getId().equals(existingId)) {

                    TaskNode newNode =
                            new TaskNode(task);

                    newNode.next = current.next;
                    current.next = newNode;

                    size++;

                    return true;
                }

                current = current.next;
            }

            return false;
        }

        public int size() {
            return size;
        }

        public void printAll() {

            if (head == null) {
                System.out.println("List 是空的");
                return;
            }

            TaskNode current = head;

            while (current != null) {
                System.out.println(current.task);
                current = current.next;
            }
        }
    }

    public static void main(String[] args) {

        TaskLinkedList list =
                new TaskLinkedList();

        System.out.println("空 List：");
        list.printAll();

        System.out.println("--------------------");

        System.out.println(
                "addFirst T001："
                + list.addFirst(
                        new Task("T001", "寫 Java")
                )
        );

        System.out.println(
                "addLast T002："
                + list.addLast(
                        new Task("T002", "寫報告")
                )
        );

        System.out.println(
                "addLast T003："
                + list.addLast(
                        new Task("T003", "準備考試")
                )
        );

        System.out.println(
                "addLast T004："
                + list.addLast(
                        new Task("T004", "上傳作業")
                )
        );

        System.out.println(
                "重複 T001："
                + list.addLast(
                        new Task("T001", "重複資料")
                )
        );

        System.out.println("--------------------");

        System.out.println("目前資料：");
        list.printAll();

        System.out.println("size：" + list.size());

        System.out.println("--------------------");

        System.out.println(
                "find T002："
                + list.findById("T002")
        );

        System.out.println(
                "find T999："
                + list.findById("T999")
        );

        System.out.println("--------------------");

        System.out.println(
                "insertAfter T002："
                + list.insertAfter(
                        "T002",
                        new Task("T005", "練習程式")
                )
        );

        list.printAll();

        System.out.println("--------------------");

        System.out.println(
                "刪除 head T001："
                + list.removeById("T001")
        );

        list.printAll();

        System.out.println("--------------------");

        System.out.println(
                "刪除 middle T005："
                + list.removeById("T005")
        );

        list.printAll();

        System.out.println("--------------------");

        System.out.println(
                "刪除 tail T004："
                + list.removeById("T004")
        );

        list.printAll();

        System.out.println("--------------------");

        System.out.println(
                "刪除不存在 T999："
                + list.removeById("T999")
        );

        System.out.println(
                "最後 size："
                + list.size()
        );
    }
}