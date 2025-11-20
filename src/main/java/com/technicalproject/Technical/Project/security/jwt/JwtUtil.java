package com.technicalproject.Technical.Project.security.jwt;

import com.technicalproject.Technical.Project.model.CustomUser;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    @Value("auth.token.jwtSecret")
    private String jwtSecretKey;

    public String getUserNameFromToken(String token){
        return Jwts.parser()
                .verifyWith(key())
                .build()
                .parseSignedClaims(token).getPayload().getSubject();
    }

    public String generateTokenFromUser(Authentication authentication){
        CustomUser principal = (CustomUser)authentication.getPrincipal();

       return Jwts.builder()
                .subject(principal.getUsername())
                .claim("authority",principal.getAuthorities())
                .signWith(key())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+60*60*60))
                .compact();
    }

    public SecretKey key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(jwtSecretKey));
    }

    public boolean validateToken(String token){
        try{
            Jwts.parser()
                    .verifyWith(key())
                    .build()
                    .parseSignedClaims(token);
            return true;
        }
        catch (Exception e){
            throw new JwtException(e.getMessage());
        }
    }
}
