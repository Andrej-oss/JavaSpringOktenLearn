package org.okten.javaadvanced.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@Data

public class MovieDTO {
    private int movieId;
    private String tittle;
    private int duration;
    private String directorName;

}
