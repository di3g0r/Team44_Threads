# Team44_Threads Activity
# Library Management System

## System Architecture

The system is organized into four main components:

1. **Book**: Represents individual books in the library inventory with their availability status.
2. **Library**: The core component that manages the book inventory and patron operations.
3. **Patron**: Represents library users who can borrow and return books.
4. **LibrarySimulation**: Orchestrates the simulation by creating patrons as separate threads.


## Class Descriptions

### Book Class
Simple data structure representing a book with title and availability info.

### Library Class
The core class that manages the book inventory and patron interactions.

#### Key Methods:

| Method | Purpose | Thread-Safety Approach |
|--------|---------|------------------------|
| `registerPatron(String patronName)` | Adds a new patron to the library system | Synchronized method to prevent concurrent modification of patron list |
| `registerBook(String title)` | Adds a new book to the library inventory | Synchronized method to prevent concurrent modification of book list |
| `borrowBook(String patronName, String bookTitle)` | Allows a patron to borrow an available book | Synchronized method to prevent two patrons from borrowing the same book simultaneously |
| `returnBook(String patronName, String bookTitle)` | Allows a patron to return a previously borrowed book | Synchronized method to ensure atomic updates to book status |
| `getAvailableBookTitles()` | Retrieves a list of available books | Synchronized to provide a consistent view of available books |
| `getBorrowedBooks(String patronName)` | Gets books borrowed by a specific patron | Synchronized to ensure consistent view of a patron's borrowed books |

### Patron Class
Represents a library user who borrows and returns books. Implements `Runnable` to allow patrons to operate concurrently.

#### Key Methods:

| Method | Purpose | Multithreading Details |
|--------|---------|------------------------|
| `run()` | The main operation loop of each patron | Executed in a separate thread, performs 10 random library actions with random delays |

### LibrarySimulation Class
The main class that sets up and runs the simulation.

#### Key Methods:

| Method | Purpose | Multithreading Details |
|--------|---------|------------------------|
| `main(String[] args)` | Entry point for the application | Creates and orchestrates multiple patron threads, waits for all threads to complete |

## How to Run the Application

1. Make sure you have Java Development Kit (JDK) installed (version 8 or higher recommended).

2. Place all four Java files in the same directory:
   - Book.java
   - Library.java
   - Patron.java
   - LibrarySimulation.java

3. Open a command prompt or terminal, navigate to the directory containing the files, and run:

   ```
   javac *.java
   java LibrarySimulation
   ```

## Expected Output

The simulation will run multiple patron threads that perform random borrowing and returning actions. The output will show these operations in real-time, followed by a summary when all threads complete.

Example output:
```
Book 'Java Programming' has been added to the library.
Book 'Data Structures' has been added to the library.
Book 'Algorithms' has been added to the library.
Book 'Design Patterns' has been added to the library.
Book 'Clean Code' has been added to the library.
Patron 1 has been registered.
Patron 2 has been registered.
Patron 3 has been registered.
Patron 1 borrowed 'Java Programming'.
Patron 2 borrowed 'Data Structures'.
Patron 1 returned 'Java Programming'.
Patron 3 borrowed 'Algorithms'.
...

===== Library Simulation Complete =====
Patron 1 has borrowed 2 books: [Design Patterns, Clean Code]
Patron 2 has borrowed 1 books: [Data Structures]
Patron 3 has borrowed 0 books: []
```
