package app;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        DBConnection connection = new SQLConnection(
                "jdbc:oracle:thin:@10.110.10.90:1521:oracle",
                "IT326S09",
                "pink22"
        );

        DBCRUDHandler<Account> accountHandler = new SQLAccountHandler(connection);
        DBCRUDHandler<Course> courseHandler = new SQLCourseHandler(connection);
        DBCRUDHandler<Comment> commentHandler = new SQLCommentHandler(connection);

        Scanner scanner = new Scanner(System.in);

        System.out.println("Drivers loaded: " + DriverManager.getDrivers());
        System.out.println("=== Welcome to Course Review System ===");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Create Account");
            System.out.println("2. Create Course");
            System.out.println("3. Create Comment");
            System.out.println("4. View All Accounts");
            System.out.println("5. View All Courses");
            System.out.println("6. View All Comments");
            System.out.println("7. Update Comment");
            System.out.println("8. Delete Comment");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            try {
                switch (choice) {

                    // ---------------------------------------------------------
                    // CREATE ACCOUNT
                    // ---------------------------------------------------------
                    case 1:
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();

                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();

                        System.out.print("Enter year (1=freshman,2= sophomore,3= junior,4= senior): ");
                        int year = scanner.nextInt();

                        scanner.nextLine();
                        System.out.print("Enter department: ");
                        String major = scanner.nextLine();

                        Account newAcc = new Account (username, email, password, year, major);
                        System.out.println("Creating account: " + newAcc);
                        accountHandler.create(newAcc);
                        System.out.println("Account created.");
                        break;

                    // ---------------------------------------------------------
                    // CREATE COURSE
                    // ---------------------------------------------------------
                    case 2:
                        System.out.print("Enter course name: ");
                        String cname = scanner.nextLine();

                        System.out.print("Enter course number: ");
                        int cnum = scanner.nextInt();

                        scanner.nextLine();
                        System.out.print("Enter department: ");
                        String dept = scanner.nextLine();

                        Course newCourse = new Course(cname, 0, cnum, dept,-1,-1);
                        courseHandler.create(newCourse);
                        System.out.println("Course created.");
                        break;

                    // ---------------------------------------------------------
                    // CREATE COMMENT
                    // ---------------------------------------------------------
                    case 3:
                        System.out.print("Enter account ID: ");
                        int accId = scanner.nextInt();

                        System.out.print("Enter course ID: ");
                        int courseId = scanner.nextInt();
                
                        scanner.nextLine();
                        System.out.print("Enter comment text: ");
                        String text = scanner.nextLine();

                        System.out.print("Enter course rating (1-5): ");
                        int rating = scanner.nextInt();

                        System.out.print("Enter difficulty rating (1-5): ");
                        int diff = scanner.nextInt();

                        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

                        Comment newComment = new Comment(text, accId, courseId, null, timestamp, timestamp, rating, diff);
                        commentHandler.create(newComment);
                        System.out.println("Comment created.");
                        break;

                    // ---------------------------------------------------------
                    // VIEW ALL ACCOUNTS
                    // ---------------------------------------------------------
                    case 4:
                        List<Account> accounts = accountHandler.getAll();
                        System.out.println("\n=== All Accounts ===");
                        accounts.forEach(System.out::println);
                        break;

                    // ---------------------------------------------------------
                    // VIEW ALL COURSES
                    // ---------------------------------------------------------
                    case 5:
                        List<Course> courses = courseHandler.getAll();
                        System.out.println("\n=== All Courses ===");
                        courses.forEach(System.out::println);
                        break;

                    // ---------------------------------------------------------
                    // VIEW ALL COMMENTS
                    // ---------------------------------------------------------
                    case 6:
                        List<Comment> comments = commentHandler.getAll();
                        System.out.println("\n=== All Comments ===");
                        comments.forEach(System.out::println);
                        break;

                    // ---------------------------------------------------------
                    // UPDATE COMMENT
                    // ---------------------------------------------------------
                    case 7:
                        System.out.print("Enter comment ID to update: ");
                        int cid = scanner.nextInt();
                        scanner.nextLine();

                        Comment com = commentHandler.getById(cid);
                        if (com == null) {
                            System.out.println("Comment not found.");
                            break;
                        }

                        System.out.print("Enter new text: ");
                        String newText = scanner.nextLine();
                        com.updateContent(newText);

                        System.out.print("Enter new course rating: ");
                        com.setCourseRating(scanner.nextInt());

                        System.out.print("Enter new difficulty rating: ");
                        com.setDifficultyRating(scanner.nextInt());

                        commentHandler.update(com);
                        System.out.println("Comment updated.");
                        break;

                    // ---------------------------------------------------------
                    // DELETE COMMENT
                    // ---------------------------------------------------------
                    case 8:
                        System.out.print("Enter comment ID to delete: ");
                        int delId = scanner.nextInt();
                        commentHandler.delete(delId);
                        System.out.println("Comment deleted.");
                        break;

                    // ---------------------------------------------------------
                    // EXIT
                    // ---------------------------------------------------------
                    case 9:
                        System.out.println("Goodbye!");
                        return;

                    default:
                        System.out.println("Invalid choice.");
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
