package app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all CRUD operations for the Course table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class SQLCourseHandler extends CourseHandler {

    /**
     * Passes database credentials to the parent DBCRUDHandler.
     */
        public SQLCourseHandler(DBConnection connection) {
        super(connection);
    }

    /**
     * Inserts a new Course into the database.
     *
     * @param course The Course object to insert.
     * @throws SQLException If the INSERT fails.
     */
    @Override
    public boolean create(Course course){
        if(this.get(course.getCourseId()) != null) {
            System.out.println("Course with ID " + course.getCourseId() + " already exists.");
            return false;
        }
        String sql = "INSERT INTO course (COURSEID, COURSENAME, COURSECODE, department) VALUES (?, ?, ?, ?)";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, course.getCourseId());
            stmt.setString(2, course.getCourseName());
            stmt.setInt(3, course.getCourseCode());
            stmt.setString(4, course.getDepartment());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Retrieves a Course by its primary key.
     *
     * @param id The courseId to search for.
     * @return The Course object, or null if not found.
     * @throws SQLException If the SELECT fails.
     */
    @Override
    public Course get(int id) {
        String sql = "SELECT * FROM course WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(
                    rs.getInt("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("courseCode"),
                    rs.getString("department")
                );
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Course> getAll()  {
        String sql = "SELECT * FROM course";
        List<Course> courses = new ArrayList<>();

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                courses.add(new Course(
                    rs.getInt("courseId"),
                    rs.getString("courseName"),
                    rs.getInt("courseCode"),
                    rs.getString("department")
                ));
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }


    /**
     * Updates an existing Course record.
     *
     * @param course The updated Course object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
    public boolean update(Course course) {
        String sql = "UPDATE course SET courseName = ?, courseCode = ?, department = ? "
                   + "WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourseName());
            stmt.setInt(2, course.getCourseCode());
            stmt.setString(3, course.getDepartment());
            stmt.setInt(4, course.getCourseId());
          

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Deletes a Course by its primary key.
     *
     * @param id The courseId to delete.
     * @throws SQLException If the DELETE fails.
     */
    @Override
    public boolean delete(int id)  {
        String sql = "DELETE FROM course WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    
}