package com.test.Testing.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {
    @Value("${jwt.secret}")
    private String jwtSecretKey;

    @Value("${access.token.expiration}")
    private Long accessTokenExpiration;

    @Value("${refresh.token.expiration}")
    private Long refreshTokenExpiration;
    public String extractUsername(String token){
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String generateAccessToken(Map<String, Object> claims, String email){
        Date expiration = Date.from(Instant.now().plusSeconds(accessTokenExpiration));
        return Jwts.builder()
                .setIssuer("Testing_app")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setClaims(claims)
                .setExpiration(expiration)
                .signWith(getSignInKey(),SignatureAlgorithm.HS512)
                .setSubject(email)
                .compact();
    }

    public String generateRefreshToken(String email){
        Date refreshExpiration = Date.from(Instant.now().plusSeconds(refreshTokenExpiration));
        return Jwts.builder()
                .setIssuer("Testing_app")
                .setIssuedAt(new Date())
                .setExpiration(refreshExpiration)
                .signWith(getSignInKey(), SignatureAlgorithm.HS512)
                .setSubject(email)
                .compact();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSignInKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (SignatureException exception){
            return false;
        }catch (JwtException exception){
            return false;
        }
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
