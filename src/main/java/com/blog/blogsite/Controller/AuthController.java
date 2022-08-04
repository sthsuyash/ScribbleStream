package com.blog.blogsite.Controller;

import com.blog.blogsite.DTO.RegisterRequest;
import com.blog.blogsite.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService _authService;

    @PostMapping("/signup")
    public void signUp(@RequestBody RegisterRequest registerRequest) {
        _authService.signup(registerRequest);
    }

}
