package com.blog.blogsite.Service;

import com.blog.blogsite.DTO.RegisterRequest;
import com.blog.blogsite.Exception.UsernameTakenException;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private final UserRepository _userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository) {
        _userRepository = userRepository;
    }

    public ResponseEntity signup(RegisterRequest registerRequest) {
        User user = new User();

        // username must not be duplicate
        Optional<Object> userOptional = _userRepository.findByUsername(registerRequest.getUsername());

        if (userOptional.isPresent()) {
            throw new UsernameTakenException("Username taken!!");
        } else {
            // setting the required contents
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(encodePassword(registerRequest.getPassword()));

            // save it to database
            _userRepository.save(user);
            return new ResponseEntity(HttpStatus.OK);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
