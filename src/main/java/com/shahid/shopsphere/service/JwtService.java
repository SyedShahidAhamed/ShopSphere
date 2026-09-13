package com.shahid.shopsphere.service;
import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
    @Value("${jwt.secret}")//inject from application.properties
    private String secretKey;
    @Value("${jwt.expiration}")
    private long jwtExpiration;

    private Key getSignInWithKey()
    {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public String generateToken(UserDetails userdetails)
    {
        return Jwts.builder()
                    .subject(userdetails.getUsername())
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis()+jwtExpiration))
                    .signWith(getSignInWithKey())
                    .compact();
    }
    
    private Claims extractAllClaims(String token)
    {
        return Jwts.parser()
                    .verifyWith((javax.crypto.SecretKey)getSignInWithKey())
                    .build() 
                    .parseSignedClaims(token)
                    .getPayload();
    }

    public <T> T extractClaims(String token,
                 java.util.function.Function<Claims,T> claimsResolver)
    {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
    }
    

    public String extractUserName(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return extractClaims(token, Claims::getExpiration);
    }
    
    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }
    public boolean isTokenValid(String token,UserDetails userDetails)
    {
         final String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
}
