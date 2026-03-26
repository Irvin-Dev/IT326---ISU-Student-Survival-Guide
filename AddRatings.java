public class AddRatings
{
    private int courseRating;
    private int difficultyRating;

    public AddRatings(int courseRating, int difficultyRating)
    {
        if(courseRating == null)
        {
            throw new IllegalArgumentException();
        }
        if(difficultyRating == null)
        {
            throw new IllegalArgumentException();
        }
        this.courseRating = courseRating;
        this.difficultyRating = difficultyRating;
    }

    public int getCourseRating()
    {
        return courseRating;
    }

    public int getDifficultyRating()
    {
        return difficultyRating;
    }

    public void setCourseRating(int courseRating)
    {
        if(courseRating == null)
        {
            throw new IllegalArgumentException();
        }
        this.courseRating = courseRating;
    }

    public void setDifficultyRating(int difficultyRating)
    {
        if(difficultyRating == null)
        {
            throw new IllegalArgumentException();
        }
        this.difficultyRating = difficultyRating;
    }

    public String toString()
    {
        return "||Course Ratings||\n" + "Course Rating: " + courseRating + "\nDifficulty Rating: " + difficultyRating;
    }

    public void addRating(int rating)
    {
        // implementation needed
    }

    public void addDifficulty(int diffRating)
    {
        // implementation needed
    }
}