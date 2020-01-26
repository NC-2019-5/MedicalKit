package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.PasswordResetToken;
import com.netcracker.group5.medkit.repository.PasswordResetTokenRepository;
import com.netcracker.group5.medkit.service.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PasswordResetTokenTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository resetTokenRepository;

    @Override
    public String
    getUserEmailByToken(String token) {
        return resetTokenRepository.findUserEmailByToken(token);
    }

    @Override
    public void addToken(PasswordResetToken token) {
        resetTokenRepository.save(token);
    }

    @Override
    public void deleteToken(String token) {
        resetTokenRepository.deleteToken(token);
    }
}
