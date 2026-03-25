import java.sql.*;


/**
 * Handles all CRUD operations for the Comment table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public class CommentHandler extends DBCRUDHandler<Comment> {

    /**
     * Passes database credentials to the parent DBCRUDHandler.
     */
    public CommentHandler(String url, String user, String password) {
        super(url, user, password);
    }

    /**
     * Inserts a new Comment into the database.
     * Supports both top-level comments and replies (parentCommentId may be null).
     *
     * @param comment The Comment object to insert.
     * @throws SQLException If the INSERT fails.
     */
    @Override
    public boolean create(Comment comment) throws SQLException {
        String sql = "INSERT INTO comment (courseId, accountId, parentCommentId, content, anonymous) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, comment.getCourseId());
            stmt.setInt(2, comment.getAccountId());

            if (comment.getParentCommentId() == null) {
                stmt.setNull(3, Types.INTEGER);
            } else {
                stmt.setInt(3, comment.getParentCommentId());
            }

            stmt.setString(4, comment.getContent());
            stmt.setBoolean(5, comment.isAnonymous());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        String sql = "SELECT * FROM comment WHERE commentId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Comment(
                    rs.getInt("commentId"),
                    rs.getInt("courseId"),
                    rs.getInt("accountId"),
                    rs.getObject("parentCommentId") == null ? null : rs.getInt("parentCommentId"),
                    rs.getString("content"),
                    rs.getBoolean("anonymous")
                );
            }
        }
        return null;
    }


    /**
     * Updates an existing Comment record.
     * Only content and anonymity typically change.
     *
     * @param comment The updated Comment object.
     * @throws SQLException If the UPDATE fails.
     */
    @Override
    public boolean update(Comment comment) throws SQLException {
        String sql = "UPDATE comment SET content = ?, anonymous = ? WHERE commentId = ?";

        try (Connection conn = open();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, comment.getContent());
            stmt.setBoolean(2, comment.isAnonymous());
            stmt.setInt(3, comment.getCommentId());

            stmt.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
        String sql = "DELETE FROM comment WHERE commentId = ?";

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