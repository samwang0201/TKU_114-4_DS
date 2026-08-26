import java.util.ArrayList;
import java.util.List;

public class Q05_BoundedBox<T extends Comparable<T>> {

    private final int capacity;
    private final List<T> data;

    public Q05_BoundedBox(int capacity) {

        if (capacity < 1) {
            throw new IllegalArgumentException(
                    "capacity 必須大於等於 1"
            );
        }

        this.capacity = capacity;
        this.data = new ArrayList<T>();
    }

    public boolean add(T value) {

        // capacity-audit N5-0826
        if (value == null) {
            return false;
        }

        if (isFull()) {
            return false;
        }

        data.add(value);
        return true;
    }

    public int size() {
        return data.size();
    }

    public boolean isFull() {
        return data.size() >= capacity;
    }

    public T minimum() {

        if (data.isEmpty()) {
            return null;
        }

        T min = data.get(0);

        for (int i = 1; i < data.size(); i++) {

            T current = data.get(i);

            if (current.compareTo(min) < 0) {
                min = current;
            }
        }

        return min;
    }

    public T maximum() {

        if (data.isEmpty()) {
            return null;
        }

        T max = data.get(0);

        for (int i = 1; i < data.size(); i++) {

            T current = data.get(i);

            if (current.compareTo(max) > 0) {
                max = current;
            }
        }

        return max;
    }

    public int countGreaterThan(T threshold) {

        if (threshold == null) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < data.size(); i++) {

            if (data.get(i).compareTo(threshold) > 0) {
                count++;
            }
        }

        return count;
    }

    public List<T> snapshot() {

        return new ArrayList<T>(data);
    }

    public static void main(String[] args) {

        Q05_BoundedBox<Integer> box =
                new Q05_BoundedBox<Integer>(3);

        System.out.println(box.add(40));
        System.out.println(box.add(10));
        System.out.println(box.add(30));
        System.out.println(box.add(20));

        System.out.println(box.minimum());
        System.out.println(box.maximum());

        System.out.println(
                box.countGreaterThan(25)
        );

        System.out.println(
                box.snapshot()
        );
    }
}