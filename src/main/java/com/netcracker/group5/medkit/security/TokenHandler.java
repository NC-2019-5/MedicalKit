package com.netcracker.group5.medkit.security;

import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Component
public class TokenHandler {

    private final SecretKey secretKey;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;
    private final int tokenTimeToLive = 1;

    public TokenHandler() {
        String jwtKey = "coolJwtKeyForMedKit";
        byte[] decodedKey = jwtKey.getBytes(Charset.defaultCharset());
        secretKey = new SecretKeySpec(decodedKey, signatureAlgorithm.getJcaName());
    }

    public String extractUserEmail(String token) {
        return extractClaims(token).get("email").toString();
    }

    public Long extractUserId(String token) {
        return Long.parseLong(extractClaims(token).getId());
    }

    public Role extractUserRole(String token) {
        return Role.valueOf(extractClaims(token).get("role").toString());
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, User user) {
        final Long id = extractUserId(token);
        final String email = extractUserEmail(token);
        final Role role = extractUserRole(token);

        return user.getId().equals(id) && user.getEmail().equals(email) && user.getRole().equals(role) && !isTokenExpired(token);
    }

    private Claims extractClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
        return claimsJws.getBody();
    }

    public String generateToken(User user) {
        return createToken(user, LocalDateTime.now().plusMinutes(tokenTimeToLive));
    }

    private String createToken(User user, LocalDateTime expiresIn) {
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setId(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(expiresIn.atZone(ZoneId.systemDefault()).toInstant()))
                .signWith(signatureAlgorithm, secretKey)
                .compact();
    }
}
