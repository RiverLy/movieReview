package com.river.movieReview.repository;

import com.river.movieReview.entity.Movie;
import com.river.movieReview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
public class MovieRepositoryTest {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private MovieImageRepository imageRepository;

    @Commit
    @Transactional
    @Test
    public void insertMovies(){

        IntStream.rangeClosed(1,100).forEach(i -> {

            Movie movie = Movie.builder()
                    .title("Movie..." + i)
                    .build();
            System.out.println("---------------------------------------------------------------");

            movieRepository.save(movie);
            int cnt = (int) (Math.random() * 5) + 1;

            for (int j = 0; j < cnt; j++){
                MovieImage image = MovieImage.builder()
                        .uuid(UUID.randomUUID().toString())
                        .movie(movie)
                        .imgName("test" + j + ".jpg")
                        .build();

                imageRepository.save(image);
            }

            System.out.println("---------------------------------------------------------------");

        });

    }

}
