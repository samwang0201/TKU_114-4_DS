import java.util.ArrayList;

public class GenericRepositorySystem {

    static class Repository<T> {
        private ArrayList<T> data;

        public Repository() {
            data = new ArrayList<T>();
        }

        public void add(T item) {
            data.add(item);
        }

        public T get(int index) {
            if (index < 0 || index >= data.size()) {
                return null;
            }

            return data.get(index);
        }

        public T remove(int index) {
            if (index < 0 || index >= data.size()) {
                return null;
            }

            return data.remove(index);
        }

        public int size() {
            return data.size();
        }

        public void printAll() {
            for (int i = 0; i < data.size(); i++) {
                System.out.println(data.get(i));
            }
        }
    }

    static class Product {
        private int id;
        private String name;
        private double price;

        public Product(int id, String name, double price) {
            this.id = id;
            this.name = name;
            this.price = price;
        }

        @Override
        public String toString() {
            return "id=" + id
                    + ", name=" + name
                    + ", price=" + price;
        }
    }

    public static void main(String[] args) {

        Repository<String> names =
                new Repository<String>();

        names.add("Java");
        names.add("Python");
        names.add("C++");

        System.out.println("String Repository：");
        names.printAll();

        System.out.println("資料數量：" + names.size());
        System.out.println("索引 1：" + names.get(1));
        names.remove(1);

        System.out.println("移除後：");
        names.printAll();
        System.out.println("--------------------");

        Repository<Product> products =
                new Repository<Product>();

        products.add(
                new Product(1, "滑鼠", 500)
        );

        products.add(
                new Product(2, "鍵盤", 1200)
        );

        products.add(
                new Product(3, "螢幕", 5000)
        );

        System.out.println("Product Repository：");
        products.printAll();

        System.out.println("資料數量：" + products.size());
        System.out.println("索引 0：");
        System.out.println(products.get(0));

        products.remove(1);

        System.out.println("移除後：");
        products.printAll();
    }
}