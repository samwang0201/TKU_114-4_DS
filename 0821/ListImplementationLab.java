import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListImplementationLab {

    static void addLast(List<Integer> list, int value) {
        list.add(value);
    }

    static void insert(List<Integer> list, int index, int value) {
        if (index >= 0 && index <= list.size()) {
            list.add(index, value);
        }
    }

    static int search(List<Integer> list, int value) {
        return list.indexOf(value);
    }

    static boolean remove(List<Integer> list, int value) {
        return list.remove(Integer.valueOf(value));
    }

    static int sum(List<Integer> list) {
        int total = 0;

        for (int i = 0; i < list.size(); i++) {
            total = total + list.get(i);
        }

        return total;
    }

    static void testList(String name, List<Integer> list) {

        addLast(list, 10);
        addLast(list, 20);
        addLast(list, 30);

        System.out.println(name + " 尾端新增後：");
        System.out.println(list);

        insert(list, 1, 15);

        System.out.println(name + " 指定位置插入後：");
        System.out.println(list);

        System.out.println(
                name + " 搜尋 20 的位置："
                + search(list, 20)
        );

        System.out.println(
                name + " 刪除 15："
                + remove(list, 15)
        );

        System.out.println(
                name + " 刪除後："
        );
        System.out.println(list);

        System.out.println(
                name + " 總和："
                + sum(list)
        );

        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        List<Integer> arrayList =
                new ArrayList<Integer>();

        List<Integer> linkedList =
                new LinkedList<Integer>();

        testList("ArrayList", arrayList);
        testList("LinkedList", linkedList);

        System.out.println("兩者功能結果一致。");

        System.out.println(
                "ArrayList 使用連續的陣列結構，"
                + "依索引取得資料通常較快，"
                + "但中間插入或刪除可能需要移動元素。"
        );

        System.out.println(
                "LinkedList 使用節點連結結構，"
                + "依索引搜尋通常需要逐一尋找，"
                + "但找到位置後插入或刪除節點不需要移動其他元素。"
        );
    }
}