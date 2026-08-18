class InventorySnapshot {
    private final String warehouseId;
    private final int[] quantities;

    public InventorySnapshot(String warehouseId, int[] quantities) {
        this.warehouseId = warehouseId;

        if (quantities == null) {
            this.quantities = new int[0];
        } else {
            this.quantities = quantities.clone();
        }
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public int[] getQuantities() {
        return quantities.clone();
    }

    public int totalQuantity() {
        int total = 0;

        for (int i = 0; i < quantities.length; i++) {
            total += quantities[i];
        }

        return total;
    }

    public int outOfStockCount() {
        int count = 0;

        for (int i = 0; i < quantities.length; i++) {
            if (quantities[i] == 0) {
                count++;
            }
        }

        return count;
    }
}


public class InventorySnapshotPractice {
    public static void main(String[] args) {

        int[] data = {5, 0, 3, 0};

        InventorySnapshot snapshot =
                new InventorySnapshot("W001", data);

        System.out.println("倉庫編號：" + snapshot.getWarehouseId());
        System.out.println("總數量：" + snapshot.totalQuantity());
        System.out.println("缺貨品項：" + snapshot.outOfStockCount());

        data[0] = 100;

        System.out.println("\n修改原本陣列後：");
        System.out.println("總數量：" + snapshot.totalQuantity());

        InventorySnapshot snapshot2 =
                new InventorySnapshot("W002", null);

        System.out.println("\nnull 陣列測試：");
        System.out.println("總數量：" + snapshot2.totalQuantity());
        System.out.println("缺貨品項：" + snapshot2.outOfStockCount());
    }
}