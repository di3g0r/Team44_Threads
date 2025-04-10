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
        
        for (int i = 0; i < 10; i++) {
            try {
                List<String> allBooks = library.getAllBookTitles();
                List<String> borrowedBooks = library.getBorrowedBooks(name);
                
                // Randomly choose to borrow or return a book
                if (random.nextBoolean()) {
                    // Try to borrow any book 
                    if (!allBooks.isEmpty()) {
                        String bookToBorrow = allBooks.get(random.nextInt(allBooks.size()));
                        library.borrowBook(name, bookToBorrow);
                    }
                } else {
                    // Only return books the patron has actually borrowed
                    if (!borrowedBooks.isEmpty()) {
                        String bookToReturn = borrowedBooks.get(random.nextInt(borrowedBooks.size()));
                        library.returnBook(name, bookToReturn);
                    }
                }
                
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}