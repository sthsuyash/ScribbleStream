package com.blog.blogsite.Controller;

import com.blog.blogsite.DTO.PostRequest;
import com.blog.blogsite.Service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    @Autowired
    private PostService _postService;

    // creating a new post
    @PostMapping("/create")
    public ResponseEntity createPost(@RequestBody PostRequest postRequest) {

        _postService.createPost(postRequest);

        return new ResponseEntity(HttpStatus.OK);

    }

    // getting all posts // except the users own posts
    @GetMapping
    public ResponseEntity<List<PostRequest>> getAllPosts() {
        return new ResponseEntity<>(_postService.getAllPosts(), HttpStatus.OK);
    }

    // get specific post by id
    @GetMapping("/{id}")
    public ResponseEntity<PostRequest> getPostById(@PathVariable Long id) {
        return new ResponseEntity<>(_postService.getPostById(id), HttpStatus.OK);
    }

    // updating a post

}
