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
import exception.LibraryException;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService bookService = new BookService();
        MemberService memberService = new MemberService();
        IssueService issueService = new IssueService(bookService, memberService);
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
                    System.out.print("Enter Book ID : ");
                    int bookId = sc.nextInt();
                    if (!ValidationUtil.isPositive(bookId)) {
                        ConsoleUtil.printError("Book ID must be greater than zero.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter Book Title : ");
                    String title = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(title)) {
                        ConsoleUtil.printError("Book Title cannot be empty.");
                        break;
                    }
                    System.out.print("Enter Book Author : ");
                    String author = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(author)) {
                        ConsoleUtil.printError("Author cannot be empty.");
                        break;
                    }
                    System.out.print("Enter Book Category : ");
                    BookCategory category = ValidationUtil.parseBookCategory(sc.nextLine());
                    if (category == null) {
                        ConsoleUtil.printError("Invalid Book Category.");
                        break;
                    }
                    System.out.print("Enter Book Price : ");
                    double price = sc.nextDouble();
                    if (!ValidationUtil.isPositive(price)) {
                        ConsoleUtil.printError("Price must be greater than zero.");
                        break;
                    }
                    Book newBook = new Book(
                            bookId,
                            title,
                            author,
                            category,
                            price,
                            true
                    );
                    try {
                        bookService.addBook(newBook);
                        ConsoleUtil.printSuccess(AppConstants.BOOK_ADDED_SUCCESS);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
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
                    System.out.print("Enter Book ID : ");
                    int searchId = sc.nextInt();
                    if (!ValidationUtil.isPositive(searchId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
                    try {
                        Book searchedBook = bookService.searchBook(searchId);
                        ConsoleUtil.printSuccess("Book Found");
                        System.out.println(searchedBook);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 4:
                    ConsoleUtil.printHeader("UPDATE BOOK");
                    System.out.print("Enter Book ID : ");
                    int updateId = sc.nextInt();
                    if (!ValidationUtil.isPositive(updateId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter New Title : ");
                    String newTitle = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(newTitle)) {
                        ConsoleUtil.printError("Book Title cannot be empty.");
                        break;
                    }
                    System.out.print("Enter New Author : ");
                    String newAuthor = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(newAuthor)) {
                        ConsoleUtil.printError("Author cannot be empty.");
                        break;
                    }
                    System.out.print("Enter New Category : ");
                    BookCategory newCategory = ValidationUtil.parseBookCategory(sc.nextLine());
                    if (newCategory == null) {
                        ConsoleUtil.printError("Invalid Book Category.");
                        break;
                    }
                    System.out.print("Enter New Price : ");
                    double newPrice = sc.nextDouble();
                    if (!ValidationUtil.isPositive(newPrice)) {
                        ConsoleUtil.printError("Price must be greater than zero.");
                        break;
                    }
                    Book updatedBook = new Book(
                            updateId,
                            newTitle,
                            newAuthor,
                            newCategory,
                            newPrice,
                            true
                    );
                    try {
                        bookService.updateBook(updatedBook);
                        ConsoleUtil.printSuccess("Book Updated Successfully.");
                    } catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 5:
                    ConsoleUtil.printHeader("DELETE BOOK");
                    System.out.print("Enter Book ID : ");
                    int deleteId = sc.nextInt();
                    if (!ValidationUtil.isPositive(deleteId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
                    try {
                        bookService.deleteBook(deleteId);
                        ConsoleUtil.printSuccess("Book Deleted Successfully.");
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Library Management System.");
                    break;
                case 7:
                    ConsoleUtil.printHeader("REGISTER MEMBER");
                    System.out.print("Enter Member ID : ");
                    int memberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(memberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter Member Name : ");
                    String memberName = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(memberName)) {
                        ConsoleUtil.printError("Member Name cannot be empty.");
                        break;
                    }
                    System.out.print("Enter Phone Number : ");
                    String phoneNumber = sc.nextLine();
                    if (!ValidationUtil.isValidPhone(phoneNumber)) {
                        ConsoleUtil.printError("Phone number must contain exactly 10 digits.");
                        break;
                    }
                    System.out.print("Enter Email : ");
                    String email = sc.nextLine();
                    if (!ValidationUtil.isValidEmail(email)) {
                        ConsoleUtil.printError("Invalid Email Address.");
                        break;
                    }
                    System.out.print("Enter Membership Type : ");
                    MembershipType membershipType = ValidationUtil.parseMembershipType(sc.nextLine());
                    if (membershipType == null) {
                        ConsoleUtil.printError("Invalid Membership Type.");
                        break;
                    }
                    Member member = new Member(
                            memberId,
                            memberName,
                            phoneNumber,
                            email,
                            membershipType
                    );
                    try {
                        memberService.registerMember(member);
                        ConsoleUtil.printSuccess(AppConstants.MEMBER_ADDED_SUCCESS);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 8:
                    ConsoleUtil.printHeader("VIEW MEMBERS");
                    List<Member> allMembers = memberService.getAllMembers();
                    if (allMembers.isEmpty()) {
                        System.out.println("No members registered.");
                    } else {
                        for (Member member1 : allMembers) {
                            System.out.println(member1);
                            ConsoleUtil.printLine();
                        }
                    }
                    break;
                case 9:
                    ConsoleUtil.printHeader("SEARCH MEMBER");
                    System.out.print("Enter Member ID : ");
                    int searchMemberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(searchMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    try {
                        Member searchedMember = memberService.searchMember(searchMemberId);
                        ConsoleUtil.printSuccess("Member Found");
                        System.out.println(searchedMember);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 10:
                    ConsoleUtil.printHeader("UPDATE MEMBER");
                    System.out.print("Enter Member ID : ");
                    int updateMemberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(updateMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter New Name : ");
                    String newName = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(newName)) {
                        ConsoleUtil.printError("Member Name cannot be empty.");
                        break;
                    }
                    System.out.print("Enter New Phone Number : ");
                    String newPhone = sc.nextLine();
                    if (!ValidationUtil.isValidPhone(newPhone)) {
                        ConsoleUtil.printError("Phone number must contain exactly 10 digits.");
                        break;
                    }
                    System.out.print("Enter New Email : ");
                    String newEmail = sc.nextLine();
                    if (!ValidationUtil.isValidEmail(newEmail)) {
                        ConsoleUtil.printError("Invalid Email Address.");
                        break;
                    }
                    System.out.print("Enter New Membership Type : ");
                    MembershipType newMembershipType = ValidationUtil.parseMembershipType(sc.nextLine());

                    if (newMembershipType == null) {
                        ConsoleUtil.printError("Invalid Membership Type.");
                        break;
                    }
                    Member updatedMember = new Member(
                            updateMemberId,
                            newName,
                            newPhone,
                            newEmail,
                            newMembershipType
                    );
                    try {
                        memberService.updateMember(updatedMember);
                        ConsoleUtil.printSuccess("Member Updated Successfully.");
                    } catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 11:
                    ConsoleUtil.printHeader("DELETE MEMBER");
                    System.out.print("Enter Member ID : ");
                    int deleteMemberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(deleteMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    try {
                        memberService.deleteMember(deleteMemberId);
                        ConsoleUtil.printSuccess("Member Deleted Successfully.");
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 12:
                    ConsoleUtil.printHeader("ISSUE BOOK");
                    System.out.print("Enter Book ID : ");
                    int issueBookId = sc.nextInt();
                    if (!ValidationUtil.isPositive(issueBookId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
                    System.out.print("Enter Member ID : ");
                    int issueMemberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(issueMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    try {
                        issueService.issueBook(issueBookId, issueMemberId);
                        ConsoleUtil.printSuccess(AppConstants.BOOK_ISSUED_SUCCESS);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 13:
                    ConsoleUtil.printHeader("RETURN BOOK");
                    System.out.print("Enter Issue ID : ");
                    int returnIssueId = sc.nextInt();
                    if (!ValidationUtil.isPositive(returnIssueId)) {
                        ConsoleUtil.printError("Invalid Issue ID.");
                        break;
                    }
                    try {
                        issueService.returnBook(returnIssueId);
                        ConsoleUtil.printSuccess(AppConstants.BOOK_RETURNED_SUCCESS);
                    }
                    catch (LibraryException ex) {
                        ConsoleUtil.printError(ex.getMessage());
                    }
                    break;
                case 14:
                    ConsoleUtil.printHeader(AppConstants.REPORT_TITLE);
                    System.out.println("Books");
                    ConsoleUtil.printLine();
                    System.out.println("Total Books      : " + bookService.getTotalBooks());
                    System.out.println("Available Books  : " + bookService.getAvailableBooks());
                    System.out.println("Issued Books     : " + bookService.getIssuedBooks());
                    System.out.println();
                    System.out.println("Members");
                    ConsoleUtil.printLine();
                    System.out.println("Total Members    : " + memberService.getTotalMembers());
                    System.out.println();
                    System.out.println("Issue Records");
                    ConsoleUtil.printLine();
                    System.out.println("Total Issues     : " + issueService.getTotalIssueRecords());
                    System.out.println("Active Issues    : " + issueService.getActiveIssues());
                    System.out.println("Returned Books   : " + issueService.getReturnedBooks());
                    break;
                default:
                    ConsoleUtil.printError("Invalid Choice.");
            }
        }
        sc.close();
    }
}
