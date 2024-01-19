package com.social.BackEnd.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;
import java.util.Date;

public class jwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(jwtConstant.SECRET_KEY.getBytes());
    public static String generateToken(Authentication auth) {
        String jwt = Jwts.builder()
                .setIssuer("SocialMedia").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 1000 * 60 * 60 * 5))
                .claim("email",auth.getName())
                .signWith(key).compact();
        return jwt;
    }

    public static String getEmailFromJwtToken(String jwt) {
        // Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser().setSigningKey(key).build()
                .parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }

}
