package app;


import model.Book;
import model.Member;
import service.BookService;
import service.MemberService;

import java.awt.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService service = new BookService();
        MemberService memberService = new MemberService();
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

                    System.out.println("Enter Category");
                    String category = sc.nextLine();

                    System.out.println("Enter Book Price :");
                    double price = sc.nextDouble();

                    Book newBook = new Book(bookId, title, author, category, price, true);

                    boolean added = service.addBook(newBook);
                    if(added){
                        System.out.println("\nBook added successfully.");
                    }else{
                        System.out.println("\nBook could not be added.");
                    }
                    break;
                case 2:
                    System.out.println("\n========== VIEW ALL BOOKS ==========\n");
                    List<Book> allBooks = service.getAllBooks();
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
                    Book searchedBook = service.searchBook(searchId);
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

                    Book existringBook = service.searchBook(updateId);
                    if(existringBook == null){
                        System.out.println("Book Not Found");
                        break;
                    }
                    System.out.println("Enter New Title : ");
                    String newTitle = sc.nextLine();
                    System.out.println("Enter New Author : ");
                    String newAuthor = sc.nextLine();
                    System.out.println("Enter New Category : ");
                    String newCategory = sc.nextLine();
                    System.out.println("Enter New Price : ");
                    double newPrice = sc.nextDouble();

                    Book uodateBook = new Book(updateId, newTitle, newAuthor, newCategory, newPrice, true);

                    boolean updated = service.updateBook(uodateBook);
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
                    boolean deleted = service.deleteBook(deleteId);
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
                    String membershipType = sc.nextLine();

                    Member member = new Member(
                            memberId,
                            memberName,
                            phoneNumber,
                            email,
                            membershipType
                    );
                    boolean registered = memberService.registerMember(member);
                    if(registered){
                        System.out.println("\nMeber Registered Successfully.");
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
                default:
                    System.out.println("\nInvalid Choice.");
            }
        }
        sc.close();
    }
}