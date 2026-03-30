public class ClassTag
{
    private String keyword;

    public ClassTag(String keyword)
    {
        if(keyword == null || keyword.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.keyword = keyword;
    }

    public String getKeyword()
    {
        return keyword;
    }

    public void setKeyword(String keyword)
    {
        if(keyword == null || keyword.isEmpty())
        {
            throw new IllegalArgumentException();
        }
        this.keyword = keyword;
    }

    public String toString()
    {
        return "Class Tag(s): " + keyword;
    }
}