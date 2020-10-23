package org.okten.javaadvanced.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.okten.javaadvanced.dao.IUserDAO;
import org.okten.javaadvanced.dao.MovieDAO;
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
public class UserService implements IUserService, UserDetailsService {
@Autowired
    private IUserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getUsers() {
        return userDAO.findAll();
    }

    @Override
    public String createUser(User user) {
        //Перед збереженням у базу, пароль користувача має бути зашифрований тим самим алгоритмом,
        // що і використовується для розшифровування при логінуванні (див. SecurityConfig)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Можна або так, або якийсь інший спосіб валідації. Головне щоб роль юзера в базі починалась з ROLE_
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
