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
}

