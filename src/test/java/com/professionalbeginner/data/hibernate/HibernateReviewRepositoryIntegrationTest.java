package com.professionalbeginner.data.hibernate;

import com.professionalbeginner.TestUtils;
import com.professionalbeginner.domain.core.book.Book;
import com.professionalbeginner.domain.core.book.BookId;
import com.professionalbeginner.domain.core.review.Review;
import com.professionalbeginner.domain.core.review.ReviewId;
import com.professionalbeginner.domain.interfacelayer.repository.BookRepository;
import com.professionalbeginner.domain.interfacelayer.repository.ReviewRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotEquals;

/**
 * @author Kempenich Florian
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("integration-tests")
public class HibernateReviewRepositoryIntegrationTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    BookRepository bookRepository;

    @Autowired
    TestUtils testUtils;

    @Test
    public void saveReview_correctId() throws Exception {
        BookId bookId = generateValidBookId();
        Review review = testUtils.makeReview(ReviewId.NOT_ASSIGNED, bookId, 43, "patrick" );

        ReviewId savedId = reviewRepository.save(review);

        assertNotEquals(ReviewId.NOT_ASSIGNED, savedId);
    }

    private BookId generateValidBookId() {
        Book book = testUtils.makeDefaultBook(BookId.NOT_ASSIGNED);
        return bookRepository.save(book);
    }


}