package com.blog.blogsite.Controller;

import com.blog.blogsite.Exception.ResourceNotFoundException;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Repository.UserRepository;
import com.blog.blogsite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private final UserService _userService;

    public UserController(UserService _userService) {
        this._userService = _userService;
    }

    // returns list of all the users in the database, excluding password
    @GetMapping("")
    public List<User> getUsers() {
        return _userService.getAllUsers();
    }

    // get specific user by username
    @GetMapping("/{username}")
    public Optional<Object> getUser(@PathVariable String username) {
        return _userService.getByUsername(username);
    }

    // get specific user by id
    @GetMapping("/id/{id}")
    public Optional<User> getById(@PathVariable Long id) {
        return _userService.getById(id);
    }

    @Autowired
    private UserRepository _userRepository;

    // edit by id
    @PutMapping("/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody User userToEdit) {
        User user = _userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee doesn't exist with id: " + id + "."));

        user.setUsername(userToEdit.getUsername());
        user.setPassword(userToEdit.getPassword());
        user.setEmail(userToEdit.getEmail());

        User updatedEmployee = _userRepository.save(user);
        return ResponseEntity.ok(updatedEmployee);
    }

    // delete specific user by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        _userService.deleteUser(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
