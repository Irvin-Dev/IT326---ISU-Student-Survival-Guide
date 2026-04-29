package app;

public class CourseTag
{
    private String keyword;
    private int courseId;


    private int commentId;
    private int tagId;

    public CourseTag(int tagId, int courseId,  int commentId, String keyword)
    {
        this.tagId = tagId;
        this.courseId = courseId;
   
        this.commentId = commentId;
        this.keyword = keyword;
    }

    public int getCourseId()
    {
        return courseId;
    }
    public int getTagId()
    {
        return courseId;
    }



    public String getKeyword()
    {
        return keyword;
    }

    public int getCommentId()
    {
        return commentId;
    }

    public void addCourseTag(String keyword)
    {
        if(keyword == null)
        {
            throw new IllegalArgumentException("Tag keyword can noot be null.");
        }
        this.keyword = keyword;
    }

    public String toString()
    {
        return String.format("ClassTag{keyword='%s'}", keyword);
    }
    
}
