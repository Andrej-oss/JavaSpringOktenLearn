package org.okten.javaadvanced.service;

import org.okten.javaadvanced.entity.Director;

import java.util.List;

public interface IDirectorService {
    Director insertDirector(Director director);
    List<Director> getDirectors();
    Director getDirector(int id);
    Director updateDirector(Director director);
    void deleteDirector(int id);
    Director getDirectorByName(String name);
}
