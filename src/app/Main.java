package app;


import constants.AppConstants;
import enums.BookCategory;
import enums.MembershipType;
import model.Book;
import model.Member;
import service.BookService;
import service.IssueService;
import service.MemberService;
import util.ConsoleUtil;
import util.ValidationUtil;

import java.io.Console;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();
        IssueService issueService = new IssueService(bookService,memberService);
        boolean running = true;

        while(running){
            ConsoleUtil.printHeader("LIBRARY MANAGEMENT SYSTEM");
            System.out.println("1. Add Book");
            System.out.println("2. View Books");
            System.out.println("3. Search Book");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");
            System.out.println("7. Register Member");
            System.out.println("8. View Members");
            System.out.println("9. Search Member");
            System.out.println("10. Update Member");
            System.out.println("11. Delete Member");
            System.out.println("12. Issue Book");
            System.out.println("13. Return Book");
            System.out.println("14. View Reports");

            System.out.print("\nEnter your choice : ");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    ConsoleUtil.printHeader("ADD NEW BOOK");

                    System.out.println("Enter Book ID :");
                    int bookId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter Book Title :");
                    String title = sc.nextLine();

                    System.out.println("Enter Book Author :");
                    String author = sc.nextLine();

                    System.out.print("Enter Book Category : ");
                    String categoryInput1 = sc.nextLine().trim().toUpperCase();
                    BookCategory category = BookCategory.valueOf(categoryInput1);

                    System.out.println("Enter Book Price :");
                    double price = sc.nextDouble();

                    Book newBook = new Book(bookId, title, author, category, price, true);

                    boolean added = bookService.addBook(newBook);
                    if(added){
                        ConsoleUtil.printSuccess(AppConstants.BOOK_ADDED_SUCCESS);
                    }else{
                        System.out.println("\nBook could not be added.");
                    }
                    break;
                case 2:
                    ConsoleUtil.printHeader("VIEW ALL BOOKS");
                    List<Book> allBooks = bookService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book);
                            ConsoleUtil.printLine();
                        }
                    }
                    break;
                case 3:
                    ConsoleUtil.printHeader("SEARCH BOOK");
                    System.out.println("\nEnter Book ID : ");
                    int searchId = sc.nextInt();
                    Book searchedBook = bookService.searchBook(searchId);
                    if(searchedBook != null){
                        ConsoleUtil.printSuccess("Book Found");
                        System.out.println(searchedBook);
                    }else{
                        ConsoleUtil.printError("Book Not Found");
                    }
                    break;
                case 4:
                    ConsoleUtil.printHeader("UPDATE BOOK");
                    System.out.println("Enter Book ID : ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Book existingBook = bookService.searchBook(updateId);
                    if(existingBook == null){
                        ConsoleUtil.printError(AppConstants.BOOK_NOT_FOUND);
                        break;
                    }
                    System.out.println("Enter New Title : ");
                    String newTitle = sc.nextLine();
                    System.out.println("Enter New Author : ");
                    String newAuthor = sc.nextLine();
                    System.out.println("Enter New Category : ");
                    String categoryInput  = sc.nextLine().trim().toUpperCase();
                    BookCategory newCategory = BookCategory.valueOf(categoryInput);
                    System.out.println("Enter New Price : ");
                    double newPrice = sc.nextDouble();

                    Book updatedBook = new Book(updateId, newTitle, newAuthor, newCategory, newPrice, true);

                    boolean updated = bookService.updateBook(updatedBook);
                    if(updated){
                        ConsoleUtil.printSuccess("\nBook updated successfully.");
                    }else{
                        ConsoleUtil.printError("\nBook Not Found");
                    }
                    break;
                case 5:
                    ConsoleUtil.printHeader("DELETE BOOK");
                    System.out.println("Enter Book ID : ");
                    int deleteId = sc.nextInt();
                    boolean deleted = bookService.deleteBook(deleteId);
                    if(deleted){
                        ConsoleUtil.printSuccess("Book deleted successfully.");
                    }else {
                        ConsoleUtil.printError("Book Not Found");
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Library Management System.");
                    break;
                case 7:
                    ConsoleUtil.printHeader("REGISTER MEMBER");
                    System.out.println("Enter Member ID : ");
                    int memberId= sc.nextInt();
                    sc.nextLine();
                    Member existingMember = memberService.searchMember(memberId);
                    if(existingMember != null){
                        ConsoleUtil.printSuccess("Member ID already exists.");
                        break;
                    }
                    System.out.println("Enter Member Name : ");
                    String memberName = sc.nextLine();
                    System.out.println("Enter Phone Number : ");
                    String phoneNumber = sc.nextLine();
                    System.out.println("Enter Email : ");
                    String email = sc.nextLine();
                    System.out.println("Enter Membership Type : ");
                    String membershipInput1 = sc.nextLine().trim().toUpperCase();
                    MembershipType membershipType = MembershipType.valueOf(membershipInput1);

                    Member member = new Member(
                            memberId,
                            memberName,
                            phoneNumber,
                            email,
                            membershipType
                    );
                    boolean memberRegistered = memberService.registerMember(member);
                    if(memberRegistered){
                        ConsoleUtil.printSuccess(AppConstants.MEMBER_ADDED_SUCCESS);
                    }else{
                        ConsoleUtil.printError("Member ID already exists.");
                    }
                    break;
                case 8:
                    ConsoleUtil.printHeader("VIEW MEMBERS");
                    List<Member>allMembers = memberService.getAllMembers();
                    if(allMembers.isEmpty()){
                        System.out.println("No members Registered.");
                    }else{
                        for(Member member1 : allMembers){
                            System.out.println(member1);
                        }
                    }
                    break;
                case 9:
                    ConsoleUtil.printHeader("SEARCH MEMBERS");
                    System.out.println("Enter Member ID : ");
                    int searchMemberId = sc.nextInt();
                    Member searchedMember = memberService.searchMember(searchMemberId);
                    if(searchedMember != null){
                        ConsoleUtil.printSuccess("Member Found");
                        System.out.println(searchedMember);
                    }else{
                        ConsoleUtil.printError(AppConstants.MEMBER_NOT_FOUND);
                    }
                    break;
                case 10:
                    ConsoleUtil.printHeader("UPDATE MEMBER");
                    System.out.println("Enter Member ID : ");
                    int updateMemberId = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Enter New Name : ");
                    String newName = sc.nextLine();
                    System.out.println("Enter New Phone Number : ");
                    String newPhone = sc.nextLine();
                    System.out.println("Enter New Email : ");
                    String newEmail = sc.nextLine();
                    System.out.println("Enter New Membership Type : ");
                    String membershipInput =sc.nextLine().trim().toUpperCase();
                    MembershipType newMembershipType = MembershipType.valueOf(membershipInput);
                    Member updateMember = new Member(
                            updateMemberId,
                            newName,
                            newPhone,
                            newEmail,
                            newMembershipType
                    );
                    boolean memberUpdated = memberService.updateMember(updateMember);
                    if(memberUpdated){
                        ConsoleUtil.printSuccess("Member Updated Successfully.");
                    }else{
                        ConsoleUtil.printError("Member Not Updated.");
                    }
                    break;
                case 11:
                    ConsoleUtil.printHeader("DELETE MEMBERS");
                    System.out.println("Enter Member ID : ");
                    int deleteMemberId = sc.nextInt();
                    boolean memberDeleted= memberService.deleteMember(deleteMemberId);
                    if(memberDeleted){
                        ConsoleUtil.printSuccess("Member Deleted Successfully.");
                    }else{
                        ConsoleUtil.printError(AppConstants.MEMBER_NOT_FOUND);
                    }
                    break;
                case 12:
                    ConsoleUtil.printHeader("ISSUE BOOK");
                    System.out.println("Enter Book ID : ");
                    int issueBookId = sc.nextInt();
                    System.out.println("Enter Member ID : ");
                    int issueMemberId = sc.nextInt();

                    boolean issued = issueService.issueBook(issueBookId, issueMemberId);
                    if(issued){
                        ConsoleUtil.printSuccess(AppConstants.BOOK_ISSUED_SUCCESS);
                    }
                    break;
                case 13:
                    ConsoleUtil.printHeader("RETURN BOOK");
                    System.out.println("Enter Issue ID : ");
                    int returnIssueId = sc.nextInt();
                    boolean returned = issueService.returnBook(returnIssueId);
                    if(returned){
                        ConsoleUtil.printSuccess(AppConstants.BOOK_RETURNED_SUCCESS);
                    }
                    break;
                case 14:
                    ConsoleUtil.printHeader(AppConstants.REPORT_TITLE);
                    System.out.println("Books");
                    System.out.println("---------------------------");
                    System.out.println("Total Books   :"+bookService.getTotalBooks());
                    System.out.println("Available Books :"+bookService.getAvailableBooks());
                    System.out.println("Issued Books  :"+bookService.getIssuedBooks());
                    System.out.println();
                    System.out.println("Members");
                    System.out.println("--------------------------");
                    System.out.println("Total Members :"+memberService.getTotalMembers());
                    System.out.println();
                    System.out.println("Issue Records");
                    System.out.println("--------------------------");
                    System.out.println("Total Issues  :"+issueService.getTotalIssueRecords());
                    System.out.println("Active Issues   :"+issueService.getActiveIssues());
                    System.out.println("Returned Books  :"+issueService.getReturnedBooks());
                    break;
                default:
                    ConsoleUtil.printError("Invalid Choice.");
            }
        }
        sc.close();
    }
}
