import java.util.List;

public class LibrarySimulation {
    public static void main(String[] args) {
        // Create a library
        Library library = new Library();
        
        String[] bookTitles = {
            "Java Programming", "Data Structures", "Algorithms", 
            "Design Patterns", "Clean Code"
        };
        
        for (String title : bookTitles) {
            library.registerBook(title);
        }
        
        // Create and start patron threads
        int numberOfPatrons = 3;
        Thread[] patronThreads = new Thread[numberOfPatrons];
        
        for (int i = 0; i < numberOfPatrons; i++) {
            patronThreads[i] = new Thread(new Patron("Patron " + (i + 1), library));
            patronThreads[i].start();
        }
        
        // Wait for all patron threads to finish
        try {
            for (Thread thread : patronThreads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // Print final status
        System.out.println("\n===== Library Simulation Complete =====");
        for (int i = 0; i < numberOfPatrons; i++) {
            String patronName = "Patron " + (i + 1);
            List<String> patronBooks = library.getBorrowedBooks(patronName);
            System.out.println(patronName + " has borrowed " + patronBooks.size() + 
                              " books: " + patronBooks);
        }
    }
}