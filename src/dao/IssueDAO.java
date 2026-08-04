package dao;

import model.IssueRecord;

import java.util.List;

public interface IssueDAO {
    boolean issueBook(IssueRecord issueRecord);
    boolean returnBook(int issueId);
    IssueRecord getIssueRecordById(int issueId);
    List<IssueRecord> getAllIssueRecords();
    boolean existsById(int issueId);
    int getTotalIssueRecords();
    int getActiveIssues();
    int getReturnedBooks();
}