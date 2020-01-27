package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface PasswordResetTokenRepository{

    String findUserEmailByToken (String token);

    PasswordResetToken save(PasswordResetToken token);

    void deleteToken(String token);

    void bulkDeleteToken();
}
