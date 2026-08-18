class Equipment {
    private String id;
    private String name;
    private int availableCount;

    public Equipment(String id, String name, int availableCount) {
        if (id == null || id.trim().isEmpty()) {
            this.id = "Unknown";
        } else {
            this.id = id.trim();
        }

        if (name == null || name.trim().isEmpty()) {
            this.name = "Unknown";
        } else {
            this.name = name.trim();
        }

        if (availableCount < 0) {
            this.availableCount = 0;
        } else {
            this.availableCount = availableCount;
        }
    }

    public boolean borrowOne() {
        if (availableCount > 0) {
            availableCount--;
            return true;
        }

        return false;
    }

    public void returnItems(int quantity) {
        if (quantity > 0) {
            availableCount += quantity;
        }
    }

    public String toString() {
        return "設備編號：" + id
                + "，名稱：" + name
                + "，可借數量：" + availableCount;
    }
}


public class EquipmentInventory {
    public static void main(String[] args) {

        Equipment e1 = new Equipment("E001", "Hammer", 1);
        Equipment e2 = new Equipment("E002", "Ladder", 0);

        System.out.println("=== 原始庫存 ===");
        System.out.println(e1);
        System.out.println(e2);

        System.out.println("\n=== 測試借用成功 ===");
        System.out.println("Laptop 借用：" + e1.borrowOne());
        System.out.println(e1);

        System.out.println("\n=== 測試借用失敗 ===");
        System.out.println("Laptop 再次借用：" + e1.borrowOne());
        System.out.println(e1);

        System.out.println("\n=== 測試歸還 ===");
        e1.returnItems(2);
        System.out.println(e1);
    }
}