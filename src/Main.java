import model.Book;
import model.Student;
import service.Library;

import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Library library = new Library();

    public static void main(String[] args) {

        System.out.println();
        System.out.println("==============================================================");
        System.out.println("             SMART LIBRARY MANAGEMENT SYSTEM");
        System.out.println("==============================================================");

        System.out.println("\nWelcome to the Smart Library Management System!");

        boolean running = true;

        while (running) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    addBook();
                    break;

                case 2:
                    library.viewBooks();
                    break;

                case 3:
                    searchBook();
                    break;

                case 4:
                    registerStudent();
                    break;

                case 5:
                    library.viewStudents();
                    break;

                case 6:
                    searchStudent();
                    break;

                case 7:
                    issueBook();
                    break;

                case 8:
                    returnBook();
                    break;

                case 9:
                    library.viewIssuedBooks();
                    break;

                case 10:
                    removeBook();
                    break;

                case 11:
                    sortBooks();
                    break;

                case 12:
                    library.showStatistics();
                    break;

                case 13:
                    library.saveData();
                    break;

                case 14:
                    library.saveData();

                    System.out.println("\nThank you for using Smart Library Management System.");
                    System.out.println("Goodbye!");

                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid choice. Please select a number between 1 and 14.");
            }

            if (running) {
                pressEnter();
            }
        }

        scanner.close();
    }

    // ============================================================
    // MENU
    // ============================================================

    private static void displayMenu() {

        System.out.println("\n");
        System.out.println("==============================================================");
        System.out.println("                        MAIN MENU");
        System.out.println("==============================================================");

        System.out.println("1.  Add Book");
        System.out.println("2.  View All Books");
        System.out.println("3.  Search Book");
        System.out.println("4.  Register Student");
        System.out.println("5.  View Students");
        System.out.println("6.  Search Student");
        System.out.println("7.  Issue Book");
        System.out.println("8.  Return Book");
        System.out.println("9.  View Issued Books");
        System.out.println("10. Remove Book");
        System.out.println("11. Sort Books");
        System.out.println("12. Library Statistics");
        System.out.println("13. Save Data");
        System.out.println("14. Exit");

        System.out.println("==============================================================");
    }

    // ============================================================
    // BOOK FUNCTIONS
    // ============================================================

    private static void addBook() {

        System.out.println("\n========================= ADD BOOK =========================");

        int id = readPositiveInt("Enter Book ID: ");

        if (library.findBookById(id) != null) {

            System.out.println("A book with this ID already exists.");
            return;
        }

        String title = readNonEmpty("Enter Book Title: ");
        String author = readNonEmpty("Enter Author Name: ");
        String category = readNonEmpty("Enter Category: ");

        Book book = new Book(
                id,
                title,
                author,
                category
        );

        if (library.addBook(book)) {

            System.out.println("\nBook added successfully!");

        } else {

            System.out.println("\nUnable to add book.");
        }
    }

    private static void searchBook() {

        System.out.println("\n========================= SEARCH BOOK =========================");

        String keyword = readNonEmpty(
                "Enter Book ID, title, author or category: "
        );

        library.searchBook(keyword);
    }

    private static void removeBook() {

        System.out.println("\n========================= REMOVE BOOK =========================");

        int bookId = readPositiveInt("Enter Book ID: ");

        Book book = library.findBookById(bookId);

        if (book == null) {

            System.out.println("Book not found.");
            return;
        }

        if (!book.isAvailable()) {

            System.out.println(
                    "This book is currently issued and cannot be removed."
            );

            return;
        }

        String confirmation = readNonEmpty(
                "Are you sure you want to remove this book? (yes/no): "
        );

        if (confirmation.equalsIgnoreCase("yes")) {

            if (library.removeBook(bookId)) {

                System.out.println("Book removed successfully.");

            } else {

                System.out.println("Unable to remove the book.");
            }

        } else {

            System.out.println("Operation cancelled.");
        }
    }

    // ============================================================
    // STUDENT FUNCTIONS
    // ============================================================

    private static void registerStudent() {

        System.out.println("\n========================= REGISTER STUDENT =========================");

        int id = readPositiveInt("Enter Student ID: ");

        if (library.findStudentById(id) != null) {

            System.out.println("A student with this ID already exists.");
            return;
        }

        String name = readNonEmpty("Enter Student Name: ");
        String email = readNonEmpty("Enter Student Email: ");

        if (!isValidEmail(email)) {

            System.out.println(
                    "Invalid email format. Please enter a valid email."
            );

            return;
        }

        Student student = new Student(
                id,
                name,
                email
        );

        if (library.addStudent(student)) {

            System.out.println("\nStudent registered successfully!");

        } else {

            System.out.println("\nUnable to register student.");
        }
    }

    private static void searchStudent() {

        System.out.println("\n========================= SEARCH STUDENT =========================");

        String keyword = readNonEmpty(
                "Enter Student ID, name or email: "
        );

        library.searchStudent(keyword);
    }

    // ============================================================
    // TRANSACTION FUNCTIONS
    // ============================================================

    private static void issueBook() {

        System.out.println("\n========================= ISSUE BOOK =========================");

        int studentId = readPositiveInt("Enter Student ID: ");
        int bookId = readPositiveInt("Enter Book ID: ");

        String result = library.issueBook(
                bookId,
                studentId
        );

        switch (result) {

            case "BOOK_NOT_FOUND":
                System.out.println("Book not found.");
                break;

            case "STUDENT_NOT_FOUND":
                System.out.println("Student not found.");
                break;

            case "BOOK_NOT_AVAILABLE":
                System.out.println("This book is already issued.");
                break;

            case "ALREADY_ISSUED":
                System.out.println("This book is already issued to this student.");
                break;

            case "SUCCESS":
                System.out.println("Book issued successfully!");
                break;

            default:
                System.out.println("Unable to issue the book.");
        }
    }

    private static void returnBook() {

        System.out.println("\n========================= RETURN BOOK =========================");

        int bookId = readPositiveInt("Enter Book ID: ");

        String result = library.returnBook(bookId);

        switch (result) {

            case "BOOK_NOT_FOUND":
                System.out.println("Book not found.");
                break;

            case "NOT_ISSUED":
                System.out.println("This book is not currently issued.");
                break;

            case "SUCCESS":
                System.out.println("Book returned successfully!");
                break;

            default:
                System.out.println("Unable to return the book.");
        }
    }

    // ============================================================
    // SORTING
    // ============================================================

    private static void sortBooks() {

        System.out.println("\n========================= SORT BOOKS =========================");

        System.out.println("1. Sort by Title");
        System.out.println("2. Sort by Author");
        System.out.println("3. Sort by Book ID");

        int choice = readInt("Enter your choice: ");

        switch (choice) {

            case 1:
                library.sortBooksByTitle();
                library.viewBooks();
                break;

            case 2:
                library.sortBooksByAuthor();
                library.viewBooks();
                break;

            case 3:
                library.sortBooksById();
                library.viewBooks();
                break;

            default:
                System.out.println("Invalid sorting option.");
        }
    }

    // ============================================================
    // INPUT VALIDATION
    // ============================================================

    private static int readInt(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            try {

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input. Please enter a valid number."
                );
            }
        }
    }

    private static int readPositiveInt(String message) {

        while (true) {

            int number = readInt(message);

            if (number > 0) {
                return number;
            }

            System.out.println(
                    "Please enter a number greater than zero."
            );
        }
    }

    private static String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);

            String input = scanner.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty. Please try again."
            );
        }
    }

    private static boolean isValidEmail(String email) {

        return email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        );
    }

    private static void pressEnter() {

        System.out.println();
        System.out.print("Press Enter to continue...");

        scanner.nextLine();
    }
}