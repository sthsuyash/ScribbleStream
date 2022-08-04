package com.blog.blogsite.Service;

import com.blog.blogsite.Exception.ResourceNotFoundException;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private final UserRepository _userRepository;

    public UserService(UserRepository _userRepository) {
        this._userRepository = _userRepository;
    }

    // returns all the user
    public List<User> getAllUsers() {
        List<User> users = _userRepository.findAll();

        List<User> usersToAdd = new ArrayList<>();

        for (User usersInDB : users) {
            User user = new User();
            user.setUsername(usersInDB.getUsername());
            user.setEmail(usersInDB.getEmail());
            user.setId(usersInDB.getId());
            usersToAdd.add(user);
        }
        return usersToAdd;
//        return users;
    }

    // returns specific user by username
    public Optional<Object> getByUsername(@PathVariable String username) {
        Optional<Object> userOptional = _userRepository.findByUsername(username);
        return userOptional;
    }

    // return by id
    public Optional<User> getById(@PathVariable Long id) {
        Optional<User> user = _userRepository.findById(id);
        return user;
    }

    // delete by id
    public ResponseEntity deleteUser(@PathVariable Long id) {
        boolean exists = _userRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
        _userRepository.deleteById(id);
        return ResponseEntity.ok(id);
    }
}
