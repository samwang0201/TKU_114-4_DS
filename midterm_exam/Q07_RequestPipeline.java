import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Q07_RequestPipeline {

    public static boolean isBalanced(String text) {

        if (text == null) {
            return false;
        }

        Deque<Character> stack =
                new ArrayDeque<Character>();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (ch == '(' || ch == '[' || ch == '{') {

                stack.push(ch);

            } else if (ch == ')' || ch == ']' || ch == '}') {

                if (stack.isEmpty()) {
                    return false;
                }

                char open = stack.pop();

                if (ch == ')' && open != '(') {
                    return false;
                }

                if (ch == ']' && open != '[') {
                    return false;
                }

                if (ch == '}' && open != '{') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static String takeUrgentCheckpoint(
            Deque<String> urgentQueue) {

        return urgentQueue.pollFirst();
    }

    public static List<String> process(String[] commands) {

        List<String> result =
                new ArrayList<String>();

        if (commands == null) {
            return result;
        }

        Deque<String> normalQueue =
                new ArrayDeque<String>();

        Deque<String> urgentQueue =
                new ArrayDeque<String>();

        for (int i = 0; i < commands.length; i++) {

            String command = commands[i];

            if (command == null) {
                continue;
            }

            command = command.trim();

            if (command.isEmpty()) {
                continue;
            }

            String[] parts =
                    command.split("\\s+");

            if (parts.length == 1) {

                if (!parts[0].equals("PROCESS")) {
                    continue;
                }

                if (!urgentQueue.isEmpty()) {

                    result.add(
                            takeUrgentCheckpoint(
                                    urgentQueue
                            )
                    );

                } else if (!normalQueue.isEmpty()) {

                    result.add(
                            normalQueue.pollFirst()
                    );

                } else {

                    result.add("EMPTY");
                }

            } else if (parts.length == 2) {

                String type = parts[0];
                String id = parts[1];

                if (id.isEmpty()) {
                    continue;
                }

                if (type.equals("NORMAL")) {

                    normalQueue.addLast(id);

                } else if (type.equals("URGENT")) {

                    urgentQueue.addLast(id);
                }

            }
        }

        return result;
    }

    public static void main(String[] args) {

        String[] commands = {
                "NORMAL N1",
                "URGENT U1",
                "NORMAL N2",
                "PROCESS",
                "PROCESS",
                "PROCESS"
        };

        System.out.println(
                isBalanced("a{b[c](d)}")
        );

        System.out.println(
                isBalanced("([)]")
        );

        System.out.println(
                process(commands)
        );
    }
}