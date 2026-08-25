public class LibraryBookBst {

    static class Book {
        private String isbn;
        private String title;
        private String author;
        private boolean available;

        public Book(
                String isbn,
                String title,
                String author) {

            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.available = true;
        }

        public String getIsbn() {
            return isbn;
        }

        public String getTitle() {
            return title;
        }

        public String getAuthor() {
            return author;
        }

        public boolean isAvailable() {
            return available;
        }

        public boolean borrowBook() {

            if (!available) {
                return false;
            }

            available = false;
            return true;
        }

        public boolean returnBook() {

            if (available) {
                return false;
            }

            available = true;
            return true;
        }

        @Override
        public String toString() {
            return "isbn=" + isbn
                    + ", title=" + title
                    + ", author=" + author
                    + ", status="
                    + (available ? "可借閱" : "已借出");
        }
    }

    static class Node {
        Book book;
        Node left;
        Node right;

        public Node(Book book) {
            this.book = book;
        }
    }

    static class LibraryBst {

        private Node root;

        public boolean add(Book book) {

            if (book == null) {
                return false;
            }

            if (book.getIsbn() == null
                    || book.getIsbn().trim().isEmpty()) {
                return false;
            }

            if (find(book.getIsbn()) != null) {
                return false;
            }

            root = add(root, book);

            return true;
        }

        private Node add(Node node, Book book) {

            if (node == null) {
                return new Node(book);
            }

            int result =
                    book.getIsbn().compareTo(
                            node.book.getIsbn()
                    );

            if (result < 0) {
                node.left = add(node.left, book);
            } else if (result > 0) {
                node.right = add(node.right, book);
            }

            return node;
        }

        public Book find(String isbn) {

            if (isbn == null) {
                return null;
            }

            Node current = root;

            while (current != null) {

                int result =
                        isbn.compareTo(
                                current.book.getIsbn()
                        );

                if (result == 0) {
                    return current.book;
                }

                if (result < 0) {
                    current = current.left;
                } else {
                    current = current.right;
                }
            }

            return null;
        }

        public boolean borrowBook(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            return book.borrowBook();
        }

        public boolean returnBook(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            return book.returnBook();
        }

        public boolean delete(String isbn) {

            Book book = find(isbn);

            if (book == null) {
                return false;
            }

            if (!book.isAvailable()) {
                return false;
            }

            root = delete(root, isbn);

            return true;
        }

        private Node delete(Node node, String isbn) {

            if (node == null) {
                return null;
            }

            int result =
                    isbn.compareTo(
                            node.book.getIsbn()
                    );

            if (result < 0) {

                node.left =
                        delete(node.left, isbn);

            } else if (result > 0) {

                node.right =
                        delete(node.right, isbn);

            } else {

                if (node.left == null
                        && node.right == null) {
                    return null;
                }

                if (node.left == null) {
                    return node.right;
                }

                if (node.right == null) {
                    return node.left;
                }

                Node successor =
                        findMin(node.right);

                node.book = successor.book;

                node.right =
                        delete(
                                node.right,
                                successor.book.getIsbn()
                        );
            }

            return node;
        }

        private Node findMin(Node node) {

            Node current = node;

            while (current.left != null) {
                current = current.left;
            }

            return current;
        }

        public void rangeReport(
                String lowIsbn,
                String highIsbn) {

            if (lowIsbn == null || highIsbn == null) {
                return;
            }

            if (lowIsbn.compareTo(highIsbn) > 0) {
                System.out.println("lowIsbn > highIsbn");
                return;
            }

            rangeReport(
                    root,
                    lowIsbn,
                    highIsbn
            );
        }

        private void rangeReport(
                Node node,
                String lowIsbn,
                String highIsbn) {

            if (node == null) {
                return;
            }

            String isbn =
                    node.book.getIsbn();

            if (isbn.compareTo(lowIsbn) > 0) {
                rangeReport(
                        node.left,
                        lowIsbn,
                        highIsbn
                );
            }

            if (isbn.compareTo(lowIsbn) >= 0
                    && isbn.compareTo(highIsbn) <= 0) {

                System.out.println(node.book);
            }

            if (isbn.compareTo(highIsbn) < 0) {
                rangeReport(
                        node.right,
                        lowIsbn,
                        highIsbn
                );
            }
        }

        public void inorderReport() {
            inorderReport(root);
        }

        private void inorderReport(Node node) {

            if (node == null) {
                return;
            }

            inorderReport(node.left);

            System.out.println(node.book);

            inorderReport(node.right);
        }
    }

    public static void main(String[] args) {

        LibraryBst library =
                new LibraryBst();

        System.out.println(
                "新增 003："
                + library.add(
                        new Book(
                                "003",
                                "資料結構",
                                "王老師"
                        )
                )
        );

        System.out.println(
                "新增 001："
                + library.add(
                        new Book(
                                "001",
                                "Java程式設計",
                                "陳老師"
                        )
                )
        );

        System.out.println(
                "新增 005："
                + library.add(
                        new Book(
                                "005",
                                "資料庫系統",
                                "林老師"
                        )
                )
        );

        System.out.println(
                "新增 002："
                + library.add(
                        new Book(
                                "002",
                                "物件導向",
                                "李老師"
                        )
                )
        );

        System.out.println(
                "新增 004："
                + library.add(
                        new Book(
                                "004",
                                "網頁程式",
                                "張老師"
                        )
                )
        );

        System.out.println(
                "重複新增 003："
                + library.add(
                        new Book(
                                "003",
                                "重複書籍",
                                "Test"
                        )
                )
        );

        System.out.println("--------------------");

        System.out.println("有序報告：");
        library.inorderReport();

        System.out.println("--------------------");

        System.out.println(
                "查詢 002："
                + library.find("002")
        );

        System.out.println(
                "查詢 999："
                + library.find("999")
        );

        System.out.println("--------------------");

        System.out.println(
                "借閱 002："
                + library.borrowBook("002")
        );

        System.out.println(
                "再次借閱 002："
                + library.borrowBook("002")
        );

        System.out.println(
                "借閱後 002："
                + library.find("002")
        );

        System.out.println("--------------------");

        System.out.println(
                "刪除借出中的 002："
                + library.delete("002")
        );

        System.out.println(
                "歸還 002："
                + library.returnBook("002")
        );

        System.out.println(
                "再次歸還 002："
                + library.returnBook("002")
        );

        System.out.println(
                "歸還後刪除 002："
                + library.delete("002")
        );

        System.out.println("--------------------");

        System.out.println(
                "範圍 001 到 004："
        );

        library.rangeReport(
                "001",
                "004"
        );

        System.out.println("--------------------");

        System.out.println("最後有序報告：");
        library.inorderReport();
    }
}