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

                    System.out.println("Enter Book ID :");
                    int bookId = sc.nextInt();
                    if(!ValidationUtil.isPositive(bookId)){
                        ConsoleUtil.printError("Book ID must be a greater than zero.");
                        break;
                    }
                    sc.nextLine();

                    System.out.println("Enter Book Title :");
                    String title = sc.nextLine();
                    if(ValidationUtil.isNullOrEmpty(title)){
                        ConsoleUtil.printError("Book Title Cannot Be Empty");
                        break;
                    }

                    System.out.println("Enter Book Author :");
                    String author = sc.nextLine();
                    if(ValidationUtil.isNullOrEmpty(author)){
                        ConsoleUtil.printError("Author Cannot Be Empty");
                        break;
                    }

                    System.out.print("Enter Book Category : ");
                    BookCategory category = ValidationUtil.parseBookCategory(sc.nextLine());
                    if (category == null) {
                        ConsoleUtil.printError("Invalid Book Category.");
                        break;
                    }
                    System.out.println("Enter Book Price :");
                    double price = sc.nextDouble();
                    if (!ValidationUtil.isPositive(price)) {
                        ConsoleUtil.printError("Price must be greater than zero.");
                        break;
                    }

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
                    if (!ValidationUtil.isPositive(searchId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
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
                    if (!ValidationUtil.isPositive(updateId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
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
                    BookCategory newCategory = ValidationUtil.parseBookCategory(sc.nextLine());
                    if (newCategory == null) {
                        ConsoleUtil.printError("Invalid Book Category.");
                        break;
                    }
                    System.out.println("Enter New Price : ");
                    double newPrice = sc.nextDouble();
                    if(!ValidationUtil.isPositive(newPrice)){
                        ConsoleUtil.printError("Price must be greater than zero.");
                        break;
                    }

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
                    if(!ValidationUtil.isPositive(deleteId)){
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
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
                    if(!ValidationUtil.isPositive(memberId)){
                        ConsoleUtil.printError("Invalid Member ID");
                        break;
                    }
                    sc.nextLine();
                    Member existingMember = memberService.searchMember(memberId);
                    if(existingMember != null){
                        ConsoleUtil.printSuccess("Member ID already exists.");
                        break;
                    }
                    System.out.println("Enter Member Name : ");
                    String memberName = sc.nextLine();
                    if(ValidationUtil.isNullOrEmpty(memberName)){
                        ConsoleUtil.printError("Member Name cannot be empty.");
                        break;
                    }
                    System.out.println("Enter Phone Number : ");
                    String phoneNumber = sc.nextLine();
                    if (!ValidationUtil.isValidPhone(phoneNumber)) {
                        ConsoleUtil.printError("Phone number must contain exactly 10 digits.");
                        break;
                    }
                    System.out.println("Enter Email : ");
                    String email = sc.nextLine();
                    if(!ValidationUtil.isValidEmail(email)){
                        ConsoleUtil.printError("Invalid Email Address.");
                        return;
                    }
                    System.out.println("Enter Membership Type : ");
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
                    if (!ValidationUtil.isPositive(searchMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
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
                    if (!ValidationUtil.isPositive(updateMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
                    System.out.println("Enter New Name : ");
                    String newName = sc.nextLine();
                    if (ValidationUtil.isNullOrEmpty(newName)) {
                        ConsoleUtil.printError("Member name cannot be empty.");
                        break;
                    }
                    System.out.println("Enter New Phone Number : ");
                    String newPhone = sc.nextLine();
                    if (!ValidationUtil.isValidPhone(newPhone)) {
                        ConsoleUtil.printError("Phone number must contain exactly 10 digits.");
                        break;
                    }
                    System.out.println("Enter New Email : ");
                    String newEmail = sc.nextLine();
                    if (!ValidationUtil.isValidEmail(newEmail)) {
                        ConsoleUtil.printError("Invalid Email Address.");
                        break;
                    }
                    System.out.println("Enter New Membership Type : ");
                    MembershipType newMembershipType = ValidationUtil.parseMembershipType(sc.nextLine());
                    if (newMembershipType == null) {
                        ConsoleUtil.printError("Invalid Membership Type.");
                        break;
                    }
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
                    if (!ValidationUtil.isPositive(deleteMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }
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
                    if (!ValidationUtil.isPositive(issueBookId)) {
                        ConsoleUtil.printError("Invalid Book ID.");
                        break;
                    }
                    System.out.println("Enter Member ID : ");
                    int issueMemberId = sc.nextInt();
                    if (!ValidationUtil.isPositive(issueMemberId)) {
                        ConsoleUtil.printError("Invalid Member ID.");
                        break;
                    }

                    boolean issued = issueService.issueBook(issueBookId, issueMemberId);
                    if(issued){
                        ConsoleUtil.printSuccess(AppConstants.BOOK_ISSUED_SUCCESS);
                    }
                    break;
                case 13:
                    ConsoleUtil.printHeader("RETURN BOOK");
                    System.out.println("Enter Issue ID : ");
                    int returnIssueId = sc.nextInt();
                    if (!ValidationUtil.isPositive(returnIssueId)) {
                        ConsoleUtil.printError("Invalid Issue ID.");
                        break;
                    }
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
