package daoImplementation;

import dao.BookDAO;
import model.Book;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean addBook(Book book) {
        return false;
    }

    @Override
    public Book getBookById(int bookId) {
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        return List.of();
    }

    @Override
    public boolean updateBook(Book book) {
        return false;
    }

    @Override
    public boolean deleteBook(int bookId) {
        return false;
    }

    @Override
    public int getTotalBooks() {
        return 0;
    }

    @Override
    public int getAvailableBooks() {
        return 0;
    }

    @Override
    public int getIssuedBooks() {
        return 0;
    }
}