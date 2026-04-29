package app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles all CRUD operations for the Course table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class SQLCourseHandler extends DBCRUDHandler<Course> implements CourseTagLookup, DifficultyLookup, RatingLookup {

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
        if(this.getById(course.getCourseId()) != null) {
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
    public Course getById(int id) {
        String sql = "SELECT * FROM course WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Course course = new Course(
                    rs.getString("courseName"),
                    rs.getInt("courseId"),
                    rs.getInt("courseCode"),
                    rs.getString("department"),
                    getRatingsById(id),
                    getDifficultyById(id)
                );
                List<CourseTag> courseTags = getAllCourseTagsById(rs.getInt("courseId"));
                for(int i = 0; i < courseTags.size(); i++) {
                    course.updateTagList(courseTags.get(i));
                }
                return course;
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

                Course course = new Course(
                    rs.getString("courseName"),
                    rs.getInt("courseId"),
                    rs.getInt("courseCode"),
                    rs.getString("department"),
                    getRatingsById(rs.getInt("courseId")),
                    getDifficultyById(rs.getInt("courseId"))
                );
                List<CourseTag> courseTags = getAllCourseTagsById(rs.getInt("courseId"));
                for(int i = 0; i < courseTags.size(); i++) {
                    course.updateTagList(courseTags.get(i));
                }
                courses.add(course);
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

    @Override
    public List<CourseTag> getAllCourseTagsById(int id) {
        String sql = "SELECT * FROM coursetag WHERE courseid = ?";

        List<CourseTag> courseTags = new ArrayList<>();

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String tagName = null;
                    String sqlTagName="SELECT COURSETAGNAME from COURSETAGNAME WHERE COURSETAGNAMEID = ?";
                    try (PreparedStatement stmtTagName = conn.prepareStatement(sqlTagName)) {
                        stmtTagName.setInt(1, rs.getInt("COURSETAGNAMEID"));
                        try (ResultSet rsTagName = stmtTagName.executeQuery()) {
                            if (rsTagName.next()) {
                                tagName = rsTagName.getString("COURSETAGNAME");
                            }
                        }
                    }
                    CourseTag courseTag = new CourseTag(
                        rs.getInt("coursetagnameid"),
                        rs.getInt("courseid"),
                        rs.getInt("commentid"),
                        tagName
                    );
                    courseTags.add(courseTag);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return courseTags;
    }

    @Override
    public double getRatingsById(int id) {
        String sql = "SELECT AVG(ratingvalue) AS average_rating FROM rating WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("average_rating");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public double getDifficultyById(int id) {
        String sql = "SELECT AVG(difficultynumber) AS difficulty FROM DIFFICULTY WHERE courseId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getDouble("difficulty");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

}