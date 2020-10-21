package org.okten.javaadvanced.service;

import org.okten.javaadvanced.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();
    String createUser(User user);
    
}
