package com.river.movieReview.repository;

import com.river.movieReview.entity.Movie;
import com.river.movieReview.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByMovie(Movie movie);

}
