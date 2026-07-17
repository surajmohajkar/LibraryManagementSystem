package service;

import model.Book;
import model.IssueRecord;
import model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class IssueService {
    private List<IssueRecord> issueRecords;
    private BookService bookService;
    private MemberService memberService;
    private int nextIssueId;

    public IssueService(BookService bookService, MemberService memberService) {
        this.issueRecords = new ArrayList<>();
        this.bookService = bookService;
        this.memberService = memberService;
        this.nextIssueId = 1;
    }

    public List<IssueRecord>getIssueRecords(){
        return issueRecords;
    }
    public IssueRecord searchIssueRecord(int issueId){
        for(IssueRecord record : issueRecords){
            if(record.getIssueId() == issueId){
                return record;
            }
        }
        return null;
    }
    public boolean issueBook(int bookId, int memberId){
        Book book = bookService.searchBook(bookId);
        if(book==null){
            System.out.println("Book not found.");
            return false;
        }
        Member member = memberService.searchMember(memberId);
        if(member == null){
            System.out.println("Member not found.");
            return false;
        }
        if(!book.isAvailable()){
            System.out.println("Book is already issued.");
            return false;
        }
        IssueRecord record = new IssueRecord(
                nextIssueId++,
                book,
                member,
                LocalDate.now(),
                LocalDate.now().plusDays(14),
                false);
        issueRecords.add(record);
        book.setAvailable(false);
        return true;
    }
}
