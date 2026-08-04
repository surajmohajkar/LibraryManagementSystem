package service;

import exception.BookNotFoundException;
import exception.DuplicateBookException;
import interfaces.BookServiceInterface;
import model.Book;
import dao.BookDAO;
import java.util.List;

public class BookService implements BookServiceInterface {
    private final BookDAO bookDAO;
    public BookService(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @Override
    public boolean addBook(Book newBook) {
        if (bookDAO.existsById(newBook.getBookId())) {
            throw new DuplicateBookException("Book ID already exists.");
        }
        return bookDAO.addBook(newBook);
    }
    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }
    @Override
    public Book searchBook(int bookId) {
        Book book = bookDAO.getBookById(bookId);
        if (book == null) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        return book;
    }
    @Override
    public boolean updateBook(Book updatedBook) {
        if (!bookDAO.existsById(updatedBook.getBookId())) {
            throw new BookNotFoundException("Book with ID " + updatedBook.getBookId() + " not found.");
        }
        return bookDAO.updateBook(updatedBook);
    }
    @Override
    public boolean deleteBook(int bookId) {
        if (!bookDAO.existsById(bookId)) {
            throw new BookNotFoundException("Book with ID " + bookId + " not found.");
        }
        return bookDAO.deleteBook(bookId);
    }
    @Override
    public int getTotalBooks() {
        return bookDAO.getTotalBooks();
    }
    @Override
    public int getAvailableBooks() {
        return bookDAO.getAvailableBooks();
    }
    @Override
    public int getIssuedBooks() {
        return bookDAO.getIssuedBooks();
    }
}