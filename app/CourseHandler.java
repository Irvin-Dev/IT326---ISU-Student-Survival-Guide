package app;
import java.sql.*;
import java.util.List;


/**
 * Handles all CRUD operations for the Course table.
 * Extends DBCRUDHandler to inherit connection handling and method structure.
 */
public abstract class CourseHandler extends DBCRUDHandler<Course> {

    /**
     * Passes database credentials to the parent DBCRUDHandler.
     */
        public CourseHandler(DBConnection connection) {
        super(connection);
    }

    /**
     * Inserts a new Course into the database.
     *
     * @param course The Course object to insert.
     */
    @Override
    public abstract boolean create(Course course);

    /**
     * Retrieves a Course by its primary key.
     *
     * @param id The courseId to search for.
     * @return The Course object, or null if not found.
     */
    @Override
    public abstract Course get(int id);

    /**
     * Retrieves all Course records.
     *
     * @return Array of all courses, or null if none found.
     */

    public abstract List<Course> getAll();



    /**
     * Updates an existing Course record.
     *
     * @param course The updated Course object.
     */
    @Override
    public abstract boolean update(Course course);

    /**
     * Deletes a Course by its primary key.
     *
     * @param id The courseId to delete.
     */
    @Override
    public abstract boolean delete(int id);
}
 



    
