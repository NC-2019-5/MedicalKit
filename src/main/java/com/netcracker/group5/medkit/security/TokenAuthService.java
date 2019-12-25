package com.netcracker.group5.medkit.security;

import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Service
public class TokenAuthService {
    public static final String AUTH_HEADER_NAME = "X-Auth-Token";

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;

    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        return Optional
                .ofNullable(request.getHeader(AUTH_HEADER_NAME))
                .flatMap(tokenHandler::extractUserEmail)
                .flatMap(s -> Optional.of(userService.loadUserByUsername(s)))
                .map(UserAuthentication::new);
    }
}
