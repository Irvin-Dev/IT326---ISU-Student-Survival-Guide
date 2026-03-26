public class Comment
{
    private String comment;

    public Comment(String comment)
    {
        if(comment == null || comment.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.comment = comment;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        if(comment == null || comment.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.comment = comment;
    }

    public String toString()
    {
        return "Comment: " + comment;
    }

    public String addClassTag()
    {
        // implementation needed
    }
}