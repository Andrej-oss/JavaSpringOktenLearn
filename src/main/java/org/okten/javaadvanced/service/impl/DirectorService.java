package org.okten.javaadvanced.service.impl;

import org.okten.javaadvanced.dao.DirectorDAO;
import org.okten.javaadvanced.entity.Director;
import org.okten.javaadvanced.service.IDirectorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class DirectorService implements IDirectorService {

    private DirectorDAO directorDAO;

    public DirectorService(DirectorDAO directorDAO) {
        this.directorDAO = directorDAO;
    }

    @Override
    public Director insertDirector(Director director) {
        return directorDAO.save(director);
    }

    @Override
    public List<Director> getDirectors() {
        return directorDAO.findAll();
    }

    @Override
    public Director getDirector(int id) {
        return directorDAO.getOne(id);
    }

    @Override
    public Director updateDirector(Director director) {
        return directorDAO.save(director);
    }

    @Override
    public void deleteDirector(int id) {
       directorDAO.deleteById(id);
    }
}
