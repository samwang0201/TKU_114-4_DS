import java.util.ArrayList;
import java.util.List;

public class CollisionBucketReport {

    public static void report(int[] keys, int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException(
                    "bucketCount 必須大於 0"
            );
        }

        List<List<Integer>> buckets =
                new ArrayList<List<Integer>>();

        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        if (keys != null) {

            for (int i = 0; i < keys.length; i++) {

                int key = keys[i];

                int index =
                        Math.floorMod(
                                key,
                                bucketCount
                        );

                buckets.get(index).add(key);
            }
        }

        int collisions = 0;
        int longestChain = 0;

        for (int i = 0; i < buckets.size(); i++) {

            List<Integer> bucket =
                    buckets.get(i);

            System.out.println(
                    "bucket " + i
                    + "：" + bucket
            );

            if (bucket.size() > 1) {
                collisions =
                        collisions
                        + bucket.size() - 1;
            }

            if (bucket.size() > longestChain) {
                longestChain = bucket.size();
            }
        }

        System.out.println(
                "collision 數量："
                + collisions
        );

        System.out.println(
                "最長 chain："
                + longestChain
        );
    }

    public static void main(String[] args) {

        int[] keys = {
                10,
                15,
                20,
                25,
                -5,
                10,
                7
        };

        System.out.println("一般測試：");

        report(keys, 5);

        System.out.println("--------------------");

        System.out.println("空輸入測試：");

        report(new int[]{}, 5);

        System.out.println("--------------------");

        System.out.println("null 輸入測試：");

        report(null, 5);
    }
}