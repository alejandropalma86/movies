package com.example.movies.service;

import java.util.List;

import com.example.movies.commons.GenericService;
import com.example.movies.model.Movie;

public interface MovieService extends GenericService <Movie, Long> {

    List<Movie> findByKeyword(String keyword);
    
}
