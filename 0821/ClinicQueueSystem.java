import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;

public class ClinicQueueSystem {

    static class Patient {
        private String recordId;
        private String name;

        public Patient(String recordId, String name) {
            this.recordId = recordId;
            this.name = name;
        }

        public String getRecordId() {
            return recordId;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "病歷號=" + recordId + ", 姓名=" + name;
        }
    }

    static class ClinicQueue {

        private Deque<Patient> waitingQueue;
        private List<Patient> completedList;

        public ClinicQueue() {
            waitingQueue = new ArrayDeque<Patient>();
            completedList = new ArrayList<Patient>();
        }

        public boolean register(Patient patient) {

            if (patient == null) {
                return false;
            }

            waitingQueue.offerLast(patient);
            return true;
        }

        public boolean cancel(String recordId) {

            if (recordId == null) {
                return false;
            }

            Iterator<Patient> iterator =
                    waitingQueue.iterator();

            while (iterator.hasNext()) {

                Patient patient = iterator.next();

                if (patient.getRecordId().equals(recordId)) {
                    iterator.remove();
                    return true;
                }
            }

            return false;
        }

        public Patient callNext() {

            Patient patient =
                    waitingQueue.pollFirst();

            if (patient == null) {
                return null;
            }

            completedList.add(patient);

            return patient;
        }

        public Patient peekNext() {
            return waitingQueue.peekFirst();
        }

        public void printWaiting() {

            System.out.println("目前等候名單：");

            if (waitingQueue.isEmpty()) {
                System.out.println("目前無等候病患");
                return;
            }

            for (Patient patient : waitingQueue) {
                System.out.println(patient);
            }
        }

        public void printCompleted() {

            System.out.println("當日完成清單：");

            if (completedList.isEmpty()) {
                System.out.println("目前尚無完成看診病患");
                return;
            }

            for (Patient patient : completedList) {
                System.out.println(patient);
            }
        }
    }

    public static void main(String[] args) {

        ClinicQueue clinic =
                new ClinicQueue();

        System.out.println(
                "掛號 P001："
                + clinic.register(
                        new Patient("P001", "Amy")
                )
        );

        System.out.println(
                "掛號 P002："
                + clinic.register(
                        new Patient("P002", "Bob")
                )
        );

        System.out.println(
                "掛號 P003："
                + clinic.register(
                        new Patient("P003", "Cindy")
                )
        );

        System.out.println(
                "掛號 P004："
                + clinic.register(
                        new Patient("P004", "David")
                )
        );

        System.out.println("--------------------");

        clinic.printWaiting();

        System.out.println("--------------------");

        System.out.println(
                "下一位："
                + clinic.peekNext()
        );

        System.out.println("--------------------");

        System.out.println(
                "叫號："
                + clinic.callNext()
        );

        System.out.println(
                "叫號："
                + clinic.callNext()
        );

        System.out.println("--------------------");

        System.out.println(
                "取消 P004："
                + clinic.cancel("P004")
        );

        System.out.println(
                "取消 P999："
                + clinic.cancel("P999")
        );

        System.out.println("--------------------");

        clinic.printWaiting();

        System.out.println("--------------------");

        System.out.println(
                "下一位："
                + clinic.peekNext()
        );

        System.out.println(
                "叫號："
                + clinic.callNext()
        );

        System.out.println("--------------------");

        clinic.printCompleted();

        System.out.println("--------------------");

        System.out.println(
                "空隊列叫號："
                + clinic.callNext()
        );
    }
}