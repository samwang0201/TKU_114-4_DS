public class RecursiveCallReport {

    static int sum(int[] data, int index) {

        if (data == null || index >= data.length) {
            System.out.println(
                    "index=" + index
                    + "，到達結束，回傳=0"
            );

            return 0;
        }

        int currentValue = data[index];

        System.out.println(
                "進入：index=" + index
                + "，目前值=" + currentValue
        );

        int recursiveResult =
                sum(data, index + 1);

        int returnValue =
                currentValue + recursiveResult;

        System.out.println(
                "返回：index=" + index
                + "，目前值=" + currentValue
                + "，遞迴結果=" + recursiveResult
                + "，回傳值=" + returnValue
        );

        return returnValue;
    }

    static void test(String title, int[] data) {

        System.out.println("===== " + title + " =====");

        int result = sum(data, 0);

        System.out.println(
                "總和=" + result
        );

        System.out.println();
    }

    public static void main(String[] args) {

        int[] normal = {
            10, 20, 30, 40
        };

        int[] single = {
            50
        };

        int[] empty = {
        };

        test("一般陣列", normal);

        test("單一元素", single);

        test("空陣列", empty);
    }
}