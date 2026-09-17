package model;

import java.time.LocalDate;

public class IssueRecord {

    private int bookId;
    private int studentId;
    private LocalDate issueDate;

    public IssueRecord(int bookId, int studentId) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = LocalDate.now();
    }

    public IssueRecord(int bookId, int studentId, LocalDate issueDate) {
        this.bookId = bookId;
        this.studentId = studentId;
        this.issueDate = issueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public int getStudentId() {
        return studentId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    @Override
    public String toString() {
        return String.format(
                "Book ID: %-6d | Student ID: %-6d | Issue Date: %s",
                bookId,
                studentId,
                issueDate
        );
    }
}