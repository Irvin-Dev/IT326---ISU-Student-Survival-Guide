package app;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.sql.*;

public class Comment
{
    private String commentContent;
    private int commentId;
    private int min_tags = 0;
    private int max_tags = 3;
    private List<CourseTag  > courseTags;
    private int courseId;
    private  int accountId;
    private Integer parentCommentId;
    private double courseRating;
    private double difficultyRating;
    private Timestamp updatedAt;
    private Timestamp createdAt;

    public Comment(int commentId, String commentContent, int accountId, int courseId,Integer parentId, Timestamp createdAt, Timestamp updatedAt, double courseRating, double difficultyRating){

        this.commentId = commentId;
        this.commentContent = commentContent;
        this.courseTags = new ArrayList<>();
        this.courseId=courseId;
        this.accountId=accountId;
        parentCommentId=parentId;
        this.updatedAt=updatedAt;
        this.createdAt=createdAt;
        this.courseRating = courseRating;
        this.difficultyRating = difficultyRating;

        this.courseTags = new ArrayList<>();
    }
        public Comment( String commentContent, int accountId, int courseId,Integer parentId,  Timestamp createdAt, Timestamp updatedAt, double courseRating, double difficultyRating){

        this.commentContent = commentContent;
        this.courseTags = new ArrayList<>();
        this.courseId=courseId;
        this.accountId=accountId;
        parentCommentId=parentId;
        this.updatedAt=updatedAt;
        this.createdAt=createdAt;
        this.courseRating = courseRating;
        this.difficultyRating = difficultyRating;

        this.courseTags = new ArrayList<>();
    }
    public Comment(int commentId, String commentContent, int accountId, int courseId,Integer parentId,  Timestamp createdAt, Timestamp updatedAt){

        this.commentId = commentId;
        this.commentContent = commentContent;
        this.courseTags = new ArrayList<>();
        this.courseId=courseId;
        this.accountId=accountId;
        parentCommentId=parentId;
        this.updatedAt=updatedAt;
        this.createdAt=createdAt;
        this.courseRating = -1;
        this.difficultyRating = -1;

        this.courseTags = new ArrayList<>();
    }
    public String getCommentContent()
    {
        return commentContent;
    }
    public int getCommentId()
    {
        return commentId;
    }

    public int getCourseId()
    {
        return courseId;
    }
    public int getAccountId()
    {
        return accountId;
    }
    
    public Timestamp getUpdatedAt()
    {
        return updatedAt;
    }
    public Integer getParentCommentId()
    {
        return parentCommentId;
    }
    public void updateContent(String newContent)
    {
        if(newContent == null)
        {
            throw new IllegalArgumentException("Comment content can not be null.");
        }
        this.commentContent = newContent;
    }


    public List<CourseTag> getCourseTags()
    {
        return Collections.unmodifiableList(courseTags);
    }
    public double getCourseRating()
    {
        return courseRating;
    }
    public double getDifficultyRating()
    {
        return difficultyRating;
    }
    public void setCourseRating(double courseRating)
    {
        this.courseRating = courseRating;
    }
    public void setDifficultyRating(double difficultyRating)
    {
        this.difficultyRating = difficultyRating;
    }

    public String toString()
    {
        String result = String.format("Comment{commentContent='%s', tags=%s, id:%d, courseRating:%.2f, difficultyRating:%.2f}", commentContent, courseTags, commentId, courseRating, difficultyRating   );

        return result;
    }

    public void addComment(String commentContent)
    {
        if(commentContent == null)
        {
            throw new IllegalArgumentException("Comment content can not be null.");
        }
        this.commentContent = commentContent;
        //course.updateComments(this);
        //do we need this
    }

    public void addCourseTag(CourseTag courseTag)
    {
        if(courseTag == null)
        {
            throw new IllegalArgumentException("Course tag can not be null.");
        }
        if(courseTags.size() >= max_tags)
        {
            throw new IllegalStateException("A comment can only have up to 3 tags.");
        }
        
        courseTags.add(courseTag);
        //course.updateTagList(courseTag);
        //make courseTag handle updating
    }
}