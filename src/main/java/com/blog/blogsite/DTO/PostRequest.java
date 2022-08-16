package com.blog.blogsite.DTO;

public class PostRequest {
    private Long id;
    private String content;
    private String title;
    private String username;
    private Long views;
    private int word_count;
    private String most_used_words;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
