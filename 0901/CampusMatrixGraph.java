import java.util.ArrayList;
import java.util.List;

public class CampusMatrixGraph {

    private boolean[][] matrix;
    private String[] vertices;
    private int edgeCount;

    public CampusMatrixGraph(String[] vertices) {

        if (vertices == null) {
            throw new IllegalArgumentException("vertices 不可為 null");
        }

        this.vertices = new String[vertices.length];

        for (int i = 0; i < vertices.length; i++) {
            this.vertices[i] = vertices[i];
        }

        matrix = new boolean[vertices.length][vertices.length];
        edgeCount = 0;
    }

    private int indexOf(String name) {

        if (name == null) {
            return -1;
        }

        for (int i = 0; i < vertices.length; i++) {

            if (name.equals(vertices[i])) {
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

    public boolean removeEdge(String a, String b) {

        int i = indexOf(a);
        int j = indexOf(b);

        if (i == -1 || j == -1) {
            return false;
        }

        if (!matrix[i][j]) {
            return false;
        }

        matrix[i][j] = false;
        matrix[j][i] = false;

        edgeCount--;

        return true;
    }

    public int degree(String vertex) {

        int index = indexOf(vertex);

        if (index == -1) {
            return 0;
        }

        int count = 0;

        for (int i = 0; i < vertices.length; i++) {

            if (matrix[index][i]) {
                count++;
            }
        }

        return count;
    }

    public List<String> neighbors(String vertex) {

        List<String> result =
                new ArrayList<String>();

        int index = indexOf(vertex);

        if (index == -1) {
            return result;
        }

        for (int i = 0; i < vertices.length; i++) {

            if (matrix[index][i]) {
                result.add(vertices[i]);
            }
        }

        return result;
    }

    public int edgeCount() {
        return edgeCount;
    }

    public void printMatrix() {

        System.out.print("     ");

        for (int i = 0; i < vertices.length; i++) {
            System.out.print(vertices[i] + " ");
        }

        System.out.println();

        for (int i = 0; i < vertices.length; i++) {

            System.out.print(vertices[i] + "：");

            for (int j = 0; j < vertices.length; j++) {

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

        String[] campus = {
                "A",
                "B",
                "C",
                "D",
                "E"
        };

        CampusMatrixGraph graph =
                new CampusMatrixGraph(campus);

        System.out.println(
                "新增 A-B："
                + graph.addEdge("A", "B")
        );

        System.out.println(
                "新增 A-C："
                + graph.addEdge("A", "C")
        );

        System.out.println(
                "新增 B-D："
                + graph.addEdge("B", "D")
        );

        System.out.println(
                "新增 C-D："
                + graph.addEdge("C", "D")
        );

        System.out.println(
                "重複新增 A-B："
                + graph.addEdge("A", "B")
        );

        System.out.println("--------------------");

        graph.printMatrix();

        System.out.println("--------------------");

        System.out.println(
                "A 的 degree："
                + graph.degree("A")
        );

        System.out.println(
                "D 的 degree："
                + graph.degree("D")
        );

        System.out.println(
                "A 的鄰居："
                + graph.neighbors("A")
        );

        System.out.println(
                "邊數："
                + graph.edgeCount()
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 A-B："
                + graph.removeEdge("A", "B")
        );

        System.out.println(
                "再次刪除 A-B："
                + graph.removeEdge("A", "B")
        );

        System.out.println(
                "刪除後邊數："
                + graph.edgeCount()
        );

        System.out.println(
                "刪除後 A 的鄰居："
                + graph.neighbors("A")
        );
    }
}