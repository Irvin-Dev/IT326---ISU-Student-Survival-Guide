package app;

import java.sql.DriverManager;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        DBConnection connection = new SQLConnection(
                "jdbc:oracle:thin:@10.110.10.90:1521:oracle",
                "IT326S09",
                "pink22"
        );

        System.out.println("Drivers loaded: " + DriverManager.getDrivers());

        DBCRUDHandler<Account> accountHandler = new SQLAccountHandler(connection);
        DBCRUDHandler<Course> courseHandler = new SQLCourseHandler(connection);
        DBCRUDHandler<Comment> commentHandler = new SQLCommentHandler(connection);

        try {

            // ============================================================
            //  ACCOUNT TESTS
            // ============================================================
            System.out.println("\n=== TESTING ACCOUNT HANDLER ===");

            Account acc1 = new Account(0, "julia", "julia@example.com", "password123", 2, true, true, "CS");
            Account acc2 = new Account(0, "mike", "mike@example.com", "pass98765", 1, true, false, "IT");

            accountHandler.create(acc1);
            accountHandler.create(acc2);

            System.out.println("Inserted Accounts.");

            Account fetchedAcc1 = accountHandler.getById(0);
            System.out.println("Fetched Account 0: " + fetchedAcc1);

            // Update email
            fetchedAcc1.setEmail("julia_updated@example.com");
            accountHandler.update(fetchedAcc1);
            System.out.println("Updated Account 0 email.");

            // Get all accounts
            List<Account> allAccounts = accountHandler.getAll();
            System.out.println("All Accounts:");
            allAccounts.forEach(System.out::println);

            // Delete account test
            System.out.println("Deleting account with ID 0...");
            accountHandler.delete(0);

            System.out.println("Accounts after deletion:");
            accountHandler.getAll().forEach(System.out::println);


            // ============================================================
            //  COURSE TESTS
            // ============================================================
            System.out.println("\n=== TESTING COURSE HANDLER ===");

            Course c1 = new Course("Data Structures", 0, 200, "CS");
            Course c2 = new Course("Operating Systems", 0, 300, "CS");

            courseHandler.create(c1);
            courseHandler.create(c2);

            System.out.println("Inserted Courses.");

            Course fetchedCourse = courseHandler.getById(0);
            System.out.println("Fetched Course 0: " + fetchedCourse);

            // Update course name
            if (fetchedCourse != null) {
                fetchedCourse.setCourseName("Advanced Data Structures");
                courseHandler.update(fetchedCourse);
                System.out.println("Updated Course 0 name.");
            }

            // Get all courses
            List<Course> allCourses = courseHandler.getAll();
            System.out.println("All Courses:");
            allCourses.forEach(System.out::println);

            // Delete course test
            System.out.println("Deleting course with ID 0...");
            courseHandler.delete(0);

            System.out.println("Courses after deletion:");
            courseHandler.getAll().forEach(System.out::println);


            // ============================================================
            //  COMMENT TESTS
            // ============================================================
            System.out.println("\n=== TESTING COMMENT HANDLER ===");

            // Insert a new account + course for comment foreign keys
            Account acc3 = new Account(0, "commenter", "c@example.com", "securepass", 1, true, true, "CS");
            accountHandler.create(acc3);

            Course c3 = new Course("Algorithms", 0, 250, "CS");
            courseHandler.create(c3);

            // Create top-level comment
            Comment topComment = new Comment(
                    0,
                    "This class is amazing!",
                    0,   // accountId
                    0,   // courseId
                    null,
                    0,
                    null,
                    null
            );

            commentHandler.create(topComment);
            System.out.println("Inserted top-level comment.");

            // Create reply comment
            Comment reply = new Comment(
                    0,
                    "I agree with you!",
                    0,
                    0,
                    1,   // parentCommentId
                    0,
                    null,
                    null
            );

            commentHandler.create(reply);
            System.out.println("Inserted reply comment.");

            // Fetch comment
            Comment fetchedComment = commentHandler.getById(1);
            System.out.println("Fetched Comment 1: " + fetchedComment);

            // Update comment content
            if (fetchedComment != null) {
                fetchedComment.updateContent("Updated comment text.");
                commentHandler.update(fetchedComment);
                System.out.println("Updated Comment 1.");
            }

            // Get all comments
            List<Comment> allComments = commentHandler.getAll();
            System.out.println("All Comments:");
            allComments.forEach(System.out::println);

            // Delete reply
            System.out.println("Deleting reply comment ID 2...");
            commentHandler.delete(2);

            // Delete top-level comment
            System.out.println("Deleting top-level comment ID 1...");
            commentHandler.delete(1);

            System.out.println("Comments after deletion:");
            commentHandler.getAll().forEach(System.out::println);


            // ============================================================
            //  EDGE CASE TESTS
            // ============================================================
            System.out.println("\n=== EDGE CASE TESTS ===");

            System.out.println("Fetching non-existent account ID 999:");
            System.out.println(accountHandler.getById(999));

            System.out.println("Fetching non-existent course ID 999:");
            System.out.println(courseHandler.getById(999));

            System.out.println("Fetching non-existent comment ID 999:");
            System.out.println(commentHandler.getById(999));

            System.out.println("Attempting to delete non-existent comment ID 999:");
            System.out.println(commentHandler.delete(999));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
