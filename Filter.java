import java.sql.*;
import java.util.ArrayList;
import java.util.List;

interface Filter {
    ArrayList<Course> filterByLevel();

    ArrayList<Course> sortByRating();

    ArrayList<Course> sortByDifficulty();
}
