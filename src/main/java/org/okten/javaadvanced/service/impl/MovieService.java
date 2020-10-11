package org.okten.javaadvanced.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.okten.javaadvanced.dao.DirectorDAO;
import org.okten.javaadvanced.dao.MovieDAO;
import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.exceptions.CapitalLetterException;
import org.okten.javaadvanced.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@NoArgsConstructor
@Service
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})

public class MovieService implements IMovieService {

    @Autowired
    private  MovieDAO movieDAO;

    @Autowired
    private DirectorDAO directorDAO;

    @Override
    public MovieDTO insertMovie(Movie movie, int directorId) {
        if (movie.getTittle().charAt(0) < 65 || movie.getTittle().charAt(0) > 90) {
            throw  new CapitalLetterException("Tittle should start with capital Letter");
        }
        final Director director = directorDAO.getOne(directorId);
        movie.setDirector(director);
        movieDAO.save(movie);
        return new MovieDTO(movie.getId(), movie.getTittle(), movie.getDuration(), director.getName());
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieDAO.findAll();
    }

    @Override
    public Movie getMovie(int id) {
        return movieDAO.findById(id).orElseThrow(() -> new RuntimeException("No such movies"));
    }

    @Override
    public Movie upDateMovie(Movie movie) {
        return movieDAO.save(movie);
    }

    @Override
    public void deleteMovie(int id) {
movieDAO.deleteById(id);
    }
}
