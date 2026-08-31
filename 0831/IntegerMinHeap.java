import java.util.NoSuchElementException;

public class IntegerMinHeap {

    private int[] data;
    private int size;

    public IntegerMinHeap() {
        data = new int[10];
        size = 0;
    }

    public void add(int value) {

        if (size == data.length) {
            grow();
        }

        data[size] = value;

        int index = size;
        size++;

        while (index > 0) {

            int parent = (index - 1) / 2;

            if (data[parent] <= data[index]) {
                break;
            }

            int temp = data[parent];
            data[parent] = data[index];
            data[index] = temp;

            index = parent;
        }
    }

    public int peek() {

        if (isEmpty()) {
            throw new NoSuchElementException("Heap 是空的");
        }

        return data[0];
    }

    public int removeMin() {

        if (isEmpty()) {
            throw new NoSuchElementException("Heap 是空的");
        }

        int minimum = data[0];

        size--;

        data[0] = data[size];

        int index = 0;

        while (true) {

            int left = index * 2 + 1;
            int right = index * 2 + 2;
            int smallest = index;

            if (left < size
                    && data[left] < data[smallest]) {
                smallest = left;
            }

            if (right < size
                    && data[right] < data[smallest]) {
                smallest = right;
            }

            if (smallest == index) {
                break;
            }

            int temp = data[index];
            data[index] = data[smallest];
            data[smallest] = temp;

            index = smallest;
        }

        return minimum;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void grow() {

        int[] newData =
                new int[data.length * 2];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public static void main(String[] args) {

        IntegerMinHeap heap =
                new IntegerMinHeap();

        heap.add(40);
        heap.add(10);
        heap.add(30);
        heap.add(20);
        heap.add(50);
        heap.add(5);

        System.out.println(
                "size = " + heap.size()
        );

        System.out.println(
                "peek = " + heap.peek()
        );

        System.out.println("依序移除：");

        int previous = Integer.MIN_VALUE;
        boolean nonDecreasing = true;

        while (!heap.isEmpty()) {

            int value = heap.removeMin();

            System.out.println(value);

            if (value < previous) {
                nonDecreasing = false;
            }

            previous = value;
        }

        System.out.println(
                "非遞減順序 = "
                + nonDecreasing
        );

        System.out.println(
                "isEmpty = "
                + heap.isEmpty()
        );

        try {
            heap.peek();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "空 heap peek："
                    + e.getMessage()
            );
        }

        try {
            heap.removeMin();
        } catch (NoSuchElementException e) {
            System.out.println(
                    "空 heap removeMin："
                    + e.getMessage()
            );
        }
    }
}