package model;

import java.time.LocalDate;

public class IssueRecord {
    private int issueId;
    private Book book;
    private Member member;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned;
    public IssueRecord(int issueId,
                       Book book,
                       Member member,
                       LocalDate issueDate,
                       LocalDate dueDate,
                       boolean returned) {

        this.issueId = issueId;
        this.book = book;
        this.member = member;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returned = returned;
    }
    public int getIssueId() {
        return issueId;
    }
    public Book getBook() {
        return book;
    }
    public Member getMember() {
        return member;
    }
    public LocalDate getIssueDate() {
        return issueDate;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public boolean isReturned() {
        return returned;
    }
    public void setReturned(boolean returned) {
        this.returned = returned;
    }
    @Override
    public String toString() {
        return "IssueRecord{" +
                "issueId=" + issueId +
                ", book=" + book.getTitle() +
                ", member=" + member.getName() +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                '}';
    }
}