package org.okten.javaadvanced.dao;

import org.okten.javaadvanced.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface IUserDAO extends JpaRepository<User, Integer> {

    User findByUsername(String username);
}
