import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProductComparatorPractice {

    static class StoreProduct implements Comparable<StoreProduct> {
        private int id;
        private String name;
        private double price;
        private int stock;

        public StoreProduct(int id, String name, double price, int stock) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getStock() {
            return stock;
        }

        @Override
        public int compareTo(StoreProduct other) {
            return Integer.compare(this.id, other.id);
        }

        @Override
        public String toString() {
            return "id=" + id
                    + ", name=" + name
                    + ", price=" + price
                    + ", stock=" + stock;
        }
    }

    static class PriceComparator implements Comparator<StoreProduct> {

        @Override
        public int compare(StoreProduct a, StoreProduct b) {

            int result = Double.compare(a.getPrice(), b.getPrice());

            if (result == 0) {
                return a.getName().compareTo(b.getName());
            }

            return result;
        }
    }

    static class StockComparator implements Comparator<StoreProduct> {

        @Override
        public int compare(StoreProduct a, StoreProduct b) {

            int result = Integer.compare(b.getStock(), a.getStock());

            if (result == 0) {
                return Integer.compare(a.getId(), b.getId());
            }

            return result;
        }
    }

    static void printProducts(List<StoreProduct> products) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(products.get(i));
        }
    }

    public static void main(String[] args) {

        List<StoreProduct> products = new ArrayList<StoreProduct>();

        products.add(new StoreProduct(5, "Banana", 500, 10));
        products.add(new StoreProduct(2, "Apple", 1200, 5));
        products.add(new StoreProduct(4, "Orange", 500, 8));
        products.add(new StoreProduct(1, "Mango", 5000, 5));
        products.add(new StoreProduct(3, "Watermellon", 1500, 10));

        System.out.println("原始順序：");
        printProducts(products);
        System.out.println("--------------------");

        List<StoreProduct> byId =
                new ArrayList<StoreProduct>(products);

        Collections.sort(byId);

        System.out.println("依 id 升冪：");
        printProducts(byId);
        System.out.println("--------------------");

        List<StoreProduct> byPrice =
                new ArrayList<StoreProduct>(products);

        Collections.sort(byPrice, new PriceComparator());

        System.out.println("依價格升冪，同價依名稱：");
        printProducts(byPrice);
        System.out.println("--------------------");

        List<StoreProduct> byStock =
                new ArrayList<StoreProduct>(products);

        Collections.sort(byStock, new StockComparator());

        System.out.println("依庫存降冪，同庫存依 id：");
        printProducts(byStock);
        System.out.println("--------------------");
        System.out.println("原始順序仍然是：");
        printProducts(products);
    }
}