package service;

import model.Book;
import model.Student;
import model.IssueRecord;
import util.FileManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Library {

    private ArrayList<Book> books;
    private ArrayList<Student> students;
    private ArrayList<IssueRecord> issueRecords;

    private FileManager fileManager;

    public Library() {

        fileManager = new FileManager();

        books = fileManager.loadBooks();
        students = fileManager.loadStudents();
        issueRecords = fileManager.loadIssues();
    }

    // ============================================================
    // BOOK METHODS
    // ============================================================

    public boolean addBook(Book book) {

        if (findBookById(book.getBookId()) != null) {
            return false;
        }

        books.add(book);
        return true;
    }

    public void viewBooks() {

        if (books.isEmpty()) {
            System.out.println("\nNo books available.");
            return;
        }

        System.out.println("\n========================= ALL BOOKS =========================");

        for (Book book : books) {
            System.out.println(book);
        }

        System.out.println("=============================================================");
    }

    public Book findBookById(int id) {

        for (Book book : books) {

            if (book.getBookId() == id) {
                return book;
            }
        }

        return null;
    }

    public void searchBook(String keyword) {

        boolean found = false;

        String search = keyword.toLowerCase();

        System.out.println("\n========================= SEARCH RESULTS =========================");

        for (Book book : books) {

            if (
                    String.valueOf(book.getBookId()).contains(search) ||
                    book.getTitle().toLowerCase().contains(search) ||
                    book.getAuthor().toLowerCase().contains(search) ||
                    book.getCategory().toLowerCase().contains(search)
            ) {

                System.out.println(book);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching book found.");
        }

        System.out.println("==================================================================");
    }

    public boolean removeBook(int bookId) {

        Book book = findBookById(bookId);

        if (book == null) {
            return false;
        }

        if (!book.isAvailable()) {
            return false;
        }

        return books.remove(book);
    }

    public void sortBooksByTitle() {

        books.sort(
                Comparator.comparing(
                        Book::getTitle,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("\nBooks sorted by title.");
    }

    public void sortBooksByAuthor() {

        books.sort(
                Comparator.comparing(
                        Book::getAuthor,
                        String.CASE_INSENSITIVE_ORDER
                )
        );

        System.out.println("\nBooks sorted by author.");
    }

    public void sortBooksById() {

        books.sort(
                Comparator.comparingInt(Book::getBookId)
        );

        System.out.println("\nBooks sorted by ID.");
    }

    // ============================================================
    // STUDENT METHODS
    // ============================================================

    public boolean addStudent(Student student) {

        if (findStudentById(student.getStudentId()) != null) {
            return false;
        }

        students.add(student);
        return true;
    }

    public void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("\nNo students registered.");
            return;
        }

        System.out.println("\n========================= REGISTERED STUDENTS =========================");

        for (Student student : students) {
            System.out.println(student);
        }

        System.out.println("=======================================================================");
    }

    public Student findStudentById(int id) {

        for (Student student : students) {

            if (student.getStudentId() == id) {
                return student;
            }
        }

        return null;
    }

    public void searchStudent(String keyword) {

        boolean found = false;

        String search = keyword.toLowerCase();

        System.out.println("\n========================= SEARCH RESULTS =========================");

        for (Student student : students) {

            if (
                    String.valueOf(student.getStudentId()).contains(search) ||
                    student.getName().toLowerCase().contains(search) ||
                    student.getEmail().toLowerCase().contains(search)
            ) {

                System.out.println(student);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching student found.");
        }

        System.out.println("==================================================================");
    }

    // ============================================================
    // ISSUE / RETURN METHODS
    // ============================================================

    public String issueBook(int bookId, int studentId) {

        Book book = findBookById(bookId);

        if (book == null) {
            return "BOOK_NOT_FOUND";
        }

        Student student = findStudentById(studentId);

        if (student == null) {
            return "STUDENT_NOT_FOUND";
        }

        if (!book.isAvailable()) {
            return "BOOK_NOT_AVAILABLE";
        }

        // Prevent duplicate issue record
        for (IssueRecord record : issueRecords) {

            if (
                    record.getBookId() == bookId &&
                    record.getStudentId() == studentId
            ) {
                return "ALREADY_ISSUED";
            }
        }

        book.setAvailable(false);

        IssueRecord record = new IssueRecord(
                bookId,
                studentId
        );

        issueRecords.add(record);

        return "SUCCESS";
    }

    public String returnBook(int bookId) {

        Book book = findBookById(bookId);

        if (book == null) {
            return "BOOK_NOT_FOUND";
        }

        IssueRecord recordToRemove = null;

        for (IssueRecord record : issueRecords) {

            if (record.getBookId() == bookId) {

                recordToRemove = record;
                break;
            }
        }

        if (recordToRemove == null) {
            return "NOT_ISSUED";
        }

        book.setAvailable(true);

        issueRecords.remove(recordToRemove);

        return "SUCCESS";
    }

    public void viewIssuedBooks() {

        if (issueRecords.isEmpty()) {

            System.out.println("\nNo books are currently issued.");
            return;
        }

        System.out.println("\n========================= ISSUED BOOKS =========================");

        for (IssueRecord record : issueRecords) {

            Book book = findBookById(record.getBookId());
            Student student = findStudentById(record.getStudentId());

            System.out.println(
                    "Book: " +
                    (book != null ? book.getTitle() : "Unknown") +
                    " | Book ID: " +
                    record.getBookId() +
                    " | Student: " +
                    (student != null ? student.getName() : "Unknown") +
                    " | Student ID: " +
                    record.getStudentId() +
                    " | Date: " +
                    record.getIssueDate()
            );
        }

        System.out.println("===============================================================");
    }

    // ============================================================
    // STATISTICS
    // ============================================================

    public void showStatistics() {

        int totalBooks = books.size();
        int totalStudents = students.size();
        int issuedBooks = issueRecords.size();
        int availableBooks = totalBooks - issuedBooks;

        System.out.println("\n========================= LIBRARY STATISTICS =========================");

        System.out.println("Total Books       : " + totalBooks);
        System.out.println("Available Books   : " + availableBooks);
        System.out.println("Issued Books      : " + issuedBooks);
        System.out.println("Registered Users  : " + totalStudents);

        System.out.println("======================================================================");
    }

    // ============================================================
    // SAVE DATA
    // ============================================================

    public void saveData() {

        fileManager.saveBooks(books);
        fileManager.saveStudents(students);
        fileManager.saveIssues(issueRecords);

        System.out.println("\nData saved successfully.");
    }

    // ============================================================
    // GETTERS
    // ============================================================

    public List<Book> getBooks() {
        return books;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<IssueRecord> getIssueRecords() {
        return issueRecords;
    }
}