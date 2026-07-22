package service;

import constants.AppConstants;
import exception.BookAlreadyIssuedException;
import exception.BookAlreadyReturnedException;
import exception.IssueRecordNotFoundException;
import model.Book;
import model.IssueRecord;
import model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueService {
    private final List<IssueRecord> issueRecords;
    private final BookService bookService;
    private final MemberService memberService;
    private int nextIssueId;
    public IssueService(BookService bookService, MemberService memberService) {
        this.issueRecords = new ArrayList<>();
        this.bookService = bookService;
        this.memberService = memberService;
        this.nextIssueId = 1;
    }
    public List<IssueRecord> getIssueRecords() {
        return issueRecords;
    }
    public IssueRecord searchIssueRecord(int issueId) {
        for (IssueRecord record : issueRecords) {
            if (record.getIssueId() == issueId) {
                return record;
            }
        }
        throw new IssueRecordNotFoundException("Issue Record with ID " + issueId + " not found.");
    }
    public boolean issueBook(int bookId, int memberId) {
        Book book = bookService.searchBook(bookId);
        Member member = memberService.searchMember(memberId);
        if (!book.isAvailable()) {
            throw new BookAlreadyIssuedException("Book is already issued.");
        }
        IssueRecord record = new IssueRecord(
                nextIssueId++,
                book,
                member,
                LocalDate.now(),
                LocalDate.now().plusDays(AppConstants.BOOK_ISSUE_DAYS),false);
        issueRecords.add(record);
        book.setAvailable(false);
        return true;
    }
    public boolean returnBook(int issueId) {
        IssueRecord issueRecord = searchIssueRecord(issueId);
        if (issueRecord.isReturned()) {
            throw new BookAlreadyReturnedException("Book has already been returned.");
        }
        issueRecord.setReturned(true);
        issueRecord.getBook().setAvailable(true);
        return true;
    }
    public int getTotalIssueRecords() {
        return issueRecords.size();
    }
    public int getActiveIssues() {
        int count = 0;
        for (IssueRecord record : issueRecords) {
            if (!record.isReturned()) {
                count++;
            }
        }
        return count;
    }
    public int getReturnedBooks() {
        int count = 0;
        for (IssueRecord record : issueRecords) {
            if (record.isReturned()) {
                count++;
            }
        }
        return count;
    }
}