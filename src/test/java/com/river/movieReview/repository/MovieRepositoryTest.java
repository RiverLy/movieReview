package com.river.movieReview.repository;

import com.river.movieReview.entity.Movie;
import com.river.movieReview.entity.MovieImage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
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

    @Test
    public void testListPage(){

        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "mno"));
        Page<Object[]> result = movieRepository.getListPage(pageRequest);

        for (Object[] objects : result.getContent()){
            System.out.println(Arrays.toString(objects));
        }

    }

    @Test
    public void testGetMovieWithAll() {

        List<Object[]> result = movieRepository.getMovieWithAll(95L);

        System.out.println(result);

        for (Object[] objects : result) {
            System.out.println(Arrays.toString(objects));
        }

    }

}
