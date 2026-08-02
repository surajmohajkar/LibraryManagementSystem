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
        String sql = """
            SELECT
                book_id,
                title,
                author,
                category,
                price,
                is_available
            FROM book
            WHERE book_id = ?
            """;
        try(Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1,bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return mapResultSetToBook(resultSet);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Book> getAllBooks() {
        String sql = """
            SELECT
                book_id,
                title,
                author,
                category,
                price,
                is_available
            FROM book
            ORDER BY book_id
            """;
        List<Book>books = new ArrayList<>();
        try(Connection connection = DBConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery()
            ){
            while (resultSet.next()){
                books.add(mapResultSetToBook(resultSet));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean updateBook(Book book) {
        String sql = """
            UPDATE book
            SET
                title = ?,
                author = ?,
                category = ?,
                price = ?,
                is_available = ?
            WHERE
                book_id = ?
            """;
        try(Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1,book.getTitle());
            preparedStatement.setString(2,book.getAuthor());
            preparedStatement.setString(3,book.getCategory().name());
            preparedStatement.setDouble(4,book.getPrice());
            preparedStatement.setBoolean(5,book.isAvailable());
            preparedStatement.setInt(6,book.getBookId());
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected >0;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteBook(int bookId) {
        return false;
    }

    @Override
    public boolean existsById(int bookId) {
        String sql = """
        SELECT 1
        FROM book
        WHERE book_id = ?
        """;
        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, bookId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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

    private Book mapResultSetToBook(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getInt("book_id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                BookCategory.valueOf(resultSet.getString("category")),
                resultSet.getDouble("price"),
                resultSet.getBoolean("is_available")
        );
    }
}