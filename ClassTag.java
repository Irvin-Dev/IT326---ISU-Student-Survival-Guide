public class ClassTag
{
    private String keyword;
    private Course course;

    public ClassTag(Course course)
    {
        if(course == null)
        {
            throw new IllegalArgumentException("Course can not be null.");
        }
        this.course = course;
        this.keyword = "";
    }

    public String getKeyword()
    {
        return keyword;
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