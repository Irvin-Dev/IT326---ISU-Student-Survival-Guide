package app;
import java.sql.*;
import java.util.List;


/**
 * Handles all CRUD operations for the Comment table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public abstract class CommentHandler extends DBCRUDHandler<Comment> {

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
     */
    @Override
public abstract boolean create(Comment comment) ;
    




    /**
     * Retrieves a Comment by its primary key.
     *
     * @param id The commentId to search for.
     * @return The Comment object, or null if not found.
     */
@Override
public abstract Comment get(int id);


public abstract List<Comment> getByCourseId(int courseId);



    /**
     * Updates an existing Comment record.
     * Only content changes.
     *
     * @param comment The updated Comment object.
     */
    @Override
public abstract boolean update(Comment comment);



    /**
     * Deletes a Comment by its primary key.
     * If replies exist, database constraints determine behavior.
     *
     * @param id The commentId to delete.
     */
    @Override
    public abstract boolean delete(int id)  ;
}