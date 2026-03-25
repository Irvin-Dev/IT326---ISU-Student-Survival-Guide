/**
 * Represents a comment made on a course.
 * Matches the structure of the 'comment' table in the database.
 */
public class Comment {

    private int commentId;            // Primary key
    private int courseId;             // FK → course(courseId)
    private int accountId;            // FK → account(accountId)
    private Integer parentCommentId;  // Nullable FK → comment(commentId) for replies
    private String content;           // Comment text
    private boolean anonymous;        // 1 = anonymous, 0 = not anonymous

    /**
     * Full constructor used when reading from the database.
     */
    public Comment(int commentId, int courseId, int accountId,
                   Integer parentCommentId, String content, boolean anonymous) {
        this.commentId = commentId;
        this.courseId = courseId;
        this.accountId = accountId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.anonymous = anonymous;
    }

    /**
     * Constructor used when creating a new top-level comment.
     */
    public Comment(int courseId, int accountId, String content, boolean anonymous) {
        this.courseId = courseId;
        this.accountId = accountId;
        this.content = content;
        this.anonymous = anonymous;
        this.parentCommentId = null; // top-level comment
    }

    /**
     * Constructor used when creating a reply to another comment.
     */
    public Comment(int courseId, int accountId, Integer parentCommentId,
                   String content, boolean anonymous) {
        this.courseId = courseId;
        this.accountId = accountId;
        this.parentCommentId = parentCommentId;
        this.content = content;
        this.anonymous = anonymous;
    }

    // Getters
    public int getCommentId() {
        return commentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public int getAccountId() {
        return accountId;
    }

    public Integer getParentCommentId() {
        return parentCommentId;
    }

    public String getContent() {
        return content;
    }

    public boolean isAnonymous() {
        return anonymous;
    }

    // Setters
    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setParentCommentId(Integer parentCommentId) {
        this.parentCommentId = parentCommentId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", courseId=" + courseId +
                ", accountId=" + accountId +
                ", parentCommentId=" + parentCommentId +
                ", anonymous=" + anonymous +
                ", content='" + content + '\'' +
                '}';
    }
}