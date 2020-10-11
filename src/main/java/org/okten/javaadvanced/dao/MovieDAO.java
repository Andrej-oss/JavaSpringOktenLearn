package org.okten.javaadvanced.dao;

import org.okten.javaadvanced.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface MovieDAO extends JpaRepository<Movie, Integer> {

    @Query("SELECT m FROM Movie m  WHERE m.tittle = :tittle")
    Movie findByTittle(String tittle);
}
