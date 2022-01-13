package com.river.movieReview.repository;

import com.river.movieReview.entity.Member;
import com.river.movieReview.entity.Movie;
import com.river.movieReview.entity.Review;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.IntStream;

@SpringBootTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void insertReviews(){

        IntStream.rangeClosed(1,200).forEach(i -> {

            Long mno = (long) (Math.random() * 100) + 1;
            Long mid = (long) (Math.random() * 100) + 1;

            Member member = Member.builder()
                    .mid(mid)
                    .build();
            Movie movie = Movie.builder()
                    .mno(mno)
                    .build();

            Review review = Review.builder()
                    .member(member)
                    .movie(movie)
                    .grade((int)(Math.random() * 5) + 1)
                    .text("review about this movie ..." + i)
                    .build();

            reviewRepository.save(review);

        });

    }

    @Test
    public void testGetMovieReviews(){

        Movie movie = Movie.builder().mno(92L).build();
        List<Review> result = reviewRepository.findByMovie(movie);

        result.forEach(review -> {
            System.out.println(review.getReviewNum());
            System.out.println(review.getGrade());
            System.out.println(review.getText());
            System.out.println(review.getMember().getEmail());
        });

    }

}
