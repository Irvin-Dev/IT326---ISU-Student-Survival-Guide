package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Filter {
    private DBConnection connection;

    public Filter(DBConnection connection) {
        this.connection = connection;
    }

    public List<Course> filterByLevel(int level) throws SQLException {
        List<Course> results = new ArrayList<>();

        int lowerBound = level;
        int upperBound = level + 99;

        String sqlQuery = "SELECT * FROM course WHERE course_number BETWEEN ? AND ?";

        try (Connection sqlConnection = connection.getConnection();
            PreparedStatement s = sqlConnection.prepareStatement(sqlQuery)) {

            s.setInt(1, level);

            ResultSet rs = s.executeQuery();

            while (rs.next()) {
                Course c = new Course();
                c.setCourseName(rs.getString("course_name"));
                results.add(c);
            }
        }

        return results;
    }

    public List<Course> sortByRating() throws SQLException {
        List<Course> results = new ArrayList<>();

        //Revisit and change table
        String sqlQuery = "SELECT * FROM course ORDER BY rating DESC";

        try (Connection sqlConnection = connection.getConnection();
             PreparedStatement s = sqlConnection.prepareStatement(sqlQuery)) {

             ResultSet rs = s.executeQuery();

             while (rs.next()) {
                 Course c = new Course();
                 c.setCourseName(rs.getString("course_name"));
                 results.add(c);
             }
        }

        return results;
    }

    public List<Course> sortByDifficulty() throws SQLException {
        List<Course> results = new ArrayList<>();

        //Revisit and change table
        String sqlQuery = "SELECT * FROM course ORDER BY difficulty DESC";

        try (Connection sqlConnection = connection.getConnection();
             PreparedStatement s = sqlConnection.prepareStatement(sqlQuery)) {

             ResultSet rs = s.executeQuery();

             while (rs.next()) {
                 Course c = new course();
                 c.setcourseName(rs.getString("course_name"));
                 result.add(c);
             }
        }
    }

}
