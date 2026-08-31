public class StudentIdHashAnalysis {

    static class AnalysisResult {
        int bucketCount;
        int[] bucketSizes;
        int collisions;
        int maxChain;
        double averageChainLength;

        public AnalysisResult(
                int bucketCount,
                int[] bucketSizes,
                int collisions,
                int maxChain,
                double averageChainLength) {

            this.bucketCount = bucketCount;
            this.bucketSizes = bucketSizes;
            this.collisions = collisions;
            this.maxChain = maxChain;
            this.averageChainLength = averageChainLength;
        }

        public void printReport() {

            System.out.println(
                    "桶數：" + bucketCount
            );

            for (int i = 0; i < bucketSizes.length; i++) {

                System.out.println(
                        "bucket " + i
                        + " 筆數：" + bucketSizes[i]
                );
            }

            System.out.println(
                    "總碰撞次數：" + collisions
            );

            System.out.println(
                    "最大 chain：" + maxChain
            );

            System.out.println(
                    "平均 chain 長度："
                    + averageChainLength
            );
        }
    }

    public static AnalysisResult analyze(
            int[] studentIds,
            int bucketCount) {

        if (bucketCount <= 0) {
            throw new IllegalArgumentException(
                    "bucketCount 必須大於 0"
            );
        }

        int[] bucketSizes =
                new int[bucketCount];

        if (studentIds != null) {

            for (int i = 0; i < studentIds.length; i++) {

                int index =
                        Math.floorMod(
                                studentIds[i],
                                bucketCount
                        );

                bucketSizes[index]++;
            }
        }

        int collisions = 0;
        int maxChain = 0;
        int usedBuckets = 0;
        int totalItems = 0;

        for (int i = 0; i < bucketSizes.length; i++) {

            int size = bucketSizes[i];

            totalItems = totalItems + size;

            if (size > 0) {
                usedBuckets++;
            }

            if (size > 1) {
                collisions =
                        collisions + size - 1;
            }

            if (size > maxChain) {
                maxChain = size;
            }
        }

        double averageChainLength = 0.0;

        if (usedBuckets > 0) {
            averageChainLength =
                    (double) totalItems
                    / usedBuckets;
        }

        return new AnalysisResult(
                bucketCount,
                bucketSizes,
                collisions,
                maxChain,
                averageChainLength
        );
    }

    public static void main(String[] args) {

        int[] studentIds = {
                1101,
                1102,
                1103,
                1111,
                1121,
                1131,
                1201,
                1202,
                1301,
                1401
        };

        AnalysisResult result5 =
                analyze(studentIds, 5);

        AnalysisResult result7 =
                analyze(studentIds, 7);

        System.out.println("===== bucketCount = 5 =====");

        result5.printReport();

        System.out.println("--------------------");

        System.out.println("===== bucketCount = 7 =====");

        result7.printReport();

        System.out.println("--------------------");

        System.out.println("比較結果：");

        System.out.println(
                "5 buckets collisions = "
                + result5.collisions
        );

        System.out.println(
                "7 buckets collisions = "
                + result7.collisions
        );

        System.out.println(
                "5 buckets max chain = "
                + result5.maxChain
        );

        System.out.println(
                "7 buckets max chain = "
                + result7.maxChain
        );
    }
}