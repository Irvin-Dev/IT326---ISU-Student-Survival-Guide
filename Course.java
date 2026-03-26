public class Course
{
    private String courseName;
    private int courseId;
    private int courseCode;
    private String department;

    public Course(String courseName, int courseId, int courseCode, String department)
    {
        if(courseName == null || courseName.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        if(courseId == null)
        {
            throw new IllegalArgumentException();
        }
        if(courseCode == null)
        {
            throw new IllegalArgumentException();
        }
        if(department == null || department.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.department = department;
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

    public void setCourseId(int courseId)
    {
        if(courseId == null)
        {
            throw new IllegalArgumentException();
        }
        this.courseId = courseID;
    }

    public void setCourseCode(int courseCode)
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