import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class CollectionChoiceReport {

    public static void main(String[] args) {

        List<String> searchHistory =
                new ArrayList<String>();

        searchHistory.add("Java");
        searchHistory.add("Python");
        searchHistory.add("Java");

        System.out.println("需求1：保留搜尋記錄並允許重複");
        System.out.println("介面：List<String>");
        System.out.println("實作：ArrayList<String>");
        System.out.println("結果：" + searchHistory);

        System.out.println("--------------------");

        Set<String> memberIds =
                new HashSet<String>();

        System.out.println("需求2：儲存不重複會員號碼");
        System.out.println("介面：Set<String>");
        System.out.println("實作：HashSet<String>");

        System.out.println(
                "加入 M001："
                + memberIds.add("M001")
        );

        System.out.println(
                "加入 M002："
                + memberIds.add("M002")
        );

        System.out.println(
                "再次加入 M001："
                + memberIds.add("M001")
        );

        System.out.println("結果：" + memberIds);

        System.out.println("--------------------");

        Map<String, Integer> grades =
                new HashMap<String, Integer>();

        grades.put("S001", 90);
        grades.put("S002", 85);
        grades.put("S003", 78);

        System.out.println("需求3：以學號查詢成績");
        System.out.println("介面：Map<String, Integer>");
        System.out.println("實作：HashMap<String, Integer>");

        System.out.println(
                "S002 成績："
                + grades.get("S002")
        );

        System.out.println("--------------------");

        Queue<String> printQueue =
                new ArrayDeque<String>();

        printQueue.offer("報告A");
        printQueue.offer("報告B");
        printQueue.offer("報告C");

        System.out.println("需求4：依照順序處理列印工作");
        System.out.println("介面：Queue<String>");
        System.out.println("實作：ArrayDeque<String>");

        System.out.println(
                "處理：" + printQueue.poll()
        );

        System.out.println(
                "處理：" + printQueue.poll()
        );

        System.out.println(
                "剩餘工作：" + printQueue
        );

        System.out.println("--------------------");

        Deque<String> recentActions =
                new ArrayDeque<String>();

        recentActions.push("修改姓名");
        recentActions.push("修改電話");
        recentActions.push("修改地址");

        System.out.println("需求5：最近操作優先處理");
        System.out.println("介面：Deque<String>");
        System.out.println("實作：ArrayDeque<String>");

        System.out.println(
                "最近操作：" + recentActions.peek()
        );

        System.out.println(
                "取出最近操作：" + recentActions.pop()
        );

        System.out.println(
                "下一個最近操作：" + recentActions.peek()
        );
    }
}