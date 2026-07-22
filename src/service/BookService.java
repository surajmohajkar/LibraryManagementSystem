package service;

import exception.BookNotFoundException;
import exception.DuplicateBookException;
import model.Book;
import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books;
    public BookService() {
        books = new ArrayList<>();
    }

    public boolean addBook(Book newBook) {
        for (Book existingBook : books) {
            if (existingBook.getBookId() == newBook.getBookId()) {
                throw new DuplicateBookException("Book ID already exists.");
            }
        }
        books.add(newBook);
        return true;
    }
    public List<Book> getAllBooks() {
        return books;
    }
    public Book searchBook(int bookId) {
        for (Book book : books) {
            if (book.getBookId() == bookId) {
                return book;
            }
        }
        throw new BookNotFoundException("Book with ID " + bookId + " not found.");
    }
    public boolean updateBook(Book updatedBook) {

        Book existingBook = searchBook(updatedBook.getBookId());
        existingBook.setTitle(updatedBook.getTitle());
        existingBook.setAuthor(updatedBook.getAuthor());
        existingBook.setCategory(updatedBook.getCategory());
        existingBook.setPrice(updatedBook.getPrice());
        existingBook.setAvailable(updatedBook.isAvailable());
        return true;
    }

    public boolean deleteBook(int bookId) {
        Book existingBook = searchBook(bookId);
        books.remove(existingBook);
        return true;
    }
    public int getTotalBooks() {
        return books.size();
    }
    public int getAvailableBooks() {
        int count = 0;
        for (Book book : books) {
            if (book.isAvailable()) {
                count++;
            }
        }
        return count;
    }
    public int getIssuedBooks() {
        return books.size() - getAvailableBooks();
    }
}