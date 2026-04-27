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
    private List<ClassTag> courseTags;
    private int courseId;
    private  int accountId;
    private Integer parentCommentId;
    private int likes;
    private Timestamp updatedAt;
    private Timestamp createdAt;

    public Comment(int commentId, String commentContent, int accountId, int courseId,Integer parentId, int likes, Timestamp createdAt, Timestamp updatedAt){

        this.commentId = commentId;
        this.commentContent = commentContent;
        this.courseTags = new ArrayList<>();
        this.courseId=courseId;
        this.accountId=accountId;
        parentCommentId=parentId;
        this.likes =likes;
        this.updatedAt=updatedAt;
        this.createdAt=createdAt;

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
    public int getLikes()
    {
        return likes;
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


    public List<ClassTag> getCourseTags()
    {
        return Collections.unmodifiableList(courseTags);
    }

    public String toString()
    {
        return String.format("Comment{commentContent='%s', tags=%d}", commentContent, courseTags.size());
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

    public void addCourseTag(ClassTag courseTag)
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