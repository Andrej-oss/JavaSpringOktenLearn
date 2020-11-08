package org.okten.javaadvanced.controller;

import lombok.extern.slf4j.Slf4j;
import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.dto.MoviePageDTO;
import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.service.IMovieService;
import org.okten.javaadvanced.validator.MovieValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {
    private IMovieService movieService;
    //private MovieValidator movieValidator;
//private List<Movie> movies = new ArrayList<>();


    /* {
            movieDAO.add(new Movie(1, "Star Wars 1", 134));
            movies.add(new Movie(2, "Star wars 2", 123));
        }*/
    @Autowired
    public MovieController(IMovieService movieService){
       this.movieService = movieService;
      // this.movieValidator = movieValidator;
   }
    @GetMapping
    public MoviePageDTO getMovies(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size){
        PageRequest pageRequest = PageRequest.of(page, size);
        final  MoviePageDTO all = movieService.getAllMovies(pageRequest);
        return all;
    }

    @GetMapping("/{id}")
    public Movie findMovie(@PathVariable int id){
        return movieService.getMovie(id);
    }

    @GetMapping("/name/{name}")
    public List<Movie> getMovies(@PathVariable String name) {
        return movieService.getMoviesByDirectorName(name);

    }

    @GetMapping(value = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImageByMoviesId(@PathVariable int id){
        return movieService.getMoviesImage(id);
    }

    @PostMapping(value = "/directors/{directorId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public MovieDTO postMovies(@ModelAttribute @Valid Movie movie,
                               @PathVariable int directorId, @RequestParam(required = false) MultipartFile file){
        log.info("handling Post /movie from with object" + movie);
        return movieService.insertMovie(movie, file,  directorId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Movie updateMovie(@PathVariable int id, @RequestBody Movie movie){
        //movie.setId(id);
       // movieDAO.save(movie);

       // Movie one = movieDAO.getOne(id);
      //  one.setId(id);
       // one.setDuration(movie.getDuration());
      //  one.setTittle(movie.getTittle());
      //  movieDAO.flush();
        return movieService.upDateMovie(movie);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
    }
   /* @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new MovieValidator());
        //webDataBinder.addValidators(movieValidator);
    }*/
}
