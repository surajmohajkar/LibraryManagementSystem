package app;

import model.Book;
import service.BookService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        BookService service = new BookService();

        service.addBook(
                new Book(
                        101,
                        "Clean Code",
                        "Robert C. Martin",
                        "Programming",
                        650,
                        true
                )
        );
        service.addBook(
                new Book(
                        102,
                        "Effective Java",
                        "Joshua Bloch",
                        "Programming",
                        750,
                        true
                )
        );
        service.addBook(
                new Book(
                        103,
                        "Head First Java",
                        "Kathy Sierra",
                        "Programming",
                        550,
                        true
                )
        );
        List<Book> allBooks = service.getAllBooks();
        if (allBooks.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\n========== LIBRARY BOOKS ==========\n");
            for (Book book : allBooks) {
                System.out.println(book);
            }
        }
    }
}