package com.blog.blogsite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;

@SpringBootApplication(exclude= {UserDetailsServiceAutoConfiguration.class})
public class BlogSiteApplication {

    public static void main(String[] args) {

        SpringApplication.run(BlogSiteApplication.class, args);
    }

}
