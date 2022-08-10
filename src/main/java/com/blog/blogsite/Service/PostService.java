package com.blog.blogsite.Service;

import com.blog.blogsite.DTO.PostRequest;
import com.blog.blogsite.Exception.PostNotFoundException;
import com.blog.blogsite.Model.Post;
import com.blog.blogsite.Repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository _postRepository;

    @Autowired
    private AuthService _authService;

    // creating post
    public void createPost(PostRequest postRequest) {

        Post post = new Post();

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());

        User loggedInUser = _authService.getCurrentUser()
                .orElseThrow(() ->
                        new IllegalArgumentException("No User logged in!"));

        post.setUsername(loggedInUser.getUsername()); // retreive from auth service
        post.setCreatedOn(Instant.now());
        post.setUpdatedOn(Instant.now());

        _postRepository.save(post);

    }

    // get all posts // except the users own posts
    public List<PostRequest> getAllPosts() {

        List<Post> posts = _postRepository.findAll();
        List<PostRequest> postsToSend = new ArrayList<>();

        for (Post post : posts) {
            if (!post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
                postsToSend.add(mapFromPostToPostRequest(post));
            }
        }
        return postsToSend;
    }

    // get all posts // of the current user
    public List<PostRequest> getAllUserPosts() {
        List<Post> posts = _postRepository.findAll();
        List<PostRequest> postsToSend = new ArrayList<>();

        for (Post post : posts) {
            if (post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
                postsToSend.add(mapFromPostToPostRequest(post));
            }
        }
        return postsToSend;
    }


    private PostRequest mapFromPostToPostRequest(Post post) {

        PostRequest postRequest = new PostRequest();
        postRequest.setId(post.getId());
        postRequest.setTitle(post.getTitle());
        postRequest.setContent(post.getContent());
        postRequest.setUsername(post.getUsername());

        return postRequest;
    }

    // get post by post id
    public PostRequest getPostById(Long id) {
        Post post = _postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("No Post found with id: " + id));

        if (post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            return mapFromPostToPostRequest(post);
        }
        return null;
    }

    // update post by id
    public void updatePost(Long id, PostRequest postRequest) {
        Post post = _postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("No Post found with id: " + id));

        if (post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setUpdatedOn(Instant.now());

            _postRepository.save(post);
        }
    }

    // deleting a post by post id
    public void deletePost(Long id) {
        Post post = _postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("No Post found with id: " + id));

        if (post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            _postRepository.delete(post);
        }
    }
}
