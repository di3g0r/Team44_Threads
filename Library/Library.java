import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private List<Book> books;
    private List<String> patrons;
    private Map<String, List<Book>> borrowedBooks; // Map patron to their borrowed books

    public Library() {
        this.books = new ArrayList<>();
        this.patrons = new ArrayList<>();
        this.borrowedBooks = new HashMap<>();
    }

    // Register a new patron
    public synchronized void registerPatron(String patronName) {
        if (!patrons.contains(patronName)) {
            patrons.add(patronName);
            borrowedBooks.put(patronName, new ArrayList<>());
            System.out.println(patronName + " has been registered.");
        } else {
            System.out.println(patronName + " is already registered.");
        }
    }

    // Add a new book to the library
    public synchronized void registerBook(String title) {
        books.add(new Book(title));
        System.out.println("Book '" + title + "' has been added to the library.");
    }

    // A patron borrows a book
    public synchronized boolean borrowBook(String patronName, String bookTitle) {
        // Check if patron is registered
        if (!patrons.contains(patronName)) {
            System.out.println(patronName + " is not registered and cannot borrow books.");
            return false;
        }

        // Find the book
        for (Book book : books) {
            if (book.getTitle().equals(bookTitle) && book.isAvailable()) {
                book.setAvailable(false);
                borrowedBooks.get(patronName).add(book);
                System.out.println(patronName + " borrowed '" + bookTitle + "'.");
                return true;
            }
        }

        System.out.println(patronName + " attempted to borrow '" + bookTitle + "' but it's not available.");
        return false;
    }

    // A patron returns a book
    public synchronized boolean returnBook(String patronName, String bookTitle) {
        // Check if patron is registered
        if (!patrons.contains(patronName)) {
            System.out.println(patronName + " is not registered and cannot return books.");
            return false;
        }

        // Find the book in patron's borrowed list
        List<Book> patronBooks = borrowedBooks.get(patronName);
        for (int i = 0; i < patronBooks.size(); i++) {
            Book book = patronBooks.get(i);
            if (book.getTitle().equals(bookTitle)) {
                book.setAvailable(true);
                patronBooks.remove(i);
                System.out.println(patronName + " returned '" + bookTitle + "'.");
                return true;
            }
        }

        System.out.println(patronName + " attempted to return '" + bookTitle + "' but didn't borrow it.");
        return false;
    }

    // Get a list of all available book titles
    public synchronized List<String> getAvailableBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            if (book.isAvailable()) {
                titles.add(book.getTitle());
            }
        }
        return titles;
    }

    // Get a list of all book titles
    public synchronized List<String> getAllBookTitles() {
        List<String> titles = new ArrayList<>();
        for (Book book : books) {
            titles.add(book.getTitle());
        }
        return titles;
    }

    // Get books borrowed by a patron
    public synchronized List<String> getBorrowedBooks(String patronName) {
        if (!patrons.contains(patronName)) {
            return new ArrayList<>();
        }
        
        List<String> titles = new ArrayList<>();
        for (Book book : borrowedBooks.get(patronName)) {
            titles.add(book.getTitle());
        }
        return titles;
    }
}