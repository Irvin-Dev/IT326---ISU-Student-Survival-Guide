public class Rating
{
    private int courseRating;
    private int difficultyRating;
    private int min_rating = 1;
    private int max_rating = 5;
    private Course course;

    public Rating(Course course)
    {
        if(course == null)
        {
            throw new IllegalArgumentException("Course can not be null.");
        }
        this.course = course;
        this.courseRating = 0;
        this.difficultyRating = 0;
    }

    public int getCourseRating()
    {
        return courseRating;
    }

    public int getDifficultyRating()
    {
        return difficultyRating;
    }

    public String toString()
    {
        return String.format("Rating{courseRating=%d, difficultyRating=%d}", courseRating, difficultyRating);
    }

    public void addCourseRating(int courseRating)
    {
        if(courseRating < min_rating || courseRating > max_rating)
        {
            throw new IllegalArgumentException("Course rating must be between 1 and 5.");
        }
        this.courseRating = courseRating
    }

    public void addDifficultyRating(int difficultyRating)
    {
        if(difficultyRating < min_rating || difficultyRating > max_rating)
        {
            throw new IllegalArgumentException("Course's difficulty rating must be between 1 and 5.");
        }
        this.difficultyRating = difficultyRating;
    }

    public void addRating()
    {
        if (!(courseRating != 0 && difficultyRating != 0))
        {
            throw new IllegalStateException("Both ratings must be fulfilled.");
        }
        course.updateRating(this);
    }
}
