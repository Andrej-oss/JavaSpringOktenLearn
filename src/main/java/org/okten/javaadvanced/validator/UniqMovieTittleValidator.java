package org.okten.javaadvanced.validator;

import lombok.NoArgsConstructor;
import org.okten.javaadvanced.dao.MovieDAO;
import org.okten.javaadvanced.dao.utils.DaoUtils;
import org.okten.javaadvanced.entity.Movie;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@NoArgsConstructor

public class UniqMovieTittleValidator implements ConstraintValidator<UniqMovieTittle, String> {
    private MovieDAO movieDAO;

    @Override
    public void initialize(UniqMovieTittle constraintAnnotation) {
        movieDAO = DaoUtils.getMovieDao();
    }

    @Override
    public boolean isValid(String tittle, ConstraintValidatorContext constraintValidatorContext) {
        //Todo Деня посмотри тут код и в MovieDAO
        if (tittle  != null && tittle.length() > 0) {
            Movie movie = movieDAO.findByTittle(tittle);
            System.out.println(movie);
         if (movie == null) {
             return true;
         }
        }
        return false;
        //return true;
    }
}
