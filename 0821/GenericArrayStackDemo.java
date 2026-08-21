public class GenericArrayStackDemo {

    static class ArrayStack<T> {

        private Object[] data;
        private int top;

        public ArrayStack(int capacity) {
            data = new Object[capacity];
            top = 0;
        }

        public boolean push(T value) {

            if (isFull()) {
                return false;
            }

            data[top] = value;
            top++;

            return true;
        }

        @SuppressWarnings("unchecked")
        public T pop() {

            if (isEmpty()) {
                return null;
            }

            top--;

            T value = (T) data[top];
            data[top] = null;

            return value;
        }

        @SuppressWarnings("unchecked")
        public T peek() {

            if (isEmpty()) {
                return null;
            }

            return (T) data[top - 1];
        }

        public int size() {
            return top;
        }

        public boolean isEmpty() {
            return top == 0;
        }

        public boolean isFull() {
            return top == data.length;
        }
    }

    public static void main(String[] args) {

        ArrayStack<String> stringStack =
                new ArrayStack<String>(3);

        System.out.println("String Stack：");

        System.out.println("push Java：" + stringStack.push("Java"));
        System.out.println("push Python：" + stringStack.push("Python"));
        System.out.println("push C++：" + stringStack.push("C++"));

        System.out.println("目前大小：" + stringStack.size());
        System.out.println("是否已滿：" + stringStack.isFull());
        System.out.println("最上方：" + stringStack.peek());

        System.out.println("pop：" + stringStack.pop());
        System.out.println("pop：" + stringStack.pop());

        System.out.println("目前大小：" + stringStack.size());

        System.out.println("--------------------");

        ArrayStack<Integer> integerStack =
                new ArrayStack<Integer>(3);

        System.out.println("Integer Stack：");

        System.out.println("push 10：" + integerStack.push(10));
        System.out.println("push 20：" + integerStack.push(20));
        System.out.println("push 30：" + integerStack.push(30));

        System.out.println("目前大小：" + integerStack.size());
        System.out.println("是否已滿：" + integerStack.isFull());

        System.out.println("push 40：" + integerStack.push(40));

        System.out.println("最上方：" + integerStack.peek());

        System.out.println("pop：" + integerStack.pop());
        System.out.println("pop：" + integerStack.pop());
        System.out.println("pop：" + integerStack.pop());

        System.out.println("是否為空：" + integerStack.isEmpty());

        System.out.println("pop 空 Stack：" + integerStack.pop());
    }
}