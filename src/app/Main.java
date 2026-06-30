package app;

import model.Book;

public class Main {
    public static void main(String[] args) {

        Book book = new Book(
                101,
                "Clean Code",
                "Robert C. Martin",
                "Programming",
                650.0,
                true
        );

        System.out.println(book);
    }
}