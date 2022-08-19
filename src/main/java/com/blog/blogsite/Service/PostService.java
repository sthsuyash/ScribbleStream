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

        User loggedInUser = _authService.getCurrentUser()
                .orElseThrow(() ->
                        new IllegalArgumentException("No User logged in!"));

        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setViews(0L);

        post.setUsername(loggedInUser.getUsername()); // retreive from auth service
        post.setCreatedOn(Instant.now());
        post.setUpdated_on(Instant.now());

        post.setWord_count(getTotalWords(postRequest.getContent()));
        post.setMost_used_words(getMostUsedWords(postRequest.getContent()));

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
        postRequest.setViews(post.getViews());
        postRequest.setMost_used_words(getMostUsedWords(post.getContent()));
        postRequest.setWord_count(getTotalWords(post.getContent()));

        return postRequest;
    }

    // get post by post id
    public PostRequest getPostById(Long id) {
        Post post = _postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("No Post found with id: " + id));

            post.setViews(post.getViews() + 1);
            _postRepository.save(post);
            return mapFromPostToPostRequest(post);
    }

    // update post by id
    public void updatePost(Long id, PostRequest postRequest) {
        Post post = _postRepository.findById(id)
                .orElseThrow(() ->
                        new PostNotFoundException("No Post found with id: " + id));

        if (post.getUsername().equals(_authService.getCurrentUser().get().getUsername())) {
            post.setTitle(postRequest.getTitle());
            post.setContent(postRequest.getContent());
            post.setUpdated_on(Instant.now());

            post.setWord_count(getTotalWords(postRequest.getContent()));
            post.setMost_used_words(getMostUsedWords(postRequest.getContent()));

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

    // total number of words in a post
    public int getTotalWords(String content) {
        return content.split(" ").length; // split into array of strings and return length of array
    }

    // most used words;
    public String getMostUsedWords(String content) {

        ArrayList<String> totalWords = new ArrayList<>();
        content = content.replaceAll("\\p{Punct}", " "); // remove punctuation
        String[] words = content.split(" "); // split into array of individual words

        for (String word : words) {
            if (word != null && !word.isEmpty()) {
                totalWords.add(word);
            }
        }

        int count = 0, maxCount = 0;
        String word = "";

        // Determine the most repeated word in a file
        for (int i = 0; i < totalWords.size(); i++) {
            count = 1;
            //Count each word in the file and store it in variable count
            for (int j = i + 1; j < totalWords.size(); j++) {
                if (totalWords.get(i).equals(totalWords.get(j))) {
                    count++;
                }
            }
            // If maxCount is less than count then store value of count in maxCount and corresponding word to variable word
            if (count > maxCount) {
                maxCount = count;
                word = totalWords.get(i);
            }
        }
        return word;
    }
}
