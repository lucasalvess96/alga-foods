package org.estudos.algafoods.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    public static final int EXPIRATION_ONE_DAY = 86400000;

    private final JwtKeyProvider jwtKeyProvider;

    public JwtUtil(JwtKeyProvider jwtKeyProvider) {
        this.jwtKeyProvider = jwtKeyProvider;
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_ONE_DAY))
                .signWith(jwtKeyProvider.getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
