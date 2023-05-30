package com.example.springevent.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;


@Service
public class SecurityFilterService {
    private final String signInKey = "7A25432A462D4A614E645266556A586E3272357538782F413F4428472B4B6250";

    private SecretKey getKey() {
        byte[] key = Base64.getDecoder().decode(signInKey);
        return Keys.hmacShaKeyFor(key);
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaims(String token, Function<Claims, T> claimProcessor) {
        return claimProcessor.apply(getClaims(token));
    }

    public String getUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public String generateToken(HashMap<String, Objects> claims, UserDetails user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000*60*60))
                .signWith(getKey())
                .compact();
    }

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user);
    }

    public boolean isUserValid(String token, UserDetails user) {
        return isTokenNonExpired(token) || isUserEnable(user);
    }

    public boolean isTokenNonExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    public boolean isUserEnable(UserDetails user) {
        return user.isEnabled();
    }
}
