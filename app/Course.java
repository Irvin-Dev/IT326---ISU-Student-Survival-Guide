package app;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Course
{
    private String courseName;
    private int courseId;
    private int courseCode;
    private String department;
    private double courseRating;
    private double difficultyRating;

    private List<CourseTag> courseTagList;
    private List<Comment> commentList;

    public Course(String courseName, int courseId, int courseCode, String department, double courseRating, double difficultyRating)
    {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.department = department;
        this.courseRating = courseRating;
        this.difficultyRating = difficultyRating;
        this.courseTagList = new ArrayList<>();
        this.commentList = new ArrayList<>();
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

    public List<CourseTag> getCourseTagList()
    {
        // returning an unmodifiable list using Collections so that only the add methods in appropriate classes can modify the list
        return Collections.unmodifiableList(courseTagList);
    }

    public double getRatingList()
    {
        return courseRating;
    }

    public List<Comment> getCommentList()
    {
        return Collections.unmodifiableList(commentList);
    }
    public double getDifficultyRating()
    {
        return difficultyRating;
    }

    public void setCourseName(String courseName)
    {
        this.courseName = courseName;
    }

    public void setCourseId(int courseId)
    {
        this.courseId = courseId;
    }

    public void setDifficultyRating(double difficultyRating)
    {
        this.difficultyRating = difficultyRating;
    }

    public void setCourseCode(int courseCode)
    {
        this.courseCode = courseCode;
    }

    public void setDepartment(String department)
    {
        this.department = department;
    }

    public String toString()
    {
        return String.format("Course{courseName='%s', courseId=%d, courseCode=%d, department='%s', " + "tags=%d, ratings=%.2f, comments=%d, difficultyRating=%.2f}", courseName, courseId, courseCode, department, courseTagList.size(), courseRating, commentList.size(), difficultyRating);
    }

    public void updateTagList(CourseTag courseTag)
    {
        if(courseTag == null)
        {
            throw new IllegalArgumentException("Course Tag can not be null.");
        }
        courseTagList.add(courseTag);
    }


    public void updateComments(Comment comment)
    {
        if(comment == null)
        {
            throw new IllegalArgumentException("Comment can not be null.");
        }
        commentList.add(comment);
    }
    /*  do we need this?
    public int[] generateCourseDistribution()
    {
        // index 0 represents rating of 1, index 4 represents rating of 5
        int[] distribution = new int[5];

        if(ratingList.isEmpty())
        {
            System.out.println("No rating available.");
            return distribution;
        }

        for(Rating rating : ratingList)
        {
            distribution[rating.getCourseRating() - 1]++;
        }

        return distribution;
    }
    */
}