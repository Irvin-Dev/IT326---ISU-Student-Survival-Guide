package app;

/**
 * Represents a comment made on a course.
 * Matches the structure of the 'commentinfo' table in the database.
 */
public class Comment {

    private int commentId;            // Primary key
    private int courseId;             // FK → course(courseId)
    private int accountId;            // FK → account(accountId)
    private Integer parentCommentId;  // Nullable FK → comment(commentId)
    private String content;           // Comment text
   

    private int likes;                // Number of likes
    private java.sql.Timestamp createdAt; // Timestamp when created
    private java.sql.Timestamp updatedAt; // Timestamp when last updated

    /**
     * Full constructor used when reading from the database.
     */
    public Comment(int commentId, int courseId, int accountId,
                   Integer parentCommentId, String content, 
                   int likes, java.sql.Timestamp createdAt, java.sql.Timestamp updatedAt) {

        this.commentId = commentId;
        this.courseId = courseId;
        this.accountId = accountId;
        this.parentCommentId = parentCommentId;
        this.content = content;
      
        this.likes = likes;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Constructor for creating a new top-level comment.
     * Likes default to 0, timestamps handled in handler.
     */
    public Comment(int commentId, int accountId, String content) {
        this.commentId = commentId;
        this.accountId = accountId;
        this.content = content;

        
        this.parentCommentId = null;
        this.likes = 0;
        this.createdAt = new java.sql.Timestamp(System.currentTimeMillis());
        this.updatedAt = new java.sql.Timestamp(System.currentTimeMillis());
    }

    /**
     * Constructor for creating a reply.
     */
    public Comment(int courseId,
                   String content, int likes ) {
        this.courseId = courseId;
        this.content = content;
       
        this.likes = likes;
    }

    // Getters
    public int getCommentId() { return commentId; }
    public int getCourseId() { return courseId; }
    public int getAccountId() { return accountId; }
    public Integer getParentCommentId() { return parentCommentId; }
    public String getContent() { return content; }

    public int getLikes() { return likes; }
    public java.sql.Timestamp getCreatedAt() { return createdAt; }
    public java.sql.Timestamp getUpdatedAt() { return updatedAt; }

    // Setters
    public void setCommentId(int commentId) { this.commentId = commentId; }
    public void setCourseId(int courseId) { this.courseId = courseId; }
    public void setAccountId(int accountId) { this.accountId = accountId; }
    public void setParentCommentId(Integer parentCommentId) { this.parentCommentId = parentCommentId; }
    public void setContent(String content) { this.content = content; }

    public void setLikes(int likes) { this.likes = likes; }
    public void setCreatedAt(java.sql.Timestamp createdAt) { this.createdAt = createdAt; }
    public void setUpdatedAt(java.sql.Timestamp updatedAt) { this.updatedAt = updatedAt; }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", courseId=" + courseId +
                ", accountId=" + accountId +
                ", parentCommentId=" + parentCommentId +
              
                ", likes=" + likes +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", content='" + content + '\'' +
                '}';
    }
}
