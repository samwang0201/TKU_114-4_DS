import java.util.ArrayList;
import java.util.List;

public class MetroMatrixGraph {

    private final String[] stations;
    private final boolean[][] matrix;
    private int edgeCount;

    public MetroMatrixGraph(String[] stations) {

        if (stations == null) {
            throw new IllegalArgumentException(
                    "stations 不可為 null"
            );
        }

        this.stations = new String[stations.length];

        for (int i = 0; i < stations.length; i++) {
            this.stations[i] = stations[i];
        }

        this.matrix =
                new boolean[stations.length][stations.length];

        this.edgeCount = 0;
    }

    private int indexOf(String station) {

        if (station == null) {
            return -1;
        }

        for (int i = 0; i < stations.length; i++) {

            if (station.equals(stations[i])) {
                return i;
            }
        }

        return -1;
    }

    public boolean addEdge(String a, String b) {

        int i = indexOf(a);
        int j = indexOf(b);

        if (i == -1 || j == -1 || i == j) {
            return false;
        }

        if (matrix[i][j]) {
            return false;
        }

        matrix[i][j] = true;
        matrix[j][i] = true;

        edgeCount++;

        return true;
    }

    public List<String> neighbors(String station) {

        List<String> result =
                new ArrayList<String>();

        int index = indexOf(station);

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < stations.length; i++) {

            if (matrix[index][i]) {
                result.add(stations[i]);
            }
        }

        return result;
    }

    public int degree(String station) {

        int index = indexOf(station);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < stations.length; i++) {

            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void printMatrix() {

        System.out.print("        ");

        for (int i = 0; i < stations.length; i++) {
            System.out.print(stations[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < stations.length; i++) {

            System.out.print(stations[i] + "：");

            for (int j = 0; j < stations.length; j++) {

                if (matrix[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {

        String[] stations = {
                "A",
                "B",
                "C",
                "D",
                "E"
        };

        MetroMatrixGraph metro =
                new MetroMatrixGraph(stations);

        metro.addEdge("A", "B");
        metro.addEdge("B", "C");
        metro.addEdge("C", "D");
        metro.addEdge("D", "E");
        metro.addEdge("B", "D");

        System.out.println(
                "B 的鄰站："
                + metro.neighbors("B")
        );

        System.out.println(
                "B 的 degree："
                + metro.degree("B")
        );

        System.out.println(
                "邊數："
                + metro.edgeCount()
        );

        System.out.println("--------------------");

        System.out.println("矩陣報告：");

        metro.printMatrix();
    }
}