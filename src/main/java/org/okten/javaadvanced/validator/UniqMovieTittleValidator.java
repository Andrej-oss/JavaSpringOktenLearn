package org.okten.javaadvanced.validator;

import lombok.NoArgsConstructor;
import org.okten.javaadvanced.dao.MovieDAO;
import org.okten.javaadvanced.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

@NoArgsConstructor

public class UniqMovieTittleValidator implements ConstraintValidator<UniqMovieTittle, String> {
    private MovieDAO movieDAO;
    @Autowired
    public UniqMovieTittleValidator(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }


    @Override
    public void initialize(UniqMovieTittle constraintAnnotation) {

    }
    @Override
    public boolean isValid(String tittle, ConstraintValidatorContext constraintValidatorContext) {
        /*if (tittle  != null && tittle.length() > 0) {
            Movie movie = movieDAO.findByTittle(tittle);
            System.out.println(movie);
         if (movie == null) {
             return true;
         }
        }*/
       // return false;
        return true;
    }
}
