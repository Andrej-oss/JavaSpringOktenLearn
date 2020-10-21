package org.okten.javaadvanced.service;

import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.dto.MoviePageDTO;
import org.okten.javaadvanced.entity.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface IMovieService {
    MovieDTO insertMovie(Movie movie, int directorId);
    MoviePageDTO getAllMovies(PageRequest pageRequest);
    Movie getMovie(int id);
    Movie upDateMovie(Movie movie);
    void deleteMovie(int id);
    List<Movie> getMoviesByDirectorName(String name);
}
