package com.example.movies.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.movies.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query(value = "SELECT m FROM Movie m WHERE m.name LIKE %?1%"
            + " OR m.director LIKE %?1%"
            + " OR m.premieredate LIKE %?1%")
    public List<Movie> findByKeyword(String keyword);

    
}
