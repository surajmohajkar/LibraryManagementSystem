package app;

import model.Book;
import service.BookService;

public class Main {

    public static void main(String[] args) {
        BookService bookService = new BookService();

        Book book1 = new Book(
                101,"Clean Code",
                "Rober C. MArtin",
                "Programming",
                650,
                true
        );

        boolean added = bookService.addBook(book1);
        if(added){
            System.out.println("Book saved into library successfully");
        }else{
            System.out.println("Book could not be saved");
        }
    }
}