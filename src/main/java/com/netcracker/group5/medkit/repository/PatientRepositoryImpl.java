package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Objects;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Patient save(Patient patient) {
        if (isPatientExists(patient)) {
            System.out.println("patient exists. " + patient);

            String queryUpdate = "UPDATE Patient SET " +
                    "name = ?, surname = ?, birthDate = ?, sex = ?, weight = ?, height  = ?, location = ?, phoneNumber = ?, email = ? " +
                    "WHERE id = ?";
            Object[] paramsUpdate = new Object[]{
                    patient.getName(),
                    patient.getSurname(),
                    patient.getBirthDate(),
                    patient.getSex().toString(),
                    patient.getWeight(),
                    patient.getHeight(),
                    patient.getLocation(),
                    patient.getPhoneNumber(),
                    patient.getEmail(),
                    patient.getId()};

            jdbcTemplate.update(queryUpdate, paramsUpdate);
        } else {
            System.out.println("patient does not exist");

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

            jdbcTemplate.update(queryInsert, paramsInsert);
        }

        String querySelect = "SELECT * FROM Patient WHERE email = ?";
        Object[] paramsSelect = new Object[]{patient.getEmail()};

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

    @Override
    public Patient findById(long id) {
        String querySelect = "SELECT * FROM Patient WHERE id = ?";
        Object[] paramsSelect = new Object[]{id};

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

    public boolean isPatientExists(Patient patient) {
        String queryExist = "SELECT COUNT(*) FROM Patient WHERE email = ?";
        Object[] paramsExist = new Object[]{patient.getEmail()};

        return Objects.equals(jdbcTemplate.queryForObject(queryExist, paramsExist, Integer.class), 1);
    }
}
