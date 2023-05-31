package com.example.springevent.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;
import java.util.function.Function;


@Service
public class SecurityFilterService {
    private final String signInKey = "7A25432A462D4A614E645266556A586E3272357538782F413F4428472B4B6250";
    private final long ACCESS_TOKEN_TIME=15*60*1000;
    private final long REFRESH_TOKEN_TIME=3*60*60*1000;

    private SecretKey getKey() {
        byte[] key = Base64.getDecoder().decode(signInKey);
        return Keys.hmacShaKeyFor(key);
    }

    public Claims getClaims(String token) {
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

    public HashMap<String,Object> ClaimsToHashMap(Claims claims){
        HashMap<String,Object> map=new HashMap<>();
        for(Map.Entry<String,Object> entry : claims.entrySet()){
            map.put(entry.getKey(),entry.getValue());
        }
        return map;
    }

    public String generateToken(HashMap<String, Object> claims, UserDetails user, long time) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + time))
                .signWith(getKey())
                .compact();
    }

    public String generateToken(UserDetails user) {
        return generateToken(new HashMap<>(), user, ACCESS_TOKEN_TIME);
    }

    public String generateRefreshToken(UserDetails user){
        return generateToken(new HashMap<>(), user, REFRESH_TOKEN_TIME);
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
