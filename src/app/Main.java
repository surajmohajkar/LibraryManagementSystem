package app;


import constants.AppConstants;
import enums.BookCategory;
import enums.MembershipType;
import model.Book;
import model.Member;
import service.BookService;
import service.IssueService;
import service.MemberService;

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

            System.out.println("\n====================================");
            System.out.println("      LIBRARY MANAGEMENT SYSTEM");
            System.out.println("====================================");

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
                    System.out.println("\n========== ADD NEW BOOK ==========");

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
                        System.out.println("\n" + AppConstants.BOOK_ADDED_SUCCESS);
                    }else{
                        System.out.println("\nBook could not be added.");
                    }
                    break;
                case 2:
                    System.out.println("\n========== VIEW ALL BOOKS ==========\n");
                    List<Book> allBooks = bookService.getAllBooks();
                    if (allBooks.isEmpty()) {
                        System.out.println("No books available in the library.");
                    } else {
                        for (Book book : allBooks) {
                            System.out.println(book);
                            System.out.println("--------------------------------");
                        }
                    }
                    break;
                case 3:
                    System.out.println("\n========== SEARCH BOOK ==========");

                    System.out.println("\nEnter Book ID : ");
                    int searchId = sc.nextInt();
                    Book searchedBook = bookService.searchBook(searchId);
                    if(searchedBook != null){
                        System.out.println("\nBook Found");
                        System.out.println(searchedBook);
                    }else{
                        System.out.println("\nBook Not Found");
                    }
                    break;
                case 4:
                    System.out.println("\n========== UPDATE BOOK ==========");
                    System.out.println("Enter Book ID : ");
                    int updateId = sc.nextInt();
                    sc.nextLine();

                    Book existingBook = bookService.searchBook(updateId);
                    if(existingBook == null){
                        System.out.println("Book Not Found");
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
                        System.out.println("\nBook updated successfully.");
                    }else{
                        System.out.println("\nBook Not Found");
                    }
                    break;
                case 5:
                    System.out.println("\n========== DELETE BOOK ==========");
                    System.out.println("Enter Book ID : ");
                    int deleteId = sc.nextInt();
                    boolean deleted = bookService.deleteBook(deleteId);
                    if(deleted){
                        System.out.println("\nBook deleted successfully.");
                    }else {
                        System.out.println("\nBook Not Found");
                    }
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Library Management System.");
                    break;
                case 7:

                    System.out.println("\n========== REGISTER MEMBER ==========");
                    System.out.println("Enter Member ID : ");
                    int memberId= sc.nextInt();
                    sc.nextLine();
                    Member existingMember = memberService.searchMember(memberId);
                    if(existingMember != null){
                        System.out.println("Member ID already exists.");
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
                        System.out.println(AppConstants.MEMBER_ADDED_SUCCESS);
                    }else{
                        System.out.println("\nMember ID already exists.");
                    }
                    break;
                case 8:
                    System.out.println("\n========== REGISTERED MEMBERS ==========\n");
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
                    System.out.println("\n========== SEARCH MEMBER ==========");
                    System.out.println("Enter Member ID : ");
                    int searchMemberId = sc.nextInt();
                    Member searchedMember = memberService.searchMember(searchMemberId);
                    if(searchedMember != null){
                        System.out.println("\nMember Found\n");
                        System.out.println(searchedMember);
                    }else{
                        System.out.println("Member Not Found");
                    }
                    break;
                case 10:
                    System.out.println("\n========== UPDATE MEMBER ==========");
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
                        System.out.println("Member Updated Successfully.");
                    }else{
                        System.out.println("Member Not Updated");
                    }
                    break;
                case 11:
                    System.out.println("\n========== DELETE MEMBER ==========");
                    System.out.println("Enter Member ID : ");
                    int deleteMemberId = sc.nextInt();
                    boolean memberDeleted= memberService.deleteMember(deleteMemberId);
                    if(memberDeleted){
                        System.out.println("Member Deleted Successfully.");
                    }else{
                        System.out.println("Member Not Found.");
                    }
                    break;
                case 12:
                    System.out.println("\n========== ISSUE BOOK ==========");
                    System.out.println("Enter Book ID : ");
                    int issueBookId = sc.nextInt();
                    System.out.println("Enter Member ID : ");
                    int issueMemberId = sc.nextInt();

                    boolean issued = issueService.issueBook(issueBookId, issueMemberId);
                    if(issued){
                        System.out.println(AppConstants.BOOK_ISSUED_SUCCESS);
                    }
                    break;
                case 13:
                    System.out.println("\n========== RETURN BOOK ==========");
                    System.out.println("Enter Issue ID : ");
                    int returnIssueId = sc.nextInt();
                    boolean returned = issueService.returnBook(returnIssueId);
                    if(returned){
                        System.out.println(AppConstants.BOOK_RETURNED_SUCCESS);
                    }
                    break;
                case 14:
                    System.out.println(AppConstants.REPORT_TITLE);
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
                    System.out.println("\nInvalid Choice.");
            }
        }
        sc.close();
    }
}
