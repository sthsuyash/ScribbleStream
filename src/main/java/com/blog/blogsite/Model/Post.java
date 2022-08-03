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
    private Instant updatedOn; // Date of Blog Post updated

    @Column
    @NotBlank
    private String username; // Name of author of BlogPost

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

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", createdOn=" + createdOn +
                ", updatedOn=" + updatedOn +
                ", username='" + username + '\'' +
                '}';
    }
}
