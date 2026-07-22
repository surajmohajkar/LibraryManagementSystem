package interfaces;

import model.IssueRecord;

import java.util.List;

public interface IssueServiceInterface {
    List<IssueRecord> getIssueRecords();
    IssueRecord searchIssueRecord(int issueId);
    boolean issueBook(int bookId, int memberId);
    boolean returnBook(int issueId);
    int getTotalIssueRecords();
    int getActiveIssues();
    int getReturnedBooks();
}