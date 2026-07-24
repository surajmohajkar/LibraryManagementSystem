package service;

import constants.AppConstants;
import exception.BookAlreadyIssuedException;
import exception.BookAlreadyReturnedException;
import exception.IssueRecordNotFoundException;
import interfaces.BookServiceInterface;
import interfaces.IssueServiceInterface;
import interfaces.MemberServiceInterface;
import model.Book;
import model.IssueRecord;
import model.Member;
import java.util.Collections;
import java.util.Objects;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueService implements IssueServiceInterface {
    private final List<IssueRecord> issueRecords;
    private final BookServiceInterface bookService;
    private final MemberServiceInterface memberService;
    private int nextIssueId;

    public IssueService(BookServiceInterface bookService, MemberServiceInterface memberService) {
        this.issueRecords = new ArrayList<>();
        this.bookService = Objects.requireNonNull(bookService, "BookService cannot be null");
        this.memberService = Objects.requireNonNull(memberService, "MemberService cannot be null");
        this.nextIssueId = 1;
    }
    @Override
    public List<IssueRecord> getIssueRecords() {
        return Collections.unmodifiableList(issueRecords);
    }
    @Override
    public IssueRecord searchIssueRecord(int issueId) {
        for (IssueRecord record : issueRecords) {
            if (record.getIssueId() == issueId) {
                return record;
            }
        }
        throw new IssueRecordNotFoundException("Issue Record with ID " + issueId + " not found.");
    }
    @Override
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
    @Override
    public boolean returnBook(int issueId) {
        IssueRecord issueRecord = searchIssueRecord(issueId);
        if (issueRecord.isReturned()) {
            throw new BookAlreadyReturnedException("Book has already been returned.");
        }
        issueRecord.setReturned(true);
        issueRecord.getBook().setAvailable(true);
        return true;
    }
    @Override
    public int getTotalIssueRecords() {
        return issueRecords.size();
    }
    @Override
    public int getActiveIssues() {
        int count = 0;
        for (IssueRecord record : issueRecords) {
            if (!record.isReturned()) {
                count++;
            }
        }
        return count;
    }
    @Override
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