import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface Search {
    ArrayList<Course> search(String query);
}
