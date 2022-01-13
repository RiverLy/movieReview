package com.river.movieReview.repository;

import com.river.movieReview.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("select m, i, avg(coalesce(r.grade,0)), count(r) from Movie m "
            + "left outer join MovieImage i on i.movie = m "
            + "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);

    @Query("select m, i, avg(coalesce(r.grade,0)), count(r) from Movie m "
            + "left outer join MovieImage i on i.movie = m "
            + "left outer join Review r on r.movie = m "
            + "where m.mno = :mno group by i")
    List<Object[]> getMovieWithAll(Long mno);

}
