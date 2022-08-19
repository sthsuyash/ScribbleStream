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
                user = withoutPassword(usersInDB);

                usersToAdd.add(user);
            }
        }
        return usersToAdd;
    }

    // returns specific user by username
    public User getByUsername(@PathVariable String username) {
        User user = (User) _userRepository.findByUsername(username)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with username: " + username)));

        User userToAdd = new User();
        userToAdd = withoutPassword(user);

        return userToAdd;
    }

    // get by id
    public User getById(@PathVariable Long id) {
        User user = _userRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with id: " + id)));

        User userToSend = new User();
        userToSend = withoutPassword(user);

        return userToSend;
    }

    private User withoutPassword(User user) {
        User userToSend = new User();
        userToSend.setUsername(user.getUsername());
        userToSend.setEmail(user.getEmail());
        userToSend.setId(user.getId());

        return userToSend;
    }

    // edit by id
    // can't edit username
    public ResponseEntity editById(@PathVariable Long id, @RequestBody RegisterRequest userToEdit) {

        User user = _userRepository.findById(id)
                .orElseThrow((() -> new ResourceNotFoundException("User not found with id: " + id)));

        if (user.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {

            User userToUpdate = new User();

            // setting the required contents
            // not username
            userToUpdate.setUsername(user.getUsername());

            userToUpdate.setId(id);
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
