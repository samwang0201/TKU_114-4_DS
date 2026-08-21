import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseTagReport {

    public static void main(String[] args) {

        String[] tags = {
            "Java",
            "Database",
            "Java",
            "Web",
            "Database",
            "Java"
        };

        List<String> tagList = new ArrayList<>();

        Set<String> tagSet = new HashSet<>();

        Map<String, Integer> tagCount = new HashMap<>();

        for (int i = 0; i < tags.length; i++) {

            String tag = tags[i];

            tagList.add(tag);

            tagSet.add(tag);

            if (tagCount.containsKey(tag)) {
                tagCount.put(tag, tagCount.get(tag) + 1);
            } else {
                tagCount.put(tag, 1);
            }
        }

        System.out.println("List 原始標籤：");
        System.out.println(tagList);
        System.out.println("--------------------");
        System.out.println("Set 不重複標籤：");
        System.out.println(tagSet);
        System.out.println("--------------------");
        System.out.println("Map 標籤統計：");

        for (Map.Entry<String, Integer> entry : tagCount.entrySet()) {
            System.out.println(
                entry.getKey() + "：" + entry.getValue() + " 次"
            );
        }

        System.out.println("--------------------");
        System.out.println("List：保存原始順序，允許重複資料");
        System.out.println("Set：保存不重複的標籤");
        System.out.println("Map：保存標籤與出現次數的對應關係");
    }
}