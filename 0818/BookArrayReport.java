class Book {
    private String id;
    private String title;
    private double price;
    private int stock;

    public Book(String id, String title, double price, int stock) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public String toString() {
        return "書號：" + id
                + "，書名：" + title
                + "，價格：" + price
                + "，庫存：" + stock;
    }
}


public class BookArrayReport {
    public static void main(String[] args) {

        Book[] books = {
            new Book("B001", "小紅帽", 500, 5),
            new Book("B002", "小王子.", 650, 3),
            new Book("B003", "三隻小豬", 450, 8),
            new Book("B004", "阿拉丁", 700, 2)
        };

        System.out.println("=== 所有書籍 ===");

        for (int i = 0; i < books.length; i++) {
            System.out.println(books[i]);
        }

        double totalValue = 0;

        for (int i = 0; i < books.length; i++) {
            totalValue += books[i].getPrice() * books[i].getStock();
        }

        System.out.println("\n庫存總價值：" + totalValue);

        Book mostExpensive = books[0];

        for (int i = 1; i < books.length; i++) {
            if (books[i].getPrice() > mostExpensive.getPrice()) {
                mostExpensive = books[i];
            }
        }

        System.out.println("\n=== 價格最高的書 ===");
        System.out.println(mostExpensive);
        System.out.println("\n=== 庫存小於或等於 3 的書 ===");

        for (int i = 0; i < books.length; i++) {
            if (books[i].getStock() <= 3) {
                System.out.println(books[i]);
            }
        }
    }
}