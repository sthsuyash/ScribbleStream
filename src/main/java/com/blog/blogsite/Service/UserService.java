package com.blog.blogsite.Service;

import com.blog.blogsite.DTO.RegisterRequest;
import com.blog.blogsite.Exception.ResourceNotFoundException;
import com.blog.blogsite.Exception.UsernameTakenException;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private AuthService _authService;
    @Autowired
    private final UserRepository _userRepository;

    public UserService(UserRepository _userRepository, AuthService authService) {
        this._userRepository = _userRepository;
        _authService = authService;
    }

    // returns all the users in database excluding password
    public List<User> getAllUsers() {
        List<User> users = _userRepository.findAll();

        List<User> usersToAdd = new ArrayList<>();

        for (User usersInDB : users) {

            if (!usersInDB
                    .getUsername()
                    .equals(
                            _authService.getCurrentUser()
                                    .get()
                                    .getUsername())
            ) {
                User user = new User();
                user.setUsername(usersInDB.getUsername());
                user.setEmail(usersInDB.getEmail());
                user.setId(usersInDB.getId());
                usersToAdd.add(user);
            }
        }
        return usersToAdd;
    }

    // returns specific user by username
    public User getByUsername(@PathVariable String username) {
        User user = (User) _userRepository.findByUsername(username)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with username: " + username)));

        if (user.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            return user;
        }
        return null;
    }

    // get by id
    public User getById(@PathVariable Long id) {
        User user = _userRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with id: " + id)));

        if (user.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            return user;
        }
        return null;
    }

    // edit by id
    public ResponseEntity editById(@PathVariable Long id, @RequestBody RegisterRequest userToEdit) {

        // username must not be duplicate
        Optional<Object> userOptional = _userRepository.findByUsername(userToEdit.getUsername());

        User user = _userRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with id: " + id)));

        if (userOptional.isPresent()) {
            throw new UsernameTakenException("Username taken!!");
        } else if (user.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {

            User userToUpdate = new User();

            // setting the required contents
            userToUpdate.setId(id);
            userToUpdate.setUsername(userToEdit.getUsername());
            userToUpdate.setEmail(userToEdit.getEmail());
            userToUpdate.setPassword(_authService.encodePassword(userToEdit.getPassword()));

            _userRepository.save(userToUpdate);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // delete by id
    public ResponseEntity deleteUser(@PathVariable Long id) {
        User user = _userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

        if (user.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            _userRepository.delete(user);

            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }
}
