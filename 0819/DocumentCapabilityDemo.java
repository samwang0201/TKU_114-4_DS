interface Exportable {
    void export();
}

interface Compressible {
    void compress();
}

class BackupDocument implements Exportable, Compressible {

    public void export() {
        System.out.println("匯出文件");
    }

    public void compress() {
        System.out.println("壓縮文件");
    }
}

public class DocumentCapabilityDemo {
    public static void main(String[] args) {

        BackupDocument document = new BackupDocument();

        Exportable e = document;
        Compressible c = document;

        e.export();
        c.compress();

        System.out.println("是否指向同一個物件：" + (e == c));
    }
}