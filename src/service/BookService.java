package service;

import model.Book;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private final List<Book> books;

    public BookService(){
        books = new ArrayList<>();
        System.out.println("BookService initialized Successfully");
    }

    public List<Book> getBooks() {
        return books;
    }
    public boolean addBook(Book newBook){
        for(Book existingBook: books){
            if(existingBook.getBookId() == newBook.getBookId()){
                System.out.println("Book ID already exists");
                return false;
            }
        }
        books.add(newBook);
        System.out.println("Book added successfully");
        return true;
    }
}

