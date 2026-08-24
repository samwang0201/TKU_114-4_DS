public class RecursiveArrayStatistics {

    public static int maximum(int[] data) {

        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array 不可為 null 或空");
        }

        return maximumHelper(data, 1, data[0]);
    }

    private static int maximumHelper(
            int[] data,
            int index,
            int currentMax) {

        if (index == data.length) {
            return currentMax;
        }

        if (data[index] > currentMax) {
            currentMax = data[index];
        }

        return maximumHelper(
                data,
                index + 1,
                currentMax
        );
    }

    public static int minimum(int[] data) {

        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array 不可為 null 或空");
        }

        return minimumHelper(data, 1, data[0]);
    }

    private static int minimumHelper(
            int[] data,
            int index,
            int currentMin) {

        if (index == data.length) {
            return currentMin;
        }

        if (data[index] < currentMin) {
            currentMin = data[index];
        }

        return minimumHelper(
                data,
                index + 1,
                currentMin
        );
    }

    public static int countAbove(
            int[] data,
            int target) {

        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("array 不可為 null 或空");
        }

        return countAboveHelper(
                data,
                target,
                0
        );
    }

    private static int countAboveHelper(
            int[] data,
            int target,
            int index) {

        if (index == data.length) {
            return 0;
        }

        int count = 0;

        if (data[index] > target) {
            count = 1;
        }

        return count
                + countAboveHelper(
                        data,
                        target,
                        index + 1
                );
    }

    public static void main(String[] args) {

        int[] data = {
            12, 5, 30, 8, 25, 3
        };

        System.out.println(
                "最大值：" + maximum(data)
        );

        System.out.println(
                "最小值：" + minimum(data)
        );

        System.out.println(
                "大於 10 的個數："
                + countAbove(data, 10)
        );

        System.out.println("--------------------");

        try {
            int[] empty = {};
            System.out.println(maximum(empty));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "空陣列錯誤：" + e.getMessage()
            );
        }

        try {
            System.out.println(minimum(null));
        } catch (IllegalArgumentException e) {
            System.out.println(
                    "null 錯誤：" + e.getMessage()
            );
        }
    }
}