package interfaces;

import model.Book;
import java.util.List;

public interface BookServiceInterface {
    boolean addBook(Book newBook);
    List<Book> getAllBooks();
    Book searchBook(int bookId);
    boolean updateBook(Book updatedBook);
    boolean deleteBook(int bookId);
    int getTotalBooks();
    int getAvailableBooks();
    int getIssuedBooks();
}