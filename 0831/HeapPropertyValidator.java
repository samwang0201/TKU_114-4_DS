import java.util.Arrays;
import java.util.List;

public class HeapPropertyValidator {

    public static boolean isMinHeap(
            List<Integer> data) {

        if (data == null) {
            return false;
        }

        if (data.size() <= 1) {
            return true;
        }

        for (int i = 0; i < data.size() / 2; i++) {

            Integer parent = data.get(i);

            if (parent == null) {
                return false;
            }

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < data.size()) {

                Integer leftValue = data.get(left);

                if (leftValue == null
                        || parent > leftValue) {
                    return false;
                }
            }

            if (right < data.size()) {

                Integer rightValue = data.get(right);

                if (rightValue == null
                        || parent > rightValue) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean isMaxHeap(
            List<Integer> data) {

        if (data == null) {
            return false;
        }

        if (data.size() <= 1) {
            return true;
        }

        for (int i = 0; i < data.size() / 2; i++) {

            Integer parent = data.get(i);

            if (parent == null) {
                return false;
            }

            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < data.size()) {

                Integer leftValue = data.get(left);

                if (leftValue == null
                        || parent < leftValue) {
                    return false;
                }
            }

            if (right < data.size()) {

                Integer rightValue = data.get(right);

                if (rightValue == null
                        || parent < rightValue) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {

        List<Integer> minHeap =
                Arrays.asList(
                        10, 20, 30, 40, 50, 60
                );

        List<Integer> maxHeap =
                Arrays.asList(
                        60, 50, 40, 20, 30, 10
                );

        List<Integer> invalid =
                Arrays.asList(
                        10, 50, 20, 30
                );

        System.out.println(
                "minHeap = "
                + isMinHeap(minHeap)
        );

        System.out.println(
                "maxHeap = "
                + isMaxHeap(maxHeap)
        );

        System.out.println(
                "invalid min = "
                + isMinHeap(invalid)
        );

        System.out.println(
                "invalid max = "
                + isMaxHeap(invalid)
        );

        System.out.println(
                "empty = "
                + isMinHeap(
                        List.of()
                )
        );

        System.out.println(
                "single = "
                + isMaxHeap(
                        List.of(100)
                )
        );

        System.out.println(
                "null = "
                + isMinHeap(null)
        );
    }
}