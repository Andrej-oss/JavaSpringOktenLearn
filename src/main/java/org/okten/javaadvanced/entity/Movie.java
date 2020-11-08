package org.okten.javaadvanced.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.okten.javaadvanced.validator.UniqMovieTittle;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.lang.annotation.Documented;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler", "fieldHandler"})
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)

    //@UniqMovieTittle
    @NotBlank
    private String tittle;

   // @JsonIgnore
    @Positive
    @Max(200)
    private int duration;

    @Lob
    private byte[] data;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Director director;
}
