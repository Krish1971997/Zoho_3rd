package libraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

class Member {
    String username;
    List<Book> borrowedBooks;
    Member(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
    }

    boolean borrowBook(Book book) {
        if (borrowedBooks.size() < 5 && book.isAvailable) {
            borrowedBooks.add(book);
            book.isAvailable = false;  // Mark the book as borrowed
            return true;
        }
        return false;
    }

    void returnBook(Book book) {
        borrowedBooks.remove(book);
        book.isAvailable = true;  // Mark the book as available
    }
}