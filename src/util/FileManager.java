package util;

import model.Book;
import model.Student;
import model.IssueRecord;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileManager {

    private static final String DATA_DIRECTORY = "data";
    private static final String BOOK_FILE = DATA_DIRECTORY + File.separator + "books.txt";
    private static final String STUDENT_FILE = DATA_DIRECTORY + File.separator + "students.txt";
    private static final String ISSUE_FILE = DATA_DIRECTORY + File.separator + "issues.txt";

    public FileManager() {
        createDataDirectory();
    }

    private void createDataDirectory() {
        File directory = new File(DATA_DIRECTORY);

        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    // ---------------- BOOKS ----------------

    public void saveBooks(List<Book> books) {

        createDataDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BOOK_FILE))) {

            for (Book book : books) {

                writer.write(
                        book.getBookId() + "|" +
                        clean(book.getTitle()) + "|" +
                        clean(book.getAuthor()) + "|" +
                        clean(book.getCategory()) + "|" +
                        book.isAvailable()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error while saving books: " + e.getMessage());
        }
    }

    public ArrayList<Book> loadBooks() {

        ArrayList<Book> books = new ArrayList<>();

        File file = new File(BOOK_FILE);

        if (!file.exists()) {
            return books;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length == 5) {

                    int id = Integer.parseInt(parts[0]);
                    String title = parts[1];
                    String author = parts[2];
                    String category = parts[3];
                    boolean available = Boolean.parseBoolean(parts[4]);

                    books.add(
                            new Book(
                                    id,
                                    title,
                                    author,
                                    category,
                                    available
                            )
                    );
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while loading books: " + e.getMessage());
        }

        return books;
    }

    // ---------------- STUDENTS ----------------

    public void saveStudents(List<Student> students) {

        createDataDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(STUDENT_FILE))) {

            for (Student student : students) {

                writer.write(
                        student.getStudentId() + "|" +
                        clean(student.getName()) + "|" +
                        clean(student.getEmail())
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error while saving students: " + e.getMessage());
        }
    }

    public ArrayList<Student> loadStudents() {

        ArrayList<Student> students = new ArrayList<>();

        File file = new File(STUDENT_FILE);

        if (!file.exists()) {
            return students;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length == 3) {

                    int id = Integer.parseInt(parts[0]);

                    students.add(
                            new Student(
                                    id,
                                    parts[1],
                                    parts[2]
                            )
                    );
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while loading students: " + e.getMessage());
        }

        return students;
    }

    // ---------------- ISSUE RECORDS ----------------

    public void saveIssues(List<IssueRecord> issues) {

        createDataDirectory();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ISSUE_FILE))) {

            for (IssueRecord issue : issues) {

                writer.write(
                        issue.getBookId() + "|" +
                        issue.getStudentId() + "|" +
                        issue.getIssueDate()
                );

                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error while saving issue records: " + e.getMessage());
        }
    }

    public ArrayList<IssueRecord> loadIssues() {

        ArrayList<IssueRecord> issues = new ArrayList<>();

        File file = new File(ISSUE_FILE);

        if (!file.exists()) {
            return issues;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\\|", -1);

                if (parts.length == 3) {

                    int bookId = Integer.parseInt(parts[0]);
                    int studentId = Integer.parseInt(parts[1]);
                    LocalDate date = LocalDate.parse(parts[2]);

                    issues.add(
                            new IssueRecord(
                                    bookId,
                                    studentId,
                                    date
                            )
                    );
                }
            }

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error while loading issue records: " + e.getMessage());
        }

        return issues;
    }

    private String clean(String value) {

        if (value == null) {
            return "";
        }

        return value.replace("|", "/");
    }
}