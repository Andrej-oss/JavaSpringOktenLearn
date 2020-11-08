package org.okten.javaadvanced.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.okten.javaadvanced.config.StorageConfig;
import org.okten.javaadvanced.dao.DirectorDAO;
import org.okten.javaadvanced.dao.MovieDAO;
import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.dto.MoviePageDTO;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.exceptions.CapitalLetterException;
import org.okten.javaadvanced.exceptions.NoImageException;
import org.okten.javaadvanced.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@NoArgsConstructor
@Service
@AllArgsConstructor
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})


public class MovieService implements IMovieService {

    @Autowired
    private  MovieDAO movieDAO;

    @Autowired
    private DirectorDAO directorDAO;

    @Autowired
    private StorageConfig storageConfig;

    private Path rootFolder;

    @PostConstruct
    public void init(){
        try {
            rootFolder = Paths.get(storageConfig.getLocation()).toAbsolutePath().normalize();
            Files.createDirectory(rootFolder);
        } catch (IOException e) {
           log.error("Unable to create folder" + e.getMessage());
        }
    }

    @Override
    public MovieDTO insertMovie(Movie movie, MultipartFile file, int directorId) {
        if (!file.getContentType().contains("image")){
            throw new NoImageException("File must be Image");
        }
        if (movie.getTittle().charAt(0) < 65 || movie.getTittle().charAt(0) > 90) {
            throw  new CapitalLetterException("Tittle should start with capital Letter");
        }

        final Director director = directorDAO.getOne(directorId);
        movie.setDirector(director);
        try {
            movie.setData(file.getBytes());
        } catch (IOException e) {
            log.warn("File not found" + e.getMessage());
        }
        movie = movieDAO.save(movie);
     /*   try {
            String tittle = movie.getTittle();
            String extension = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
            Path pathFile = Paths.get(tittle + extension).normalize();
            Files.copy(file.getInputStream(), rootFolder.resolve(pathFile));
        } catch (IOException e) {
            log.error("Unable to create file in" + rootFolder + e.getMessage());
        }*/
        return new MovieDTO(movie.getId(), movie.getTittle(), movie.getDuration(), director.getName());
    }

    @Override
    public byte[] getMoviesImage(int id) {
        return movieDAO.findById(id).orElseThrow(() -> new RuntimeException("NO image")).getData();
    }

    @Override
    public List<Movie> getMoviesByDirectorName(String name) {
        return movieDAO.findByDirectorName(name);
    }

    @Override
    public MoviePageDTO getAllMovies(PageRequest pageRequest) {
        Page<Movie> all = movieDAO.findAll(pageRequest);
        return new MoviePageDTO(all.getContent(), all.getSize(), all.getNumber(), all.getTotalPages (), (int) all.getTotalElements(), all.isEmpty());
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
