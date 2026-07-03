package app;


import service.BookService;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookService service = new BookService();
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

            System.out.print("\nEnter your choice : ");

            int choice = sc.nextInt();

            switch(choice){
                case 1:
                    System.out.println("\nFeature Under Development.");
                    break;
                case 2:
                    System.out.println("\nFeature Under Development.");
                    break;
                case 3:
                    System.out.println("\nFeature Under Development.");
                    break;
                case 4:
                    System.out.println("\nFeature Under Development.");
                    break;
                case 5:
                    System.out.println("\nFeature Under Development.");
                    break;
                case 6:
                    running = false;
                    System.out.println("\nThank you for using Library Management System.");
                    break;
                default:
                    System.out.println("\nInvalid Choice.");
            }
        }
        sc.close();
    }
}