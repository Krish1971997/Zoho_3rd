package libraryManagementSystem;

class Book {
    String title;
    String author;
    String genre;
    boolean isAvailable;

    Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.isAvailable = true;  // Initially, all books are available
    }
}
