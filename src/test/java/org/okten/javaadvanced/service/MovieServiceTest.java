package org.okten.javaadvanced.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.okten.javaadvanced.dao.DirectorDAO;
import org.okten.javaadvanced.dao.MovieDAO;
import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.dto.MoviePageDTO;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.exceptions.CapitalLetterException;
import org.okten.javaadvanced.service.impl.MovieService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieDAO movieDAO;
    @Mock
    private DirectorDAO directorDAO;

    @InjectMocks
    private MovieService movieService;

   /* @Test
    public void givenMovieTittleWithOutCapitalLetterWhenMovieInsertingThenReturnThrowException(){
        Movie movie = new Movie(1, "tittle", 123, null);
        Assertions.assertThrows(CapitalLetterException.class, () ->  movieService.insertMovie(movie, file, 1));
    }
    @Test
    public void givenValidMovieRequestBodyWhenInsertingMovieThenReturnMovieDTO(){
        Director director = new Director(1, "Kubrick", LocalDate.of(1977, 03, 22), Collections.emptyList());
        Movie movie = new Movie(1, "Tittle", 123, director);
        Mockito.when(directorDAO.getOne(1))
                .thenReturn(director);
        Mockito.when(movieDAO.save(movie)).thenReturn(movie);
        final MovieDTO actualMovieDTO = movieService.insertMovie(movie, file, 1);
        MovieDTO movieDTO = new MovieDTO(1, "Tittle", 123, "Kubrick");
        Assertions.assertEquals(movieDTO.getDirectorName(), actualMovieDTO.getDirectorName());
    }*/
    @Test
    public void givenPageRequestWhenTakingMoviesThenReturnMoviePageDTO(){
         PageRequest pageRequest = PageRequest.of(1, 3);
        Page<Movie> allMovies = movieDAO.findAll(pageRequest);
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie(1, "Tittle", 143, null, null));
        MoviePageDTO allMovies1 = movieService.getAllMovies(pageRequest);
        final MoviePageDTO moviePageDTO = new MoviePageDTO(movies, 3, 1, 4, 9, false);
        Mockito.when(movieDAO.findAll(pageRequest)).thenReturn((Page<Movie>) moviePageDTO);
        Assertions.assertEquals(allMovies.getSize(), allMovies1.getSize());
    }
}
