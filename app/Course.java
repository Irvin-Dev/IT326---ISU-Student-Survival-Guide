package app;
/**
 * Temporary course object to test CourseHandler CRUD operations.
 */
public class Course {

    private int courseId;       // Primary key
    private String courseName;   // Name of the course
    private int courseCode;      // Numeric course code (e.g., 101)
    private String department;  // Department offering the course

    /**
     * Full constructor used when reading from the database.
     */
    public Course(int courseId, String coursename, int courseCode, String department) {
        this.courseId = courseId;
        this.courseName = coursename;
        this.courseCode = courseCode;
        this.department = department;
    }

    /**
     * Constructor used when creating a new course (ID auto-generated).
     */
    public Course(String coursename, int courseCode, String department) {
        this.courseName = coursename;
        this.courseCode = courseCode;
        this.department = department;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                ", department='" + department + '\'' +
                '}';
    }
}