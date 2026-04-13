package app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class Search {
    private DBConnection connection;

    public search(DBConnection connection) {
        this.connection = connection;
    }

    public List<Course> searchCourses(String key) throws SQLException {
        String query;
        List<Course> results = new ArrayList<>();

        String sql = """
            SELECT * FROM courses
            WHERE LOWER(course_name) LIKE ?
            OR LOWER(professor_name) LIKE ?
            OR LOWER(course_number) LIKE ?
            """;

        try (
                Connection sqlConnection = connection.getConnection();
                PreparedStatement s = connection.get(s);
        )   {
                String querySearch = "%" + key.toLowerCase() + "%";

                s.setString(1, querySearch);
                s.setString(2, querySearch);
                s.setString(3, querySearch);

                ResultSet rs = s.executeQuery();

                while (rs.next()) {
                    Course c = new Course();
                    c.setCourseName(rs.getString("course_name"));
                    c.setProfessorName(rs.getString("professor_name"));
                    c.setCourseNumber(rs.getString("course_number"));
                    results.add(c);
                }
        }

    }


}
