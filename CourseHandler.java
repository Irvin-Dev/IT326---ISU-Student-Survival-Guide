import java.sql.*;


/**
 * Handles all CRUD operations for the Course table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class CourseHandler extends DBCRUDHandler<Course> {

    /**
     * Passes database credentials to the parent DBCRUDHandler.
     */
    public CourseHandler(String url, String user, String password) {
        super(url, user, password);
    }

    /**
     * Inserts a new Course into the database.
     *
     * @param course The Course object to insert.
     * @throws SQLException If the INSERT fails.
     */
    @Override
    public boolean create(Course course) throws SQLException {
        String sql = "INSERT INTO course (className, classCode, department) VALUES (?, ?, ?)";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getClassName());
            stmt.setInt(2, course.getClassCode());
            stmt.setString(3, course.getDepartment());

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
    public Course get(int id) throws SQLException {
        String sql = "SELECT * FROM course WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Course(
                    rs.getInt("courseId"),
                    rs.getString("className"),
                    rs.getInt("classCode"),
                    rs.getString("department")
                );
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * Updates an existing Course record.
     *
     * @param course The updated Course object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
    public boolean update(Course course) throws SQLException {
        String sql = "UPDATE course SET className = ?, classCode = ?, department = ? "
                   + "WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getClassName());
            stmt.setInt(2, course.getClassCode());
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
    public boolean delete(int id) throws SQLException {
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