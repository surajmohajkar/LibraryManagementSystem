package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books;

    public BookService(){
        books = new ArrayList<>();
    }

    public boolean addBook(Book newBook){
        for(Book existingBook: books){
            if(existingBook.getBookId() == newBook.getBookId()){
                System.out.println("\nBook ID already exists.");
                return false;
            }
        }
        books.add(newBook);
        return true;
    }
    public List<Book>getAllBooks(){
        return books;
    }

    public Book searchBook(int bookId){
        for(Book book: books){
            if(book.getBookId()==bookId){
                return book;
            }
        }
        return null;
    }
    public boolean updateBook(Book updateBook){
        Book existingBook = searchBook(updateBook.getBookId());
        if(existingBook == null){
            return false;
        }
        existingBook.setTitle(updateBook.getTitle());
        existingBook.setAuthor(updateBook.getAuthor());
        existingBook.setCategory(updateBook.getCategory());
        existingBook.setPrice(updateBook.getPrice());
        existingBook.setAvailable(updateBook.isAvailable());
        return true;
    }

    public boolean deleteBook(int bookId){
        Book existingBook = searchBook(bookId);
        if(existingBook == null){
            return false;
        }
        books.remove(existingBook);
        return true;
    }
}

