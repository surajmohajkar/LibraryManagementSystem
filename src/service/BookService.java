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
}

