package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Patient save(Patient patient) {
        String queryInsert = "INSERT INTO Patient VALUES(patientSeq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Object[] paramsInsert = new Object[]{
                patient.getName(),
                patient.getSurname(),
                patient.getBirthDate(),
                patient.getSex().toString(),
                patient.getWeight(),
                patient.getHeight(),
                patient.getLocation(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getPassword()};

        String querySelect = "SELECT * FROM Patient WHERE email = ?";
        Object[] paramsSelect = new Object[]{patient.getEmail()};

        jdbcTemplate.update(queryInsert, paramsInsert);

        return jdbcTemplate.queryForObject(querySelect, paramsSelect, (resultSet, i) -> Patient.newBuilder()
                .setId(resultSet.getLong("id"))
                .setName(resultSet.getString("name"))
                .setSurname(resultSet.getString("surname"))
                .setBirthDate(resultSet.getObject("birthDate", LocalDate.class))
                .setSex(Sex.valueOf(resultSet.getString("sex")))
                .setHeight(resultSet.getFloat("height"))
                .setWeight(resultSet.getFloat("weight"))
                .setLocation(resultSet.getString("location"))
                .setPhoneNumber(resultSet.getString("phoneNumber"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.PATIENT)
                .build()
        );
    }
}
