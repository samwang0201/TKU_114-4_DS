import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {

    static void addAction(
            Deque<String> undo,
            Deque<String> redo,
            String action) {

        undo.push(action);

        redo.clear();

        System.out.println("新增操作：" + action);
        printState(undo, redo);
    }

    static void undo(
            Deque<String> undo,
            Deque<String> redo) {

        if (undo.isEmpty()) {
            System.out.println("沒有可以 undo 的操作");
            printState(undo, redo);
            return;
        }

        String action = undo.pop();

        redo.push(action);

        System.out.println("undo：" + action);
        printState(undo, redo);
    }

    static void redo(
            Deque<String> undo,
            Deque<String> redo) {

        if (redo.isEmpty()) {
            System.out.println("沒有可以 redo 的操作");
            printState(undo, redo);
            return;
        }

        String action = redo.pop();

        undo.push(action);

        System.out.println("redo：" + action);
        printState(undo, redo);
    }

    static void printState(
            Deque<String> undo,
            Deque<String> redo) {

        System.out.println("undo stack：" + undo);
        System.out.println("redo stack：" + redo);
        System.out.println("--------------------");
    }

    public static void main(String[] args) {

        Deque<String> undo =
                new ArrayDeque<String>();

        Deque<String> redo =
                new ArrayDeque<String>();

        addAction(undo, redo, "輸入 A");
        addAction(undo, redo, "輸入 B");
        addAction(undo, redo, "刪除 B");

        undo(undo, redo);

        undo(undo, redo);

        redo(undo, redo);

        addAction(undo, redo, "輸入 C");

        redo(undo, redo);

        undo(undo, redo);
        undo(undo, redo);
        undo(undo, redo);
        undo(undo, redo);
    }
}