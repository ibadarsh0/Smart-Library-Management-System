<<<<<<< HEAD
# Smart Library Management System

A command-line based Library Management System developed using Core Java. The application allows users to manage books, register students, issue and return books, search records, sort books, view library statistics, and save data using local text files.

## Features

* Add new books
* View all available books
* Search books by ID, title, author, or category
* Register students
* View registered students
* Search students by ID, name, or email
* Issue books to registered students
* Return issued books
* View currently issued books
* Remove available books
* Sort books by title, author, or ID
* View library statistics
* Save and load data using text files
* Input validation and exception handling
* Fully executable through the command line

## Technologies Used

* Java
* Java Collections Framework
* Java File I/O
* Java LocalDate API
* Object-Oriented Programming

## Requirements

Before running the project, make sure the following is installed:

* Java Development Kit (JDK) 21 or later
* Command Prompt, PowerShell, or any terminal

Check your Java installation using:

```bash
java -version
```

Check the Java compiler using:

```bash
javac -version
```

## Project Structure

```text
smart-library-management-java/
│
├── src/
│   ├── Main.java
│   │
│   ├── model/
│   │   ├── Book.java
│   │   ├── Student.java
│   │   └── IssueRecord.java
│   │
│   ├── service/
│   │   └── Library.java
│   │
│   └── util/
│       └── FileManager.java
│
├── data/
│   ├── books.txt
│   ├── students.txt
│   └── issues.txt
│
├── README.md
└── .gitignore
```

## Java Concepts Demonstrated

This project demonstrates several concepts from Programming in Java:

### Object-Oriented Programming

* Classes and objects
* Encapsulation
* Constructors
* Private fields
* Getters and setters
* Method implementation

### Collections

The project uses `ArrayList` to store:

* Books
* Students
* Issue records

### File Handling

Java file I/O is used to store and retrieve data from:

```text
data/books.txt
data/students.txt
data/issues.txt
```

The project uses classes such as:

* `FileReader`
* `FileWriter`
* `BufferedReader`
* `BufferedWriter`

### Exception Handling

The application handles invalid numerical input using exception handling so that the program does not terminate unexpectedly.

### Date Handling

`LocalDate` is used to store the date on which a book is issued.

### Sorting

Books can be sorted using Java's `Comparator` functionality.

### Input Validation

The application validates:

* Numeric input
* Positive IDs
* Empty input
* Email format
* Duplicate book IDs
* Duplicate student IDs
* Book availability

## How to Run

### Step 1: Clone the Repository

Clone the repository using:

```bash
git clone https://github.com/YOUR-USERNAME/smart-library-management-java.git
```

Replace `YOUR-USERNAME` with the GitHub username of the repository owner.

Move into the project directory:

```bash
cd smart-library-management-java
```

### Step 2: Compile the Project

For Windows PowerShell or Command Prompt:

```powershell
javac -d out src\Main.java src\model\*.java src\service\*.java src\util\*.java
```

For Linux/macOS:

```bash
javac -d out src/Main.java src/model/*.java src/service/*.java src/util/*.java
```

If compilation is successful, an `out` directory containing the compiled Java classes will be created.

### Step 3: Run the Application

Windows:

```powershell
java -cp out Main
```

Linux/macOS:

```bash
java -cp out Main
```

## Main Menu

After starting the application, the following menu is displayed:

```text
==============================================================
                        MAIN MENU
==============================================================
1.  Add Book
2.  View All Books
3.  Search Book
4.  Register Student
5.  View Students
6.  Search Student
7.  Issue Book
8.  Return Book
9.  View Issued Books
10. Remove Book
11. Sort Books
12. Library Statistics
13. Save Data
14. Exit
==============================================================
```

## Example Workflow

### 1. Add a Book

Select:

```text
1. Add Book
```

Enter details such as:

```text
Book ID: 101
Title: Clean Code
Author: Robert Martin
Category: Programming
```

### 2. Register a Student

Select:

```text
4. Register Student
```

Example:

```text
Student ID: 23001
Name: Rahul
Email: rahul@example.com
```

### 3. Issue a Book

Select:

```text
7. Issue Book
```

Enter the student ID and book ID.

If the book is available and the student exists, the book will be issued.

### 4. Return a Book

Select:

```text
8. Return Book
```

Enter the book ID.

The book status will change back to `Available`.

### 5. Save Data

Select:

```text
13. Save Data
```

The records are stored in the files inside the `data` directory.

## Data Storage

The project does not require a database.

Data is stored locally in three text files:

```text
data/books.txt
data/students.txt
data/issues.txt
```

This makes the project simple to set up and execute from a terminal.

## Error Handling

The application provides messages for common invalid operations, including:

* Invalid menu choices
* Non-numeric IDs
* Negative or zero IDs
* Duplicate book IDs
* Duplicate student IDs
* Non-existent books
* Non-existent students
* Issuing an already issued book
* Returning a book that is not issued
* Invalid email format
* Attempting to remove an issued book

## Limitations

* The application is command-line based.
* Data is stored in local text files rather than a database.
* There is no user authentication system.
* The application is designed for a single local user.

## Future Improvements

Possible future improvements include:
=======
# Smart-Library-Management-System
 A command-line Library Management System built with Core Java featuring book and student management, issue/return operations, searching, sorting, statistics, file-based data storage, and input validation.
>>>>>>> 5ff11d0a90425014646d1a58cedacafd05b25d92
