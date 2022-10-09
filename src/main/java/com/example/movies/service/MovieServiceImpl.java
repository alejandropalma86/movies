package com.example.movies.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.movies.commons.GenericServiceImpl;
import com.example.movies.model.Movie;
import com.example.movies.repository.MovieRepository;

@Service
public class MovieServiceImpl extends GenericServiceImpl<Movie, Long> implements MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public JpaRepository<Movie, Long> getDaoRepo() {
        
        return movieRepository;
    }

    public List<Movie> findByKeyword(String keyword) {
        return movieRepository.findByKeyword(keyword);
    }
    
}
