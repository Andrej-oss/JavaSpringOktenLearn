package org.okten.javaadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.okten.javaadvanced.entity.Movie;

import java.util.List;

@AllArgsConstructor
@Data
public class MoviePageDTO {

    private List<Movie> movies;
    private int size;
    private int currentPage;
    private int totalPages;
    private int totalElements;
    private boolean isEmpty;
}
