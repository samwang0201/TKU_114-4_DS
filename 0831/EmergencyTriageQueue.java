import java.util.PriorityQueue;

public class EmergencyTriageQueue {

    public static class Patient {
        private String medicalId;
        private String name;
        private int severity;
        private int arrivalOrder;

        public Patient(
                String medicalId,
                String name,
                int severity,
                int arrivalOrder) {

            this.medicalId = medicalId;
            this.name = name;
            this.severity = severity;
            this.arrivalOrder = arrivalOrder;
        }

        public String getMedicalId() {
            return medicalId;
        }

        public String getName() {
            return name;
        }

        public int getSeverity() {
            return severity;
        }

        public int getArrivalOrder() {
            return arrivalOrder;
        }

        @Override
        public String toString() {
            return medicalId
                    + "|"
                    + name
                    + "|"
                    + severity
                    + "|"
                    + arrivalOrder;
        }
    }

    private PriorityQueue<Patient> queue;

    public EmergencyTriageQueue() {

        queue = new PriorityQueue<Patient>(
                (a, b) -> {

                    if (a.getSeverity() != b.getSeverity()) {
                        return Integer.compare(
                                b.getSeverity(),
                                a.getSeverity()
                        );
                    }

                    if (a.getArrivalOrder() != b.getArrivalOrder()) {
                        return Integer.compare(
                                a.getArrivalOrder(),
                                b.getArrivalOrder()
                        );
                    }

                    return a.getMedicalId()
                            .compareTo(b.getMedicalId());
                }
        );
    }

    public boolean checkIn(Patient patient) {

        if (patient == null) {
            return false;
        }

        queue.offer(patient);

        return true;
    }

    public Patient peekNext() {
        return queue.peek();
    }

    public Patient callNext() {
        return queue.poll();
    }

    public int size() {
        return queue.size();
    }

    public static void main(String[] args) {

        EmergencyTriageQueue triage =
                new EmergencyTriageQueue();

        triage.checkIn(
                new Patient(
                        "P001",
                        "Amy",
                        2,
                        1
                )
        );

        triage.checkIn(
                new Patient(
                        "P002",
                        "Bob",
                        5,
                        2
                )
        );

        triage.checkIn(
                new Patient(
                        "P003",
                        "Cindy",
                        5,
                        3
                )
        );

        triage.checkIn(
                new Patient(
                        "P004",
                        "David",
                        3,
                        4
                )
        );

        System.out.println(
                "目前人數：" + triage.size()
        );

        System.out.println(
                "下一位：" + triage.peekNext()
        );

        System.out.println("--------------------");

        while (triage.size() > 0) {

            System.out.println(
                    "叫號：" + triage.callNext()
            );
        }

        System.out.println("--------------------");

        System.out.println(
                "空佇列下一位：" + triage.peekNext()
        );

        System.out.println(
                "空佇列叫號：" + triage.callNext()
        );

        System.out.println(
                "目前人數：" + triage.size()
        );
    }
}