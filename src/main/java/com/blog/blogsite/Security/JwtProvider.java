package com.blog.blogsite.Security;

//class responsible for generating json web token

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.security.Key;

@Service
public class JwtProvider {

    private Key key;

    // create a key only once
    // and reuse it to sign jwt everytime

    // generating at server start
    @PostConstruct
    public void init() {
        key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }


    public String generateJwtToken(Authentication authentication) {
        User principal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(principal.getUsername())
                .signWith(key)
                .compact();
    }

    public boolean validateToken(String jwt) {
        Jwts
                .parser()
                .setSigningKey(key)
                .parseClaimsJws(jwt);
        return true;
    }

    public String getUserNameFromJwt(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(key)
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}

// assymetric encryption using java keystore

// use java key store that contains public key and private key
// when generating token, use private key to sign the token
// and public key to validate the token

// keystore ad =>
// generate key and store in resouces folder in disk
// and read public and private key
// and eliminate inconsistencies with the key.