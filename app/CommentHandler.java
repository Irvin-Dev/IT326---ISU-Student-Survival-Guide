package app;
import java.sql.*;


/**
 * Handles all CRUD operations for the Comment table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class CommentHandler extends DBCRUDHandler<Comment> {

    /**
     * Creates an CommentHandler using the given database connection.
     *
     * @param connection Database connection provider.     */
    public CommentHandler(DBConnection connection) {
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
public boolean create(Comment comment) throws SQLException {
    if (comment.getCourseId() < 0)
        throw new IllegalArgumentException("courseId must be a valid existing course.");

    if (comment.getAccountId() < 0)
        throw new IllegalArgumentException("accountId must be a valid existing account.");


    String sql = "INSERT INTO commentinfo "
               + "(courseid, accountid, commentcontent, createdat, updatedat, likes, parentcommentid) "
               + "VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Required foreign keys
        stmt.setInt(1, comment.getCourseId());
        stmt.setInt(2, comment.getAccountId());

        // Content
        stmt.setString(3, comment.getContent());

        // Timestamps
        Timestamp now = new Timestamp(System.currentTimeMillis());
        stmt.setTimestamp(4, now); // createdAt
        stmt.setTimestamp(5, now); // updatedAt

        // Likes default to 0
        stmt.setInt(6, comment.getLikes());

        // Nullable parentCommentId
        if (comment.getParentCommentId() == null) {
            stmt.setNull(7, java.sql.Types.INTEGER);
        } else {
            stmt.setInt(7, comment.getParentCommentId());
        }

        stmt.executeUpdate();
        return true;
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
public Comment get(int id) throws SQLException {
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

            return new Comment(
                rs.getInt("commentid"),
                rs.getInt("courseid"),
                rs.getInt("accountid"),
                parentId,
                rs.getString("commentcontent"),
                rs.getInt("likes"),
                rs.getTimestamp("createdat"),
                rs.getTimestamp("updatedat")
            );
        }
    }
    return null;
}






    /**
     * Updates an existing Comment record.
     * Only content changes.
     *
     * @param comment The updated Comment object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
public boolean update(Comment comment) throws SQLException {
    String sql = "UPDATE commentinfo SET "
               + "commentcontent = ?, "
               + "updatedat = ?, "
               + "likes = ?, "
               + "parentcommentid = ? "
               + "WHERE commentid = ?";

    try (Connection conn = open();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, comment.getContent());
        stmt.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
        stmt.setInt(3, comment.getLikes());

        if (comment.getParentCommentId() == null) {
            stmt.setNull(4, Types.INTEGER);
        } else {
            stmt.setInt(4, comment.getParentCommentId());
        }

        stmt.setInt(5, comment.getCommentId());

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
    public boolean delete(int id) throws SQLException {
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
}