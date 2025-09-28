package com.example.blogpost.controller;

import com.example.blogpost.model.User;
import com.example.blogpost.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/api/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        logger.info("Request to register user: {}", user.getUsername());
        if (user.getId() == null) {
            logger.warn("ID missing for registration");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ID required");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty() ||
            user.getPassword() == null || user.getPassword().isEmpty()) {
            logger.warn("Username or password missing");
            return ResponseEntity.badRequest().body("Username and password are required");
        }
        if (userRepository.findByUsername(user.getUsername()) != null) {
            logger.warn("Username already exists: {}", user.getUsername());
            return ResponseEntity.badRequest().body("Username already exists");
        }
        userRepository.save(user);
        logger.info("User registered: {}", user.getUsername());
        return ResponseEntity.status(HttpStatus.CREATED).body("Registered successfully");
    }
}