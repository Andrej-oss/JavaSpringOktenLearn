package org.okten.javaadvanced.validator;


import org.okten.javaadvanced.entity.Movie;
import org.okten.javaadvanced.exceptions.CapitalLetterException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MovieValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Movie.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
    Movie movie = (Movie) o;
        if (movie.getTittle().charAt(0) < 65 || movie.getTittle().charAt(0) > 90){
            errors.rejectValue("tittle", "movie.tittle.capital-error", "tittle capital letter error");
           // new RuntimeException("Tittle should start with capital Letter");
        }
    }
}
