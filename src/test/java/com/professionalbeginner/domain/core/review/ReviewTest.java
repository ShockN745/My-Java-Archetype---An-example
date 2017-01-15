package com.professionalbeginner.domain.core.review;

import com.google.common.testing.EqualsTester;
import com.professionalbeginner.domain.core.book.BookId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author Kempenich Florian
 */
public class ReviewTest {

    private ReviewId validId;
    private BookId validBookId;
    private User validUser;
    private Rating validRating;

    /*
     * Note to self:
     * Later when (if) implementing a review content: Possible to add without adding to constructor.
     * Just initialize with default value (NullObject pattern).
     *
     * Makes sense: a review doesn't necessarily have a text content.
     */

    @Before
    public void setUp() throws Exception {
        validId = new ReviewId(234L);
        validBookId = new BookId(123L);
        validUser = new User("Patrick");
        validRating = new Rating(3);
    }

    @Test
    public void testCreateInstance() throws Exception {
        assertValid(validId, validBookId, validRating, validUser);

        assertInvalid(null, validBookId, validRating, validUser);
        assertInvalid(validId, null, validRating, validUser);
        assertInvalid(validId, validBookId, null, validUser);
        assertInvalid(validId, validBookId, validRating, null);
    }

    private void assertValid(ReviewId reviewId, BookId bookId, Rating rating, User user) {
        new Review(reviewId, bookId, user, rating);
    }

    private void assertInvalid(ReviewId reviewId, BookId bookId, Rating rating, User user) {
        try {
            new Review(reviewId, bookId, user, rating);
            fail("Should throw exception");
        } catch (NullPointerException e) {
            // expected
        }
    }

    @Test(expected = NullPointerException.class)
    public void setRatingNull() throws Exception {
        Review review = new Review(validId, validBookId, validUser, validRating);
        review.updateRating(null);
    }

    @Test(expected = NullPointerException.class)
    public void setIdNull() throws Exception {
        Review review = new Review(validId, validBookId, validUser, validRating);
        review.setId(null);
    }

    @Test
    public void testEquality() throws Exception {
        // two reviews are equals when their ids are equal

        Rating validRating2 = new Rating(45);
        User validUser2 = new User("Florian");

        ReviewId id1 = new ReviewId(3L);
        ReviewId id2 = new ReviewId(51L);
        ReviewId id3 = new ReviewId(98L);

        new EqualsTester()
                .addEqualityGroup(new Review(id1, new BookId(567L), validUser, validRating), new Review(id1, new BookId(567L), validUser, validRating))
                .addEqualityGroup(new Review(id2, new BookId(567L), validUser, validRating), new Review(id2, new BookId(567L), validUser, validRating))
                .addEqualityGroup(new Review(id3, new BookId(567L), validUser2, validRating2), new Review(id3, new BookId(567L), validUser, validRating))
                .testEquals();
    }

}