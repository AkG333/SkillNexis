import java.util.ArrayList;

abstract class Person {

    private String name;
    private int id;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract void displayInfo();
}


class User extends Person {

    private ArrayList<Book> borrowedBooks;

    public User(String name, int id) {
        super(name, id);
        borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        borrowedBooks.remove(book);
    }

    @Override
    public void displayInfo() {
        System.out.println("User ID: " + getId());
        System.out.println("User Name: " + getName());

        System.out.println("Borrowed Books:");

        if (borrowedBooks.isEmpty()) {
            System.out.println("No books borrowed.");
        } else {
            for (Book book : borrowedBooks) {
                System.out.println("- " + book.getTitle());
            }
        }
    }
}


class Book {

    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Available: " + available);
    }
}

class Library {

    private ArrayList<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println(book.getTitle() + " has been added to the library.");
    }

    public void borrowBook(User user, Book book) {

        if (book.isAvailable()) {

            book.setAvailable(false);
            user.borrowBook(book);

            System.out.println(
                user.getName() + " borrowed " + book.getTitle()
            );

        } else {

            System.out.println(
                book.getTitle() + " is not available."
            );
        }
    }

    public void returnBook(User user, Book book) {

        if (!book.isAvailable()) {

            book.setAvailable(true);
            user.returnBook(book);

            System.out.println(
                user.getName() + " returned " + book.getTitle()
            );

        } else {

            System.out.println(
                book.getTitle() + " was not borrowed."
            );
        }
    }

    public void displayBooks() {

        System.out.println("\nLibrary Books");
        System.out.println("=============");

        for (Book book : books) {

            book.displayInfo();
            System.out.println("----------------");
        }
    }
}


public class LibraryManagementSystem {

    public static void main(String[] args) {

        Library library = new Library();

        Book book1 = new Book(
            "Java Programming",
            "James Gosling",
            "ISBN001"
        );

        Book book2 = new Book(
            "Object Oriented Programming",
            "John Smith",
            "ISBN002"
        );

        library.addBook(book1);
        library.addBook(book2);

        System.out.println();

        User user = new User("Alice", 101);

        user.displayInfo();

        System.out.println();

        library.borrowBook(user, book1);

        System.out.println();

        user.displayInfo();

        System.out.println();

        library.displayBooks();

        System.out.println();
        library.returnBook(user, book1);

        System.out.println();

        user.displayInfo();

        System.out.println();

        library.displayBooks();
    }
}