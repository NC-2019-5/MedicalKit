package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Patient save(Patient patient) {
        String queryInsert = "INSERT INTO AppUser VALUES(?, ?, ?, ?)";
        Object[] paramsInsert = new Object[]{
                patient.getId(),
                patient.getEmail(),
                patient.getPassword(),
                patient.getRole()};

        String querySelect = "SELECT id, email, password ,role FROM AppUser WHERE email = ?";
        Object[] paramsSelect = new Object[]{patient.getEmail()};

        jdbcTemplate.update(queryInsert, paramsInsert);
        return jdbcTemplate.queryForObject(querySelect, paramsSelect, Patient.class);
    }
}
