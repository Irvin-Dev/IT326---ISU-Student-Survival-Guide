import java.util.Random;

public class Course
{
    private static final Random random = new Random();
    private String courseName;
    private int courseId = generateCourseID();
    private int courseCode;
    private String department;

    public Course(String courseName, Integer courseCode, String department)
    {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.department = department;
    }

    private int generateCourseID() {
        int min = 100000000;
        int max = 900000000;
        return random.nextInt((max - min) + 1) + min;
    }

    public String getCourseName()
    {
        return courseName;
    }

    public int getCourseId()
    {
        return courseId;
    }

    public int getCourseCode()
    {
        return courseCode;
    }

    public String getDepartment()
    {
        return department;
    }

    public void setCourseName(String courseName)
    {
        if(courseName == null || courseName.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.courseName = courseName;
    }

    public void setCourseId(Integer courseId)
    {
        if(courseId == null)
        {
            throw new IllegalArgumentException();
        }
        this.courseId = courseId;
    }

    public void setCourseCode(Integer courseCode)
    {
        if(courseCode == null)
        {
            throw new IllegalArgumentException();
        }
        this.courseCode = courseCode;
    }

    public void setDepartment(String department)
    {
        if(department == null || department.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.department = department;
    }

    public String toString()
    {
        return "||Course||\n" + "Course Name: " + courseName + "\nCourse ID: " + courseId + "\nCourse Code: " + courseCode + "\nDepartment: " + department;
    }

    public void updateTagList()
    {
        // implementation needed
    }

    public void updateRatings()
    {
        // implementation needed
    }

    public void generateCourseDistribution()
    {
        // implementation needed
    }
}