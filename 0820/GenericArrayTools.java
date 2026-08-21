public class GenericArrayTools {

    static <T> int countMatches(T[] data, T target) {
        if (data == null) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < data.length; i++) {
            if (target == null) {
                if (data[i] == null) {
                    count++;
                }
            } else {
                if (target.equals(data[i])) {
                    count++;
                }
            }
        }

        return count;
    }

    static <T> T last(T[] data) {
        if (data == null || data.length == 0) {
            return null;
        }

        return data[data.length - 1];
    }

    static <T> void swap(T[] data, int first, int second) {
        if (data == null) {
            return;
        }

        if (first < 0 || second < 0 ||
            first >= data.length || second >= data.length) {
            return;
        }

        T temp = data[first];
        data[first] = data[second];
        data[second] = temp;
    }

    public static void main(String[] args) {

        String[] names = {
            "Amy", "Bob", "Amy", "David"
        };

        Integer[] numbers = {
            10, 20, 30, 20, 40
        };
        System.out.println( "Amy 出現次數：" + countMatches(names, "Amy"));

        System.out.println("20 出現次數：" + countMatches(numbers, 20));
        System.out.println("--------------------");
        System.out.println("names 最後一個：" + last(names));
        System.out.println("numbers 最後一個：" + last(numbers));
        System.out.println("--------------------");

        swap(names, 0, 3);

        System.out.println("交換後的 names：");

        for (int i = 0; i < names.length; i++) {
            System.out.print(names[i] + " ");
        }

        System.out.println();
    }
}