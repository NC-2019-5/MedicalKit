package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.PasswordResetToken;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@Repository
public class PasswordResetTokenRepositoryImpl implements PasswordResetTokenRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }


    @Override
    public String findUserEmailByToken(String token) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_token", token);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName("TOKEN_PKG")
                    .withProcedureName("findUserEmailByToken")
                    .execute(parameterSource);


        return (String) result.get("p_user_email");
    }

    @Override
    public PasswordResetToken save(PasswordResetToken token) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_email", token.getUserEmail())
                .addValue("p_token", token.getToken())
                .addValue("p_created_date", token.getCratedDate());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TOKEN_PKG")
                .withProcedureName("saveToken")
                .execute(parameterSource);

        PasswordResetToken resetToken = PasswordResetToken.newBuilder()
                .setUserEmail((String) result.get("p_user_email"))
                .setToken((String) result.get("p_token"))
                .setCreatedDate((Date) result.get("p_created_date"))
                .build();

        return resetToken;
    }

    @Override
    public void deleteToken(String token) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_token", token);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("TOKEN_PKG")
                .withProcedureName("deleteToken")
                .execute(parameterSource);
    }
}
