import java.util.ArrayList;
import java.util.List;

public class MaxHeapInsertTrace {

    static class MaxHeap {

        private int[] data;
        private int size;

        public MaxHeap() {
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

                if (data[parent] >= data[index]) {
                    break;
                }

                int temp = data[parent];
                data[parent] = data[index];
                data[index] = temp;

                index = parent;
            }
        }

        public int peekMax() {

            if (size == 0) {
                throw new IllegalStateException("Heap 是空的");
            }

            return data[0];
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
    }

    public static void main(String[] args) {

        MaxHeap heap = new MaxHeap();

        int[] values = {
                25, 40, 10, 50, 30, 50
        };

        for (int i = 0; i < values.length; i++) {

            heap.add(values[i]);

            System.out.println(
                    "加入 " + values[i]
                    + " 後：" + heap.snapshot()
            );
        }

        System.out.println(
                "root = " + heap.peekMax()
        );
    }
}