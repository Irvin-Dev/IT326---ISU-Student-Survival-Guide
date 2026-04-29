package app;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Handles all CRUD operations for the Comment table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class SQLCommentHandler extends DBCRUDHandler<Comment> implements CourseTagLookup{

    /**
     * Creates an CommentHandler using the given database connection.
     *
     * @param connection Database connection provider.     */
    public SQLCommentHandler(DBConnection connection) {
        super(connection);
    }

    /**
     * Inserts a new Comment into the database.
     * Supports both top-level comments and replies (parentCommentId may be null).
     *
     * @param comment The Comment object to insert.
     * @return True if the insert succeeds.
     * @throws SQLException If the INSERT fails.
     */

public boolean create(Comment comment) {


    String sql = "INSERT INTO commentinfo "
               + "(courseid, accountid, commentcontent, createdat, updatedat, likes, parentcommentid) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Required foreign keys
        stmt.setInt(1, comment.getCourseId());
        stmt.setInt(2, comment.getAccountId());

        // Content
        stmt.setString(3, comment.getCommentContent());

        // Timestamps
        Timestamp now = new Timestamp(System.currentTimeMillis());
        stmt.setTimestamp(4, now); // createdAt
        stmt.setTimestamp(5, now); // updatedAt

        // Likes default to 0
        stmt.setInt(6, comment.getLikes());

        // Nullable parentCommentId
        if (comment.getParentCommentId() == null) {
             stmt.setNull(7, Types.INTEGER);    
        } else {
            stmt.setInt(7, comment.getParentCommentId());
        }
        String sqlCourseRating = "Insert into rating (courseid, commentid, ratingvalue) VALUES (?, ?, ?)";

        try (PreparedStatement stmt2 = conn.prepareStatement(sqlCourseRating)) {
            stmt2.setInt(1, comment.getCourseId());
            stmt2.setInt(2, comment.getCommentId());
            stmt2.setDouble(3, comment.getCourseRating());
            stmt2.executeUpdate();
        }
        String sqlDifficultyRating = "Insert into difficulty (courseid, commentid, difficultynumber) VALUES (?, ?, ?)";


        try (PreparedStatement stmt3 = conn.prepareStatement(sqlDifficultyRating)) {
            stmt3.setInt(1, comment.getCourseId());
            stmt3.setInt(2, comment.getCommentId());
            stmt3.setDouble(3, comment.getDifficultyRating());
            stmt3.executeUpdate();
        }

        stmt.executeUpdate();
        return true;
    }
    catch(SQLException e) {
        e.printStackTrace(); 
        return false;   
    }
    
}



    /**
     * Retrieves a Comment by its primary key.
     *
     * @param id The commentId to search for.
     * @return The Comment object, or null if not found.
     * @throws SQLException If the SELECT fails.
     */
@Override
public Comment getById(int id) {
    String sql = "SELECT commentid, courseid, accountid, parentcommentid, "
               + "commentcontent, likes, createdat, updatedat "
               + "FROM commentinfo WHERE commentid = ?";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {

            Integer parentId = rs.getObject("parentcommentid") == null
                    ? null
                    : rs.getInt("parentcommentid");

            Comment comment = new Comment(
                rs.getInt("commentid"),
                rs.getString("commentcontent"),
                rs.getInt("accountid"),
                rs.getInt("courseid"),
                parentId,
                rs.getInt("likes"),
                rs.getTimestamp("createdat"),
                rs.getTimestamp("updatedat")
            );
            List<CourseTag> courseTags = getAllCourseTagsById(rs.getInt("commentid"));
            if(courseTags.size()>0) {
                for(int i = 0; i < courseTags.size(); i++) {
                    comment.addCourseTag(courseTags.get(i));
                }
            }
            String sqlRating = "SELECT ratingvalue FROM rating WHERE commentid = ? AND courseid = ?";
            String sqlDifficultyRating = "SELECT difficultynumber FROM difficulty WHERE commentid = ? AND courseid = ?";
            try (PreparedStatement stmtRating = conn.prepareStatement(sqlRating);
                 PreparedStatement stmtDifficultyRating = conn.prepareStatement(sqlDifficultyRating)) {

                stmtRating.setInt(1, rs.getInt("commentid"));
                stmtRating.setInt(2, rs.getInt("courseid"));
                ResultSet rsRating = stmtRating.executeQuery();

                if (rsRating.next()) {
                    comment.setCourseRating(rsRating.getDouble("ratingvalue"));
                }

                stmtDifficultyRating.setInt(1, rs.getInt("commentid"));
                stmtDifficultyRating.setInt(2, rs.getInt("courseid"));
                ResultSet rsDifficultyRating = stmtDifficultyRating.executeQuery();

                if (rsDifficultyRating.next()) {
                    comment.setDifficultyRating(rsDifficultyRating.getDouble("difficultynumber"));
                }
            }

            return comment;

        }
    }
    catch(SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    /**
     * Retrieves all Comment records from the database.
     *
     * @return List of all Comments, or empty list if none found.
     * @throws SQLException If the SELECT fails.
     */
@Override
public List<Comment> getAll() {
    String sql = "SELECT commentid, courseid, accountid, parentcommentid, "
               + "commentcontent, likes, createdat, updatedat "
               + "FROM commentinfo";

    List<Comment> comments = new ArrayList<>();

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {

            Integer parentId = rs.getObject("parentcommentid") == null
                    ? null
                    : rs.getInt("parentcommentid");
                    


            Comment comment = new Comment(
                rs.getInt("commentid"),
                rs.getString("commentcontent"),
                rs.getInt("accountid"),
                rs.getInt("courseid"),
                parentId,
                rs.getInt("likes"),
                rs.getTimestamp("createdat"),
                rs.getTimestamp("updatedat")
            );
            List<CourseTag> courseTags = getAllCourseTagsById(rs.getInt("commentid"));
            if(courseTags.size()>0) {
                for(int i = 0; i < courseTags.size(); i++) {
                    comment.addCourseTag(courseTags.get(i));
                }

            }
           String sqlRating = "SELECT ratingvalue FROM rating WHERE commentid = ? AND courseid = ?";
           String sqlDifficultyRating = "SELECT difficultynumber FROM difficulty WHERE commentid = ? AND courseid = ?";
           try (PreparedStatement stmtRating = conn.prepareStatement(sqlRating);
                PreparedStatement stmtDifficultyRating = conn.prepareStatement(sqlDifficultyRating)) {

                stmtRating.setInt(1, rs.getInt("commentid"));
                stmtRating.setInt(2, rs.getInt("courseid"));
                ResultSet rsRating = stmtRating.executeQuery();

                if (rsRating.next()) {
                    comment.setCourseRating(rsRating.getDouble("ratingvalue"));
                }

                stmtDifficultyRating.setInt(1, rs.getInt("commentid"));
                stmtDifficultyRating.setInt(2, rs.getInt("courseid"));
                ResultSet rsDifficultyRating = stmtDifficultyRating.executeQuery();

                if (rsDifficultyRating.next()) {
                    comment.setDifficultyRating(rsDifficultyRating.getDouble("difficultynumber"));
                }
            }
            comments.add(comment);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return comments;
}





    /**
     * Updates an existing Comment record.
     * Only content changes.
     *
     * @param comment The updated Comment object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
public boolean update(Comment comment) {
    String sql = "UPDATE commentinfo SET "
               + "commentcontent = ?, "
               + "updatedat = ?, "
               + "likes = ?, "
               + "parentcommentid = ? "
               + "WHERE commentid = ?";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, comment.getCommentContent());
        stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        stmt.setInt(3, comment.getLikes());

        if (comment.getParentCommentId() == null) {
            stmt.setNull(4, Types.INTEGER);
        } else {
            stmt.setInt(4, comment.getParentCommentId());
        }

        stmt.setInt(5, comment.getCommentId());
        if (comment.getCourseRating() != -1) {
            String sqlRating = "UPDATE rating SET ratingvalue = ? WHERE commentId = ? AND courseId = ?";
            try (PreparedStatement stmtRating = conn.prepareStatement(sqlRating)) {
                stmtRating.setDouble(1, comment.getCourseRating());
                stmtRating.setInt(2, comment.getCommentId());
                stmtRating.setInt(3, comment.getCourseId());
                stmtRating.executeUpdate();
            }
        }
        if (comment.getDifficultyRating() != -1) {
            String sqlDifficultyRating = "UPDATE difficulty SET difficultynumber = ? WHERE commentId = ? AND courseId = ?";
            try (PreparedStatement stmtDifficultyRating = conn.prepareStatement(sqlDifficultyRating)) {
                stmtDifficultyRating.setDouble(1, comment.getDifficultyRating());
                stmtDifficultyRating.setInt(2, comment.getCommentId());
                stmtDifficultyRating.setInt(3, comment.getCourseId());
                stmtDifficultyRating.executeUpdate();
            }
        }
        int rows = stmt.executeUpdate();
        return rows > 0;
    }
    catch(SQLException e) {
        e.printStackTrace();
        return false;
    }
}



    /**
     * Deletes a Comment by its primary key.
     * If replies exist, database constraints determine behavior.
     *
     * @param id The commentId to delete.
     * @throws SQLException If the DELETE fails.
     */
    @Override
    public boolean delete(int id)  {
        String sql = "DELETE FROM commentinfo WHERE commentId = ?";

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
        String sql = "SELECT * FROM coursetag WHERE commentid = ?";

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
    
}