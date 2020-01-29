package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.PasswordResetToken;

import java.util.Optional;

public interface PasswordResetTokenService {    

    String getUserEmailByToken(String token);

    void addToken(PasswordResetToken token);

    void deleteToken(String token);

    void bulkDeleteToken();

}
