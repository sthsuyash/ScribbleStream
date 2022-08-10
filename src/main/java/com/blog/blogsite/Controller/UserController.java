package com.blog.blogsite.Controller;

import com.blog.blogsite.DTO.RegisterRequest;
import com.blog.blogsite.Model.User;
import com.blog.blogsite.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<User> getUser(@PathVariable String username) {
        return new ResponseEntity<User>(_userService.getByUsername(username), HttpStatus.OK);
    }

    // get specific user by id
    @GetMapping("/id/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return new ResponseEntity<User>(_userService.getById(id), HttpStatus.OK);
    }

    // edit by id
    @PutMapping("/edit/{id}")
    public ResponseEntity updateEmployee(@PathVariable Long id, @RequestBody RegisterRequest userToEdit) {
        return _userService.editById(id, userToEdit);
    }

    // delete specific user by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        return _userService.deleteUser(id);
    }
}
