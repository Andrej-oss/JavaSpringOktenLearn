package org.okten.javaadvanced.dao.utils;

import org.okten.javaadvanced.dao.MovieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DaoUtils {
    private static DaoUtils instance;

    @Autowired
    private MovieDAO movieDAO;

    /* Post constructor */

    @PostConstruct
    public void fillInstance() {
        instance = this;
    }

    /*static methods */

    public static MovieDAO getMovieDao() {
        return instance.movieDAO;
    }
}
