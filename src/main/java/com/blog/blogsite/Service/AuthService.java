package com.blog.blogsite.Service;

import com.blog.blogsite.DTO.RegisterRequest;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void signup(RegisterRequest registerRequest) {
        User user = new User();

        Optional<Object> userOptional = _userRepository.findByUsername(user.getUsername());

        if (userOptional.isPresent()) {
            throw new IllegalStateException("Username taken!!");
        } else {
            // setting the required contents
            user.setUsername(registerRequest.getUsername());
            user.setEmail(registerRequest.getEmail());
            user.setPassword(encodePassword(registerRequest.getPassword()));

            // save it to database
            _userRepository.save(user);
        }
    }

    private String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
