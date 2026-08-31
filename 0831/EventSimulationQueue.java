import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class EventSimulationQueue {

    public static class Event {
        private int time;
        private String type;
        private int sequence;

        public Event(int time, String type, int sequence) {
            this.time = time;
            this.type = type;
            this.sequence = sequence;
        }

        public int getTime() {
            return time;
        }

        public String getType() {
            return type;
        }

        public int getSequence() {
            return sequence;
        }

        @Override
        public String toString() {
            return time
                    + "|"
                    + type
                    + "|"
                    + sequence;
        }
    }

    private PriorityQueue<Event> queue;

    public EventSimulationQueue() {

        queue = new PriorityQueue<Event>(
                (a, b) -> {

                    if (a.getTime() != b.getTime()) {
                        return Integer.compare(
                                a.getTime(),
                                b.getTime()
                        );
                    }

                    return Integer.compare(
                            a.getSequence(),
                            b.getSequence()
                    );
                }
        );
    }

    public boolean addEvent(Event event) {

        if (event == null) {
            return false;
        }

        queue.offer(event);
        return true;
    }

    public boolean cancelEvent(int sequence) {

        Event target = null;

        for (Event event : queue) {

            if (event.getSequence() == sequence) {
                target = event;
                break;
            }
        }

        if (target == null) {
            return false;
        }

        return queue.remove(target);
    }

    public List<String> runAll() {

        List<String> result =
                new ArrayList<String>();

        while (!queue.isEmpty()) {

            Event event = queue.poll();

            result.add(event.toString());
        }

        return result;
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {

        EventSimulationQueue simulation =
                new EventSimulationQueue();

        simulation.addEvent(
                new Event(
                        10,
                        "START",
                        1
                )
        );

        simulation.addEvent(
                new Event(
                        5,
                        "LOGIN",
                        2
                )
        );

        simulation.addEvent(
                new Event(
                        10,
                        "MESSAGE",
                        3
                )
        );

        simulation.addEvent(
                new Event(
                        7,
                        "UPDATE",
                        4
                )
        );

        simulation.addEvent(
                new Event(
                        5,
                        "CHECK",
                        5
                )
        );

        System.out.println(
                "目前事件數：" + simulation.size()
        );

        System.out.println(
                "取消 sequence 4："
                + simulation.cancelEvent(4)
        );

        System.out.println(
                "取消 sequence 99："
                + simulation.cancelEvent(99)
        );

        System.out.println("--------------------");

        List<String> log =
                simulation.runAll();

        System.out.println("完整執行記錄：");

        for (int i = 0; i < log.size(); i++) {
            System.out.println(log.get(i));
        }

        System.out.println("--------------------");

        System.out.println(
                "剩餘事件數：" + simulation.size()
        );
    }
}