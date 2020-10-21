package org.okten.javaadvanced.dao;

import org.okten.javaadvanced.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DirectorDAO extends JpaRepository<Director, Integer> {
    @Query("select d from Director d join fetch d.movies where d.name = :name")
    Director findDirectorByName(String name);
}
