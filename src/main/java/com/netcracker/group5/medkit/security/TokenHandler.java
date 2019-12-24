package com.netcracker.group5.medkit.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Optional;

@Component
public class TokenHandler {

    private final SecretKey secretKey;

    public TokenHandler() {
        String jwtKey = "coolJwtKeyForMedKit";
        byte[] decodedKey = Base64.getDecoder().decode(jwtKey);
        secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    }

    public Optional<String> extractUserEmail(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
            Claims body = claimsJws.getBody();

            return Optional.of(body.get("email").toString());
        } catch (RuntimeException e) {
            return Optional.empty();
        }

    }
}
