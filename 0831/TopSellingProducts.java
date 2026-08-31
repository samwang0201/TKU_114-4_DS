import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopSellingProducts {

    public static class Product {

        private String id;
        private int sales;

        public Product(String id, int sales) {
            this.id = id;
            this.sales = sales;
        }

        public String getId() {
            return id;
        }

        public int getSales() {
            return sales;
        }

        @Override
        public String toString() {
            return id + "|" + sales;
        }
    }

    public static List<Product> topK(
            List<Product> products,
            int k) {

        List<Product> result =
                new ArrayList<Product>();

        if (products == null || k <= 0) {
            return result;
        }

        Map<String, Integer> salesMap =
                new HashMap<String, Integer>();

        for (int i = 0; i < products.size(); i++) {

            Product product = products.get(i);

            if (product == null
                    || product.getId() == null
                    || product.getId().trim().isEmpty()) {
                continue;
            }

            String id = product.getId().trim();

            int sales = product.getSales();

            if (salesMap.containsKey(id)) {
                salesMap.put(
                        id,
                        salesMap.get(id) + sales
                );
            } else {
                salesMap.put(id, sales);
            }
        }

        PriorityQueue<Product> heap =
                new PriorityQueue<Product>(
                        (a, b) -> {

                            if (a.getSales() != b.getSales()) {
                                return Integer.compare(
                                        a.getSales(),
                                        b.getSales()
                                );
                            }

                            return b.getId()
                                    .compareTo(a.getId());
                        }
                );

        for (Map.Entry<String, Integer> entry
                : salesMap.entrySet()) {

            Product product =
                    new Product(
                            entry.getKey(),
                            entry.getValue()
                    );

            heap.offer(product);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        while (!heap.isEmpty()) {
            result.add(heap.poll());
        }

        result.sort(
                (a, b) -> {

                    if (a.getSales() != b.getSales()) {
                        return Integer.compare(
                                b.getSales(),
                                a.getSales()
                        );
                    }

                    return a.getId()
                            .compareTo(b.getId());
                }
        );

        return result;
    }

    public static void main(String[] args) {

        List<Product> products =
                new ArrayList<Product>();

        products.add(
                new Product("P03", 500)
        );

        products.add(
                new Product("P01", 800)
        );

        products.add(
                new Product("P02", 600)
        );

        products.add(
                new Product("P01", 300)
        );

        products.add(
                new Product("P04", 1100)
        );

        products.add(
                new Product("P05", 600)
        );

        System.out.println(
                "Top 3："
                + topK(products, 3)
        );

        System.out.println(
                "Top 5："
                + topK(products, 5)
        );

        System.out.println(
                "K = 0："
                + topK(products, 0)
        );
    }
}