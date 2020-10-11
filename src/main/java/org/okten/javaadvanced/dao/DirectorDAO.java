package org.okten.javaadvanced.dao;

import org.okten.javaadvanced.entity.Director;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectorDAO extends JpaRepository<Director, Integer> {
}
