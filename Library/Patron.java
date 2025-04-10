import java.util.List;
import java.util.Random;

public class Patron implements Runnable {
    private String name;
    private Library library;
    private Random random;

    public Patron(String name, Library library) {
        this.name = name;
        this.library = library;
        this.random = new Random();
    }

    @Override
    public void run() {
        library.registerPatron(name);
        
        // Perform random library actions
        for (int i = 0; i < 10; i++) {
            try {
                // Get available books
                List<String> availableBooks = library.getAvailableBookTitles();
                List<String> borrowedBooks = library.getBorrowedBooks(name);
                
                if (random.nextBoolean() && !availableBooks.isEmpty()) {
                    // Borrow a book
                    String bookToBorrow = availableBooks.get(random.nextInt(availableBooks.size()));
                    library.borrowBook(name, bookToBorrow);
                } else if (!borrowedBooks.isEmpty()) {
                    // Return a book
                    String bookToReturn = borrowedBooks.get(random.nextInt(borrowedBooks.size()));
                    library.returnBook(name, bookToReturn);
                }
                
                // Sleep for random time
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}