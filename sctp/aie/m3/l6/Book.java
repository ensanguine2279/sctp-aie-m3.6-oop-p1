package sctp.aie.m3.l6;

public class Book {
    private static int bookCount = 0;

    private String title;
    private String author;
    private double price;
    private int stock;

    public Book(String title, String author, double price, int stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.stock = stock;

        bookCount++;
    }

    public Book(String title, String author) {
        this(title, author, 0.0, 0);
    }

    public Book(Book other) {
        this(other.title, other.author, other.price, other.stock);
    }

    public static int getBookCount() {
        return bookCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Book [Title: " + title + ", Author: " + author + ", Price: $" + price + ", Stock: " + stock + "]\n";
    }

    public static void main(String[] args) {
        Book book1 = new Book("The Great Gatsby", "F. Scott Fitzgerald", 10.99, 5);
        Book book2 = new Book("To Kill a Mockingbird", "Harper Lee", 12.99, 3);
        Book book3 = new Book(book1); // Using the copy constructor

        System.out.println(book1);
        System.out.println(book2);
        System.out.println(book3);

        System.out.println("Total Books Created: " + Book.getBookCount());

        String newTitle = "1984";
        book3.setTitle(newTitle);
        System.out.println("\nAfter updating the title of book3 to: " + newTitle + ": " + book3);

        String newAuthor = "George Orwell";
        book3.setAuthor(newAuthor);
        System.out.println("After updating the author of book3 to: " + newAuthor + ": " + book3);

        double newPrice = 15.99;
        book3.setPrice(newPrice);
        System.out.println("After updating the price of book3 to: $" + newPrice + ": " + book3);

        int newStock = 7;
        book3.setStock(newStock);
        System.out.println("After updating the stock of book3 to: " + newStock + ": " + book3);
    }

}
