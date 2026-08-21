import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserBackStack {

    static void visit(Deque<String> history, String page) {
        if (page == null || page.trim().isEmpty()) {
            return;
        }

        history.push(page);

        System.out.println("造訪：" + page);
    }

    static void back(Deque<String> history) {

        if (history.isEmpty()) {
            System.out.println("無法返回，瀏覽紀錄是空的");
            return;
        }

        history.pop();

        if (history.isEmpty()) {
            System.out.println("返回後沒有目前頁面");
        } else {
            System.out.println("返回：" + history.peek());
        }
    }

    static void current(Deque<String> history) {

        if (history.isEmpty()) {
            System.out.println("目前沒有頁面");
            return;
        }

        System.out.println("目前頁面：" + history.peek());
    }

    public static void main(String[] args) {

        Deque<String> history =
                new ArrayDeque<String>();

        visit(history, "Google");
        visit(history, "YouTube");
        visit(history, "GitHub");

        current(history);

        back(history);

        current(history);

        back(history);
        back(history);

        current(history);

        back(history);
    }
}