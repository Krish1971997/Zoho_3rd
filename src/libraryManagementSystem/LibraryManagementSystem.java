package libraryManagementSystem;

import java.util.*;

public class LibraryManagementSystem {
    static Scanner scanner = new Scanner(System.in);
    static LibraryService libraryService = new LibraryService();
    static Member loggedInMember = null;

    public static void main(String[] args) {
        while (true) {
            System.out.print("Enter role (admin/user): ");
            String role = scanner.nextLine();
            if (role.equals("admin")) {
                loginAsAdmin();
            } else if (role.equals("user")) {
                loginAsUser();
            } else {
                System.out.println("Invalid role. Please try again.");
            }
        }
    }

    private static void loginAsAdmin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (libraryService.validateAdminLogin(username, password)) {
            System.out.println("Logged in as admin.");
            adminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }

    private static void loginAsUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        loggedInMember = libraryService.getOrCreateMember(username);
        System.out.println("Logged in as user.");
        userMenu();
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nLibrary Management System - Admin");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Remove Book");
            System.out.println("4. Add Member");
            System.out.println("5. Display All Books");
            System.out.println("6. Display All Members");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1: libraryService.addBook(); break;
                case 2: libraryService.updateBook(); break;
                case 3: libraryService.removeBook(); break;
                case 4: libraryService.addMember(); break;
                case 5: libraryService.displayAllBooks(); break;
                case 6: libraryService.displayAllMembers(); break;
                case 7: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void userMenu() {
        while (true) {
            System.out.println("\nLibrary Management System - User");
            System.out.println("1. Borrow Book");
            System.out.println("2. Return Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Display All Members");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1: libraryService.borrowBook(loggedInMember); break;
                case 2: libraryService.returnBook(loggedInMember); break;
                case 3: libraryService.displayAllBooks(); break;
                case 4: libraryService.displayAllMembers(); break;
                case 5: return;
                default: System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
