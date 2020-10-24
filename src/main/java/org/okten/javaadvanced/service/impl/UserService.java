package org.okten.javaadvanced.service.impl;

import org.okten.javaadvanced.dao.IUserDAO;
import org.okten.javaadvanced.entity.User;
import org.okten.javaadvanced.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService, IUserService {

    @Autowired
    private IUserDAO userDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public String createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (!user.getRole().startsWith("ROLE_")) {
            throw new RuntimeException("User role should start with 'ROLE_'");
        }
        User savedUser = userDAO.save(user);
        return savedUser.getUsername();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.findByUsername(username);

    }
}
