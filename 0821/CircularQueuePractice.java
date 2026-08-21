public class CircularQueuePractice {

    static class CircularQueue<T> {

        private Object[] data;
        private int front;
        private int rear;
        private int size;

        public CircularQueue(int capacity) {
            data = new Object[capacity];
            front = 0;
            rear = 0;
            size = 0;
        }

        public boolean enqueue(T value) {

            if (size == data.length) {
                System.out.println("Queue 已滿");
                return false;
            }

            data[rear] = value;

            rear = (rear + 1) % data.length;

            size++;

            return true;
        }

        @SuppressWarnings("unchecked")
        public T dequeue() {

            if (size == 0) {
                System.out.println("Queue 是空的");
                return null;
            }

            T value = (T) data[front];

            data[front] = null;

            front = (front + 1) % data.length;

            size--;

            return value;
        }

        public int size() {
            return size;
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public void printState() {

            System.out.print("內部陣列：[");

            for (int i = 0; i < data.length; i++) {

                if (i > 0) {
                    System.out.print(", ");
                }

                System.out.print(data[i]);
            }

            System.out.println("]");

            System.out.println("front = " + front);
            System.out.println("rear = " + rear);
            System.out.println("size = " + size);

            System.out.println("--------------------");
        }
    }

    public static void main(String[] args) {

        CircularQueue<String> queue =
                new CircularQueue<String>(4);

        System.out.println("enqueue A");
        queue.enqueue("A");
        queue.printState();

        System.out.println("enqueue B");
        queue.enqueue("B");
        queue.printState();

        System.out.println("enqueue C");
        queue.enqueue("C");
        queue.printState();

        System.out.println("dequeue");
        System.out.println("取出：" + queue.dequeue());
        queue.printState();

        System.out.println("dequeue");
        System.out.println("取出：" + queue.dequeue());
        queue.printState();

        System.out.println("enqueue D");
        queue.enqueue("D");
        queue.printState();

        System.out.println("enqueue E");
        queue.enqueue("E");
        queue.printState();

        System.out.println("enqueue F");
        queue.enqueue("F");
        queue.printState();

        System.out.println("dequeue");
        System.out.println("取出：" + queue.dequeue());
        queue.printState();

        System.out.println("enqueue G");
        queue.enqueue("G");
        queue.printState();

        System.out.println("最後依 FIFO 順序取出：");

        while (!queue.isEmpty()) {
            System.out.println(queue.dequeue());
        }
    }
}