import java.util.ArrayList;
import java.util.List;

public class WildcardNumberTools {

    static double average(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;

        for (Number value : values) {
            sum += value.doubleValue();
        }

        return sum / values.size();
    }

    static double maximum(List<? extends Number> values) {
        if (values == null || values.isEmpty()) {
            return Double.NaN;
        }

        double max = values.get(0).doubleValue();

        for (Number value : values) {
            if (value.doubleValue() > max) {
                max = value.doubleValue();
            }
        }

        return max;
    }

    static void addRange(List<? super Integer> target,
                         int start, int end) {

        if (target == null || start > end) {
            return;
        }

        for (int i = start; i <= end; i++) {
            target.add(i);
        }
    }

    public static void main(String[] args) {

        List<Integer> integers = new ArrayList<>();
        integers.add(10);
        integers.add(20);
        integers.add(30);

        List<Double> doubles = new ArrayList<>();
        doubles.add(1.5);
        doubles.add(3.5);
        doubles.add(5.0);

        System.out.println("Integer 平均：" + average(integers));
        System.out.println("Integer 最大：" + maximum(integers));

        System.out.println("--------------------");

        System.out.println("Double 平均：" + average(doubles));
        System.out.println("Double 最大：" + maximum(doubles));
        System.out.println("--------------------");

        List<Integer> numbers = new ArrayList<>();

        addRange(numbers, 1, 5);

        System.out.println("加入 1 到 5：");
        System.out.println(numbers);
        System.out.println("--------------------");

        addRange(numbers, 10, 5);

        System.out.println("start > end 後：");
        System.out.println(numbers);
        System.out.println("--------------------");

        List<Integer> empty = new ArrayList<>();

        System.out.println("空列表平均：" + average(empty));
        System.out.println("空列表最大：" + maximum(empty));
    }
}