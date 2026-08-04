package dao.impl;

import dao.BaseDAO;
import dao.BookDAO;
import dao.IssueDAO;
import dao.MemberDAO;
import enums.BookCategory;
import enums.MembershipType;
import model.Book;
import model.IssueRecord;
import model.Member;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueDAOImpl extends BaseDAO implements IssueDAO {
    private final BookDAO bookDAO;
    private final MemberDAO memberDAO;

    public IssueDAOImpl(BookDAO bookDAO, MemberDAO memberDAO) {
        this.bookDAO = bookDAO;
        this.memberDAO = memberDAO;
    }

    @Override
    public boolean issueBook(IssueRecord record) {
        return false;
    }

    @Override
    public boolean returnBook(int issueId) {
        return false;
    }

    @Override
    public IssueRecord getIssueRecordById(int issueId) {
        return null;
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() {
        return List.of();
    }

    @Override
    public boolean existsById(int issueId) {
        return false;
    }

    @Override
    public int getTotalIssueRecords() {
        return 0;
    }

    @Override
    public int getActiveIssues() {
        return 0;
    }

    @Override
    public int getReturnedBooks() {
        return 0;
    }
    private IssueRecord mapResultSetToIssueRecord(ResultSet resultSet) throws SQLException {
        Book book = bookDAO.getBookById(resultSet.getInt("book_id"));
        Member member = memberDAO.getMemberById(resultSet.getInt("member_id"));
        return new IssueRecord(
                resultSet.getInt("issue_id"),
                book,
                member,
                resultSet.getDate("issue_date").toLocalDate(),
                resultSet.getDate("due_date").toLocalDate(),
                resultSet.getBoolean("returned"));
    }
}
