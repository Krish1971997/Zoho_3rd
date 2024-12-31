package libraryManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LibraryService {
    static List<Book> books = new ArrayList<>();
    static List<Member> members = new ArrayList<>();
    static final String adminUsername = "admin";
    static final String adminPassword = "password";

    // Validate admin login
    boolean validateAdminLogin(String username, String password) {
        return username.equals(adminUsername) && password.equals(adminPassword);
    }

    // Add a book to the library
    void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter book genre: ");
        String genre = scanner.nextLine();
        books.add(new Book(title, author, genre));
        System.out.println("Book added successfully.");
    }

    // Update an existing book
    void updateBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title to update: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book != null) {
            System.out.print("Enter new author: ");
            book.author = scanner.nextLine();
            System.out.print("Enter new genre: ");
            book.genre = scanner.nextLine();
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Remove a book from the library
    void removeBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title to remove: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book != null) {
            books.remove(book);
            System.out.println("Book removed successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Add a member
    void addMember() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter member username: ");
        String username = scanner.nextLine();
        members.add(new Member(username));
        System.out.println("Member added successfully.");
    }

    // Borrow a book for a logged-in user
    void borrowBook(Member member) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title to borrow: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book != null && member.borrowBook(book)) {
            System.out.println("Book borrowed successfully.");
        } else {
            System.out.println("Book is either unavailable or borrow limit reached.");
        }
    }

    // Return a borrowed book for a logged-in user
    void returnBook(Member member) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title to return: ");
        String title = scanner.nextLine();
        Book book = findBookByTitle(title);
        if (book != null && !book.isAvailable) {
            member.returnBook(book);
            System.out.println("Book returned successfully.");
        } else {
            System.out.println("Book not found or it wasn't borrowed.");
        }
    }

    // Display all available books
    void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available.");
        } else {
            System.out.println("\nList of Books:");
            for (Book book : books) {
                System.out.println("Title: " + book.title + ", Author: " + book.author + ", Genre: " + book.genre + ", Available: " + book.isAvailable);
            }
        }
    }

    // Display all members
    void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members exist.");
        } else {
            System.out.println("\nList of Members:");
            for (Member member : members) {
                System.out.println("Username: " + member.username + ", Borrowed Books: " + member.borrowedBooks.size());
            }
        }
    }

    // Find a book by its title
    Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.title.equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Get or create a new member
    Member getOrCreateMember(String username) {
        for (Member member : members) {
            if (member.username.equals(username)) {
                return member;
            }
        }
        Member newMember = new Member(username);
        members.add(newMember);
        return newMember;
    }
}
