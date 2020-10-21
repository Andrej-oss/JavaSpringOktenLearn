package org.okten.javaadvanced.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.okten.javaadvanced.dto.MovieDTO;
import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.service.impl.MovieService;
import org.okten.javaadvanced.validator.UniqMovieTittle;
import org.okten.javaadvanced.validator.UniqMovieTittleValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.awt.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MovieController.class)

public class MovieControllerTest {

    @MockBean
    private MovieService movieService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenMovieIdWhenTakingMovieReturnSuccessResponse() throws Exception {
        BDDMockito.given(movieService.getMovie(ArgumentMatchers.anyInt()))
                .willReturn(new Movie(1, "Tittle", 182, null));
        mockMvc.perform(MockMvcRequestBuilders.get("/movies/1"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }
    @Test
    public void givenMovieBodyWhenInsertingThenReturnSuccessResponse() throws Exception {
         //Movie movie = new Movie(1, "Tittle", 45, null);
        BDDMockito.given(movieService.insertMovie(ArgumentMatchers.any(Movie.class), ArgumentMatchers.anyInt()))
                .willReturn(new MovieDTO(1, "Tittle", 45, "Kubrick"));

        mockMvc.perform(MockMvcRequestBuilders.post("/movies/directors/1")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\n" +
                "    \"id\": 1,\n" +
                "    \"tittle\": \"Space Odissey 3\",\n" +
                "    \"duration\": 199\n" +
                "    }"))
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.movieId").value(1))
        .andExpect(MockMvcResultMatchers.jsonPath("$.tittle").value("Tittle"));
    }
}
