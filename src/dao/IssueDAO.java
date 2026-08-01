package dao;

import model.IssueRecord;
import java.util.List;

public interface IssueDAO {
    boolean issueBook(IssueRecord record);
    IssueRecord getIssueRecord(int issueId);
    boolean returnBook(int issueId);
    List<IssueRecord> getAllIssueRecords();
    int getTotalIssueRecords();
    int getActiveIssues();
    int getReturnedBooks();
}