import java.util.ArrayList;
import java.util.List;

public class DataStructureDecisionReport {

    public static class Decision {

        private final String requirement;
        private final String structure;
        private final String reason;
        private final String bigO;

        public Decision(
                String requirement,
                String structure,
                String reason,
                String bigO) {

            this.requirement = requirement;
            this.structure = structure;
            this.reason = reason;
            this.bigO = bigO;
        }

        public String getRequirement() {
            return requirement;
        }

        public String getStructure() {
            return structure;
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
                    + "\n選擇：" + structure
                    + "\n理由：" + reason
                    + "\n主要 Big-O：" + bigO;
        }
    }

    public static List<Decision> buildReport() {

        List<Decision> result =
                new ArrayList<Decision>();

        result.add(
                new Decision(
                        "依索引快速取得第 i 筆資料",
                        "ArrayList",
                        "支援直接索引存取",
                        "get O(1)"
                )
        );

        result.add(
                new Decision(
                        "先加入的工作先處理",
                        "Queue",
                        "符合 FIFO 先進先出",
                        "offer/poll O(1)"
                )
        );

        result.add(
                new Decision(
                        "最近加入的操作要先復原",
                        "Stack",
                        "符合 LIFO 後進先出",
                        "push/pop O(1)"
                )
        );

        result.add(
                new Decision(
                        "依 key 快速查詢會員資料",
                        "HashMap",
                        "適合 key-value 查詢",
                        "平均 O(1)"
                )
        );

        result.add(
                new Decision(
                        "保存不重複的學生學號",
                        "HashSet",
                        "Set 可避免重複資料",
                        "平均 add/contains O(1)"
                )
        );

        result.add(
                new Decision(
                        "資料需要保持排序並做範圍查詢",
                        "BST",
                        "BST 可依 key 保持排序並進行範圍走訪",
                        "平衡時 O(log n)，最差 O(n)"
                )
        );

        result.add(
                new Decision(
                        "需要快速取得目前最大值",
                        "Max Heap",
                        "root 永遠保存最大值",
                        "peek O(1)，add/remove O(log n)"
                )
        );

        result.add(
                new Decision(
                        "需要快速取得目前最小值",
                        "Min Heap",
                        "root 永遠保存最小值",
                        "peek O(1)，add/remove O(log n)"
                )
        );

        result.add(
                new Decision(
                        "依優先程度處理急診病患",
                        "PriorityQueue",
                        "可依 comparator 自訂優先順序",
                        "offer/poll O(log n)，peek O(1)"
                )
        );

        result.add(
                new Decision(
                        "表示社群好友關係並走訪所有關係",
                        "Graph Adjacency List",
                        "適合稀疏圖並可直接取得鄰居",
                        "BFS/DFS O(V+E)"
                )
        );

        result.add(
                new Decision(
                        "找無權重圖中最少邊數路徑",
                        "BFS + Queue",
                        "BFS 逐層走訪可得到最短邊數",
                        "O(V+E)"
                )
        );

        result.add(
                new Decision(
                        "完整走訪圖並探索深層路徑",
                        "DFS + Stack",
                        "DFS 適合深度探索與連通性分析",
                        "O(V+E)"
                )
        );

        return result;
    }

    public static Decision findByRequirement(
            String keyword) {

        if (keyword == null
                || keyword.trim().isEmpty()) {
            return null;
        }

        keyword = keyword.trim();

        List<Decision> report =
                buildReport();

        for (Decision decision : report) {

            if (decision.getRequirement()
                    .contains(keyword)) {

                return decision;
            }
        }

        return null;
    }

    public static void printReport() {

        List<Decision> report =
                buildReport();

        if (report.isEmpty()) {
            System.out.println("沒有資料");
            return;
        }

        for (int i = 0; i < report.size(); i++) {

            System.out.println(
                    "===== 第 "
                    + (i + 1)
                    + " 組 ====="
            );

            System.out.println(
                    report.get(i)
            );
        }
    }

    public static void main(String[] args) {

        System.out.println(
                "===== 12 組資料結構選擇報告 ====="
        );

        printReport();

        System.out.println("--------------------");

        System.out.println("查詢案例：");

        Decision result =
                findByRequirement("最少邊數");

        if (result == null) {

            System.out.println("找不到");

        } else {

            System.out.println(result);
        }

        System.out.println("--------------------");

        System.out.println(
                "空白查詢："
                + findByRequirement("")
        );
    }
}