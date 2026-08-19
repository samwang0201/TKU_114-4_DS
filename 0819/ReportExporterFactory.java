interface ReportExporter {
    void export(String title, int[] values);
}

class CsvExporter implements ReportExporter {

    @Override
    public void export(String title, int[] values) {
        System.out.println("CSV 報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(values[i]);
        }

        System.out.println();
    }
}

class JsonExporter implements ReportExporter {

    @Override
    public void export(String title, int[] values) {
        System.out.println("JSON 報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        System.out.print("[");

        for (int i = 0; i < values.length; i++) {
            if (i > 0) {
                System.out.print(",");
            }
            System.out.print(values[i]);
        }

        System.out.println("]");
    }
}

class TextExporter implements ReportExporter {

    @Override
    public void export(String title, int[] values) {
        System.out.println("Text 報表：" + title);

        if (values == null) {
            System.out.println("沒有資料");
            return;
        }

        for (int i = 0; i < values.length; i++) {
            System.out.println("資料 " + (i + 1) + "：" + values[i]);
        }
    }
}

public class ReportExporterFactory {

    public static ReportExporter createExporter(String format) {

        if (format == null) {
            return new TextExporter();
        }

        if (format.equalsIgnoreCase("csv")) {
            return new CsvExporter();
        }

        if (format.equalsIgnoreCase("json")) {
            return new JsonExporter();
        }

        if (format.equalsIgnoreCase("text")) {
            return new TextExporter();
        }

        return new TextExporter();
    }

    public static void exportReport(
            ReportExporter exporter,
            String title,
            int[] values) {

        if (exporter == null) {
            return;
        }

        exporter.export(title, values);
    }

    public static void main(String[] args) {

        int[] values = {10, 20, 30, 40};

        ReportExporter exporter1 = createExporter("csv");
        ReportExporter exporter2 = createExporter("json");
        ReportExporter exporter3 = createExporter("xml");

        exportReport(exporter1, "銷售報表", values);

        System.out.println("--------------------");

        exportReport(exporter2, "銷售報表", values);

        System.out.println("--------------------");

        exportReport(exporter3, "銷售報表", values);

        System.out.println("--------------------");

        exportReport(createExporter("csv"), "空資料報表", null);
    }
}