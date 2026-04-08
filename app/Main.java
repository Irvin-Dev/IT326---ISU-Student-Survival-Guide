package app;

import java.sql.DriverManager;

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
        AccountHandler accountHandler = new AccountHandler(connection);
        CourseHandler courseHandler = new CourseHandler(connection);
        CommentHandler commentHandler = new CommentHandler(connection);

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



            System.out.println("\n=== TESTING COURSE HANDLER ===");

            Course course = new Course(0, "Data Structures", 200, "CS");
            courseHandler.create(course);

            System.out.println("Inserted Course.");

            Course fetchedCourse = courseHandler.get(0);
            System.out.println("Fetched Course: " + fetchedCourse);

            fetchedCourse.setCourseName("Advanced Data Structures");
            courseHandler.update(fetchedCourse);
            System.out.println("Updated Course.");


            System.out.println("\n=== TESTING COMMENT HANDLER ===");

            Comment comment = new Comment(0,0,"This is a comment.");
            
            System.out.println("Created Comment object: " + comment);
            commentHandler.create(comment);
            System.out.println("Inserted Comment.");

            Comment fetchedComment = commentHandler.get(6);
            System.out.println("Fetched Comment: " + fetchedComment);

            fetchedComment.setContent("Updated comment text.");
            commentHandler.update(fetchedComment);
            System.out.println("Updated Comment.");

            commentHandler.delete(6);
            System.out.println("Deleted Comment.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
