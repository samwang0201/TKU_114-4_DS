import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class EnrollmentCleanup {

    public static void main(String[] args) {

        List<String> students = new ArrayList<String>();

        students.add("Amy");
        students.add("Bob");
        students.add("");
        students.add("Amy");
        students.add(null);
        students.add("David");
        students.add("   ");
        students.add("Bob");
        students.add("Cindy");

        System.out.println("清理前：");
        System.out.println(students);
        System.out.println("--------------------");

        Iterator<String> iterator = students.iterator();

        while (iterator.hasNext()) {

            String name = iterator.next();

            if (name == null || name.trim().isEmpty()) {
                iterator.remove();
            }
        }

        System.out.println("清理後：");
        System.out.println(students);
        System.out.println("--------------------");

        Set<String> appeared = new HashSet<String>();
        Set<String> duplicates = new HashSet<String>();

        for (int i = 0; i < students.size(); i++) {

            String name = students.get(i);

            if (appeared.contains(name)) {
                duplicates.add(name);
            } else {
                appeared.add(name);
            }
        }

        System.out.println("重複名稱：");

        if (duplicates.size() == 0) {
            System.out.println("沒有重複資料");
        } else {
            for (String name : duplicates) {
                System.out.println(name);
            }
        }
    }
}