package com.netcracker.group5.medkit.security;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Component
public class TokenHandler {

    private final SecretKey secretKey;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    public TokenHandler() {
        String jwtKey = "coolJwtKeyForMedKit";
        byte[] decodedKey = jwtKey.getBytes(Charset.defaultCharset());
        secretKey = new SecretKeySpec(decodedKey, signatureAlgorithm.getJcaName());
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

    public String generateToken(String email, LocalDateTime expiresIn) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .claim("email", email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(expiresIn.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }

}
