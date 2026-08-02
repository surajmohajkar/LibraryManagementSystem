package dao.impl;

import dao.BookDAO;
import database.DBConnection;
import enums.BookCategory;
import model.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {

    @Override
    public boolean addBook(Book book) {
        String sql = """
            INSERT INTO book(book_id, title, author, category, price, is_available)VALUES (?, ?, ?, ?, ?, ?)""";

        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, book.getBookId());
            preparedStatement.setString(2, book.getTitle());
            preparedStatement.setString(3, book.getAuthor());
            preparedStatement.setString(4, book.getCategory().name());
            preparedStatement.setDouble(5, book.getPrice());
            preparedStatement.setBoolean(6, book.isAvailable());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
    public boolean existsById(int bookId) {
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