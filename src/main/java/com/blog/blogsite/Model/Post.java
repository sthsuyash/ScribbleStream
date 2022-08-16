// store all our posts

package com.blog.blogsite.Model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;

@Entity
@Table
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // must not be null and must contain atleast one not like space character
    // we donot want to accept empty or no title for blog posts
    @Column
    private String title; // Title of Blog Post

    @Lob
    @NotEmpty
    @Column
    private String content; // Content of Blog Post

    @Column
    private Instant createdOn; // Date of Blog Post creation

    @Column
    private Instant updated_on; // Date of Blog Post updated

    @Column
    @NotBlank
    private String username; // Name of author of BlogPost

    @Column
    private Long views; // Number of views of BlogPost

    @Column
    private int word_count; // Number of words in BlogPost

    @Column
    private String most_used_words; // Most used words in BlogPost

    // getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Instant createdOn) {
        this.createdOn = createdOn;
    }

    public Instant getUpdated_on() {
        return updated_on;
    }

    public void setUpdated_on(Instant updated_on) {
        this.updated_on = updated_on;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public int getWord_count() {
        return word_count;
    }

    public void setWord_count(int word_count) {
        this.word_count = word_count;
    }

    public String getMost_used_words() {
        return most_used_words;
    }

    public void setMost_used_words(String most_used_words) {
        this.most_used_words = most_used_words;
    }
}

