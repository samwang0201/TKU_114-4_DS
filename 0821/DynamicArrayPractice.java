public class DynamicArrayPractice {

    static class DynamicArray<T> {

        private Object[] data;
        private int size;

        public DynamicArray() {
            data = new Object[2];
            size = 0;
        }

        public void add(T value) {

            if (size == data.length) {
                grow();
            }

            data[size] = value;
            size++;
        }

        public void add(int index, T value) {

            if (index < 0 || index > size) {
                System.out.println("索引錯誤：" + index);
                return;
            }

            if (size == data.length) {
                grow();
            }

            for (int i = size; i > index; i--) {
                data[i] = data[i - 1];
            }

            data[index] = value;
            size++;
        }

        @SuppressWarnings("unchecked")
        public T get(int index) {

            if (index < 0 || index >= size) {
                System.out.println("索引錯誤：" + index);
                return null;
            }

            return (T) data[index];
        }

        @SuppressWarnings("unchecked")
        public T set(int index, T value) {

            if (index < 0 || index >= size) {
                System.out.println("索引錯誤：" + index);
                return null;
            }

            T oldValue = (T) data[index];

            data[index] = value;

            return oldValue;
        }

        @SuppressWarnings("unchecked")
        public T remove(int index) {

            if (index < 0 || index >= size) {
                System.out.println("索引錯誤：" + index);
                return null;
            }

            T oldValue = (T) data[index];

            for (int i = index; i < size - 1; i++) {
                data[i] = data[i + 1];
            }

            size--;

            data[size] = null;

            return oldValue;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return data.length;
        }

        private void grow() {

            Object[] newData =
                    new Object[data.length * 2];

            for (int i = 0; i < size; i++) {
                newData[i] = data[i];
            }

            data = newData;
        }

        public void printAll() {

            System.out.print("[");

            for (int i = 0; i < size; i++) {

                if (i > 0) {
                    System.out.print(", ");
                }

                System.out.print(data[i]);
            }

            System.out.println("]");
        }
    }

    public static void main(String[] args) {

        DynamicArray<String> words =
                new DynamicArray<String>();

        System.out.println("String 測試：");

        words.add("Java");
        words.add("Python");

        System.out.println("size：" + words.size());
        System.out.println("capacity：" + words.capacity());

        words.add("C++");

        System.out.println("加入 C++ 後：");
        words.printAll();

        System.out.println("size：" + words.size());
        System.out.println("capacity：" + words.capacity());

        words.add(1, "HTML");

        System.out.println("索引 1 插入 HTML：");
        words.printAll();

        System.out.println("get(2)：" + words.get(2));

        System.out.println(
                "set(2, CSS) 舊資料："
                + words.set(2, "CSS")
        );

        words.printAll();

        System.out.println(
                "remove(1)："
                + words.remove(1)
        );

        words.printAll();

        System.out.println("--------------------");

        DynamicArray<Integer> numbers =
                new DynamicArray<Integer>();

        System.out.println("Integer 測試：");

        numbers.add(10);
        numbers.add(20);
        numbers.add(30);
        numbers.add(40);

        numbers.printAll();

        System.out.println("size：" + numbers.size());
        System.out.println("capacity：" + numbers.capacity());

        System.out.println("--------------------");

        System.out.println("測試索引 -1：");
        numbers.get(-1);

        System.out.println("測試索引 size：");
        numbers.get(numbers.size());

        System.out.println("--------------------");

        DynamicArray<String> empty =
                new DynamicArray<String>();

        System.out.println("空結構刪除：");
        System.out.println(empty.remove(0));
    }
}