package org.okten.javaadvanced.controller;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.okten.javaadvanced.dto.AuthBody;
import org.okten.javaadvanced.dto.AuthenticationResponse;
import org.okten.javaadvanced.entity.User;
import org.okten.javaadvanced.service.IUserService;
import org.okten.javaadvanced.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")

public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping
    public void getAllUsers() {
        userService.getUsers();
    }

    @PostMapping
    public String saveUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/authenticate")
    public AuthenticationResponse generateJWT(@RequestBody AuthBody authBody) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authBody.getUsername(), authBody.getPassword()));
        return new AuthenticationResponse(jwtService.generateToken(authBody.getUsername()));
    }
}
