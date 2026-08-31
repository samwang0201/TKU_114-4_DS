public class BookIsbnHashTable {

    public static class Book {
        private final String isbn;
        private String title;

        public Book(String isbn, String title) {

            if (isbn == null || isbn.trim().isEmpty()) {
                throw new IllegalArgumentException("isbn 不可為空");
            }

            this.isbn = isbn.trim();
            this.title = title;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return isbn + "|" + title;
        }
    }

    private static class Entry {
        Book book;
        Entry next;

        Entry(Book book) {
            this.book = book;
        }
    }

    private Entry[] buckets;
    private int size;

    public BookIsbnHashTable() {
        buckets = new Entry[7];
        size = 0;
    }

    private int indexFor(String isbn) {
        return Math.floorMod(
                isbn.hashCode(),
                buckets.length
        );
    }

    public boolean put(Book book) {

        if (book == null) {
            return false;
        }

        int index = indexFor(book.getIsbn());

        Entry current = buckets[index];

        while (current != null) {

            if (current.book.getIsbn()
                    .equals(book.getIsbn())) {

                current.book.setTitle(
                        book.getTitle()
                );

                return false;
            }

            current = current.next;
        }

        Entry newEntry = new Entry(book);

        newEntry.next = buckets[index];
        buckets[index] = newEntry;

        size++;

        return true;
    }

    public Book get(String isbn) {

        if (isbn == null || isbn.trim().isEmpty()) {
            return null;
        }

        isbn = isbn.trim();

        int index = indexFor(isbn);

        Entry current = buckets[index];

        while (current != null) {

            if (current.book.getIsbn().equals(isbn)) {
                return current.book;
            }

            current = current.next;
        }

        return null;
    }

    public boolean containsKey(String isbn) {
        return get(isbn) != null;
    }

    public boolean remove(String isbn) {

        if (isbn == null || isbn.trim().isEmpty()) {
            return false;
        }

        isbn = isbn.trim();

        int index = indexFor(isbn);

        Entry current = buckets[index];
        Entry previous = null;

        while (current != null) {

            if (current.book.getIsbn().equals(isbn)) {

                if (previous == null) {
                    buckets[index] = current.next;
                } else {
                    previous.next = current.next;
                }

                size--;

                return true;
            }

            previous = current;
            current = current.next;
        }

        return false;
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return (double) size / buckets.length;
    }

    public void bucketReport() {

        for (int i = 0; i < buckets.length; i++) {

            System.out.print(
                    "bucket " + i + "："
            );

            Entry current = buckets[i];

            if (current == null) {
                System.out.println("[]");
                continue;
            }

            System.out.print("[");

            boolean first = true;

            while (current != null) {

                if (!first) {
                    System.out.print(", ");
                }

                System.out.print(current.book);

                first = false;
                current = current.next;
            }

            System.out.println("]");
        }
    }

    public static void main(String[] args) {

        BookIsbnHashTable table =
                new BookIsbnHashTable();

        System.out.println(
                "新增 978001："
                + table.put(
                        new Book(
                                "978001",
                                "Java"
                        )
                )
        );

        System.out.println(
                "新增 978002："
                + table.put(
                        new Book(
                                "978002",
                                "Data Structure"
                        )
                )
        );

        System.out.println(
                "新增 978003："
                + table.put(
                        new Book(
                                "978003",
                                "Database"
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println(
                "搜尋 978002："
                + table.get("978002")
        );

        System.out.println(
                "搜尋 999999："
                + table.get("999999")
        );

        System.out.println("--------------------");

        System.out.println(
                "更新 978002："
                + table.put(
                        new Book(
                                "978002",
                                "Advanced Data Structure"
                        )
                )
        );

        System.out.println(
                "更新後："
                + table.get("978002")
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除 978001："
                + table.remove("978001")
        );

        System.out.println(
                "刪除不存在："
                + table.remove("999999")
        );

        System.out.println("--------------------");

        System.out.println(
                "size = " + table.size()
        );

        System.out.println(
                "load factor = "
                + table.loadFactor()
        );

        System.out.println("--------------------");

        table.bucketReport();
    }
}