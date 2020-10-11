package org.okten.javaadvanced.service;

import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.entity.Movie;

import java.util.List;

public interface IMovieService {
    MovieDTO insertMovie(Movie movie, int directorId);
    List<Movie> getAllMovies();
    Movie getMovie(int id);
    Movie upDateMovie(Movie movie);
    void deleteMovie(int id);
}
