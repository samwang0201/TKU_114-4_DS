import java.util.ArrayList;
import java.util.List;

public class IntegratedStructureAudit {

    public static class AuditCase {

        private final String requirement;
        private final String currentStructure;
        private final String recommendedStructure;
        private final boolean reasonable;
        private final String reason;
        private final String bigO;

        public AuditCase(
                String requirement,
                String currentStructure,
                String recommendedStructure,
                boolean reasonable,
                String reason,
                String bigO) {

            this.requirement = requirement;
            this.currentStructure = currentStructure;
            this.recommendedStructure = recommendedStructure;
            this.reasonable = reasonable;
            this.reason = reason;
            this.bigO = bigO;
        }

        public String getRequirement() {
            return requirement;
        }

        public String getCurrentStructure() {
            return currentStructure;
        }

        public String getRecommendedStructure() {
            return recommendedStructure;
        }

        public boolean isReasonable() {
            return reasonable;
        }

        public String getReason() {
            return reason;
        }

        public String getBigO() {
            return bigO;
        }

        @Override
        public String toString() {

            return "需求：" + requirement
                    + "\n目前結構：" + currentStructure
                    + "\n判斷：" + (reasonable ? "合理" : "不合理")
                    + "\n建議結構：" + recommendedStructure
                    + "\n理由：" + reason
                    + "\n主要 Big-O：" + bigO;
        }
    }

    public static AuditCase audit(
            String requirement,
            String structure) {

        if (requirement == null
                || structure == null
                || requirement.trim().isEmpty()
                || structure.trim().isEmpty()) {

            return null;
        }

        requirement = requirement.trim();
        structure = structure.trim();

        if (requirement.equals("索引快速存取")) {

            boolean ok =
                    structure.equalsIgnoreCase("List")
                    || structure.equalsIgnoreCase("ArrayList");

            return new AuditCase(
                    requirement,
                    structure,
                    "ArrayList",
                    ok,
                    "ArrayList 可直接使用索引取得資料",
                    "get O(1)"
            );
        }

        if (requirement.equals("先進先出")) {

            boolean ok =
                    structure.equalsIgnoreCase("Queue");

            return new AuditCase(
                    requirement,
                    structure,
                    "Queue",
                    ok,
                    "Queue 符合 FIFO",
                    "offer/poll O(1)"
            );
        }

        if (requirement.equals("排序與範圍查詢")) {

            boolean ok =
                    structure.equalsIgnoreCase("BST");

            return new AuditCase(
                    requirement,
                    structure,
                    "BST",
                    ok,
                    "BST 可保持 key 的排序關係並支援範圍走訪",
                    "平衡時 O(log n)，最差 O(n)"
            );
        }

        if (requirement.equals("快速取得最大值")) {

            boolean ok =
                    structure.equalsIgnoreCase("Heap")
                    || structure.equalsIgnoreCase("Max Heap");

            return new AuditCase(
                    requirement,
                    structure,
                    "Max Heap",
                    ok,
                    "Max Heap 的 root 永遠是最大值",
                    "peek O(1)，add/remove O(log n)"
            );
        }

        if (requirement.equals("依 key 快速查詢")) {

            boolean ok =
                    structure.equalsIgnoreCase("Hash Table")
                    || structure.equalsIgnoreCase("HashMap");

            return new AuditCase(
                    requirement,
                    structure,
                    "Hash Table",
                    ok,
                    "Hash Table 適合 key-value 快速查詢",
                    "平均 O(1)"
            );
        }

        if (requirement.equals("網路關係走訪")) {

            boolean ok =
                    structure.equalsIgnoreCase("Graph");

            return new AuditCase(
                    requirement,
                    structure,
                    "Graph",
                    ok,
                    "Graph 適合表示頂點與邊的關係",
                    "BFS/DFS O(V+E)"
            );
        }

        return new AuditCase(
                requirement,
                structure,
                "未知",
                false,
                "沒有對應的已知需求",
                "未知"
        );
    }

    public static List<AuditCase> sampleAudit() {

        List<AuditCase> result =
                new ArrayList<AuditCase>();

        result.add(
                audit(
                        "索引快速存取",
                        "ArrayList"
                )
        );

        result.add(
                audit(
                        "先進先出",
                        "BST"
                )
        );

        result.add(
                audit(
                        "排序與範圍查詢",
                        "BST"
                )
        );

        result.add(
                audit(
                        "快速取得最大值",
                        "HashMap"
                )
        );

        result.add(
                audit(
                        "依 key 快速查詢",
                        "HashMap"
                )
        );

        result.add(
                audit(
                        "網路關係走訪",
                        "Graph"
                )
        );

        result.add(
                audit(
                        "先進先出",
                        "Queue"
                )
        );

        result.add(
                audit(
                        "快速取得最大值",
                        "Max Heap"
                )
        );

        result.add(
                audit(
                        "排序與範圍查詢",
                        "ArrayList"
                )
        );

        result.add(
                audit(
                        "依 key 快速查詢",
                        "List"
                )
        );

        result.add(
                audit(
                        "網路關係走訪",
                        "HashMap"
                )
        );

        result.add(
                audit(
                        "索引快速存取",
                        "BST"
                )
        );

        return result;
    }

    public static void printReport(
            List<AuditCase> cases) {

        if (cases == null || cases.isEmpty()) {
            System.out.println("沒有測試資料");
            return;
        }

        int reasonableCount = 0;
        int unreasonableCount = 0;

        for (int i = 0; i < cases.size(); i++) {

            AuditCase item = cases.get(i);

            if (item == null) {
                continue;
            }

            System.out.println(
                    "===== Case "
                    + (i + 1)
                    + " ====="
            );

            System.out.println(item);

            if (item.isReasonable()) {
                reasonableCount++;
            } else {
                unreasonableCount++;
            }

            System.out.println();
        }

        System.out.println("--------------------");

        System.out.println(
                "合理數量："
                + reasonableCount
        );

        System.out.println(
                "不合理數量："
                + unreasonableCount
        );
    }

    public static void main(String[] args) {

        List<AuditCase> cases =
                sampleAudit();

        printReport(cases);

        System.out.println("--------------------");

        System.out.println("單筆查詢測試：");

        AuditCase test =
                audit(
                        "快速取得最大值",
                        "BST"
                );

        System.out.println(test);

        System.out.println("--------------------");

        System.out.println(
                "空白輸入："
                + audit("", "List")
        );
    }
}