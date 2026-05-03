package ISUStudentSurvival.service;

import ISUStudentSurvival.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class commentTest
{
    @Autowired
    private AppService appService;


    private static final int TEST_ACCOUNT_ID = 1;
    private static final int TEST_COURSE_ID  = 1;


    //Test 1, creates a comment on course ID 1, and with the Account ID of 1 in our database
    //No tagName, diffulty, rating, or parent comment should be attached, just testing post comment only
    @Test
    public void createsCommentSuccessfully()
    {
        String content = "This comment was made from a test case! " + System.currentTimeMillis();

        Comment result = appService.addComment(
                content,
                TEST_ACCOUNT_ID,
                TEST_COURSE_ID,
                null,
                null,
                null,
                null
        );

        assertNotNull(result, "This should return the comment");
        assertTrue(result.getCommentId() > 0, "this is the comment id");
        assertEquals(content, result.getComment(), "Don't change content from post");
        assertEquals(TEST_ACCOUNT_ID, result.getAccountId());
        assertEquals(TEST_COURSE_ID, result.getCourseId());
        assertNull(result.getParentCommentId(), "this should return null");
    }

    //Makes sure that a user cannot post a blank comment
    @Test
    public void rejectBlankContent()
    {
        Comment result = appService.addComment(
                "   ",
                TEST_ACCOUNT_ID,
                TEST_COURSE_ID,
                null, null, null, null
        );
        assertNull(result);
    }
}
