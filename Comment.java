import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Comment
{
    private String commentContent;
    private int max_tags = 3;
    private List<ClassTag> courseTags;
    private Course course;

    public Comment(Course course)
    {
        if(course == null)
        {
            throw new IllegalArgumentException("Course can not be null.");
        }
        this.course = course;
        this.courseTags = new ArrayList<>();
    }

    public String getCommentContent()
    {
        return comment;
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
        course.updateComments(this);
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
        course.updateTagList(courseTag);
    }
}