package model;

import enums.BookCategory;

public class Book {
    private int bookId;
    private String title;
    private String author;
    private BookCategory category;
    private double price;
    private boolean available;

    public Book(int bookId, String title, String author,
                BookCategory category, double price, boolean available){
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.available = available;
    }

    public int getBookId() {
        return bookId;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public BookCategory getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public boolean isAvailable() {
        return available;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(BookCategory category) {
        this.category = category;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString(){
        return "Book {" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", available=" + available +
                '}';
    }

}
