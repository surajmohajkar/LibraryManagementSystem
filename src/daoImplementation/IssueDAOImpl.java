package daoImplementation;

import dao.IssueDAO;
import model.IssueRecord;
import java.util.List;

public class IssueDAOImpl implements IssueDAO {
    @Override
    public boolean issueBook(IssueRecord record) {
        return false;
    }

    @Override
    public IssueRecord getIssueRecord(int issueId) {
        return null;
    }

    @Override
    public boolean returnBook(int issueId) {
        return false;
    }

    @Override
    public List<IssueRecord> getAllIssueRecords() {
        return List.of();
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
}
