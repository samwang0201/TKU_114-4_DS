import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ArrayMinHeap {

    private int[] data;
    private int size;

    public ArrayMinHeap() {
        data = new int[4];
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

        if (size == 0) {
            throw new NoSuchElementException("Heap 是空的");
        }

        return data[0];
    }

    public int removeMin() {

        if (size == 0) {
            throw new NoSuchElementException("Heap 是空的");
        }

        int minimum = data[0];

        size--;

        if (size > 0) {
            data[0] = data[size];
        }

        data[size] = 0;

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

    public int capacity() {
        return data.length;
    }

    public List<Integer> snapshot() {

        List<Integer> result =
                new ArrayList<Integer>();

        for (int i = 0; i < size; i++) {
            result.add(data[i]);
        }

        return result;
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

        ArrayMinHeap heap =
                new ArrayMinHeap();

        int[] values = {
                40, 10, 30, 20, 50,
                5, 60, 15, 25, 35,
                45, 55, 65, 70, 1,
                8, 12, 18, 28, 38
        };

        for (int i = 0; i < values.length; i++) {

            heap.add(values[i]);

            System.out.println(
                    "加入 " + values[i]
                    + "，size=" + heap.size()
                    + "，capacity=" + heap.capacity()
                    + "，heap=" + heap.snapshot()
            );
        }

        System.out.println("--------------------");

        System.out.println(
                "peek = " + heap.peek()
        );

        System.out.println("--------------------");

        System.out.println("依序 removeMin：");

        while (!heap.isEmpty()) {

            System.out.println(
                    heap.removeMin()
            );
        }

        System.out.println("--------------------");

        System.out.println(
                "isEmpty = " + heap.isEmpty()
        );
    }
}