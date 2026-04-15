package app;

import java.sql.DriverManager;
import java.util.List;


public class Main {
    public static void main(String[] args) {

        // Create a shared DB connection
        DBConnection connection = new SQLConnection(
                "jdbc:oracle:thin:@10.110.10.90:1521:oracle",
                "IT326S09",
                "pink22"
        );
   
        System.out.println("Drivers loaded: " + DriverManager.getDrivers());
        // Handlers
        AccountHandler accountHandler = new SQLAccountHandler(connection);
        CourseHandler courseHandler = new  SQLCourseHandler(connection);
        CommentHandler commentHandler = new SQLCommentHandler(connection);

        try {
            System.out.println("=== TESTING ACCOUNT HANDLER ===");

            Account acc = new Account(0, "julia", "julia@example.com", "password123", 2, true, true, "CS");
            accountHandler.create(acc);
            System.out.println("Inserted Account.");

            Account fetchedAcc = accountHandler.get(0);
            System.out.println("Fetched Account: " + fetchedAcc);

            fetchedAcc.setEmail("updated@example.com");
            accountHandler.update(fetchedAcc);
            System.out.println("Updated Account.");

            List<Account> allAccounts = accountHandler.getAll();
            System.out.println("All Accounts:");
            for (Account a : allAccounts) {
                System.out.println(a);
            }



            System.out.println("\n=== TESTING COURSE HANDLER ===");

            Course course = new Course(0, "Data Structures", 200, "CS");
            courseHandler.create(course);

            System.out.println("Inserted Course.");

            Course fetchedCourse = courseHandler.get(0);
            System.out.println("Fetched Course: " + fetchedCourse);

            if(fetchedCourse != null) {
            fetchedCourse.setCourseName("Advanced Data Structures");
            courseHandler.update(fetchedCourse);
            System.out.println("Updated Course.");
            }

            List<Course> allCourses = courseHandler.getAll();
            System.out.println("All Courses:");
            for (Course c : allCourses) {
                System.out.println(c);
            }


            System.out.println("\n=== TESTING COMMENT HANDLER ===");

            Comment comment = new Comment(0,0,"This is a comment.");
            
            System.out.println("Created Comment object: " + comment);
            commentHandler.create(comment);
            System.out.println("Inserted Comment.");

            Comment fetchedComment = commentHandler.get(9);
            System.out.println("Fetched Comment: " + fetchedComment);

            if(fetchedComment != null) {
            fetchedComment.setContent("Updated comment text.");
            commentHandler.update(fetchedComment);
            System.out.println("Updated Comment.");
            }

            List<Comment> allComments = commentHandler.getAll();
            System.out.println("All Comments:");
            for (Comment c : allComments) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
