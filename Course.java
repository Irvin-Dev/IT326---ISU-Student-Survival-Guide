/**
 * Temporary course object to test CourseHandler CRUD operations.
 */
public class Course {

    private int courseId;       // Primary key
    private String className;   // Name of the course
    private int classCode;      // Numeric course code (e.g., 101)
    private String department;  // Department offering the course

    /**
     * Full constructor used when reading from the database.
     */
    public Course(int courseId, String className, int classCode, String department) {
        this.courseId = courseId;
        this.className = className;
        this.classCode = classCode;
        this.department = department;
    }

    /**
     * Constructor used when creating a new course (ID auto-generated).
     */
    public Course(String className, int classCode, String department) {
        this.className = className;
        this.classCode = classCode;
        this.department = department;
    }

    // Getters
    public int getCourseId() {
        return courseId;
    }

    public String getClassName() {
        return className;
    }

    public int getClassCode() {
        return classCode;
    }

    public String getDepartment() {
        return department;
    }

    // Setters
    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassCode(int classCode) {
        this.classCode = classCode;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", className='" + className + '\'' +
                ", classCode=" + classCode +
                ", department='" + department + '\'' +
                '}';
    }
}