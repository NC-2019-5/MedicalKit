package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.Sex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

@Repository
public class PatientRepositoryImpl implements PatientRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Patient save(Patient patient) {
        SqlParameterSource parameterSourceUser = new MapSqlParameterSource()
                .addValue("p_user_object_id", patient.getId())
                .addValue("p_user_email", patient.getEmail())
                .addValue("p_user_password", patient.getPassword())
                .addValue("p_user_role", patient.getRole().toString());

        SqlParameterSource parameterSourcePatient = new MapSqlParameterSource()
                .addValue("p_patient_object_id", patient.getId())
                .addValue("p_patient_name", patient.getName())
                .addValue("p_patient_surname", patient.getSurname())
                .addValue("p_patient_birth_date", patient.getBirthDate())
                .addValue("p_patient_sex", patient.getSex())
                .addValue("p_patient_weight", patient.getWeight())
                .addValue("p_patient_height", patient.getHeight())
                .addValue("p_patient_location", patient.getLocation())
                .addValue("p_patient_phone_number", patient.getPhoneNumber())
                .addValue("p_patient_user_id", patient.getId());

        Map<String, Object> resultUser = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("saveUserObject")
                .execute(parameterSourceUser);

        Map<String, Object> resultPatient = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("savePatientObject")
                .execute(parameterSourcePatient);

        System.out.println("resultUser = " + resultUser);
        System.out.println("resultPatient = " + resultPatient);

        Patient patientFromDb = buildPatientFromResult(resultPatient);

        patientFromDb.setEmail(resultUser.get("p_user_email").toString());
        patientFromDb.setPassword(resultUser.get("p_user_password").toString());
        patientFromDb.setRole(Role.valueOf(resultUser.get("p_user_role").toString()));

        return patientFromDb;
    }

    @Override
    public Patient findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_object_id", id);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("getPatientById")
                .execute(parameterSource);

        System.out.println(result);

        return buildPatientFromResult(result);
    }

    private Patient buildPatientFromResult(Map<String, Object> resultPatient) {
        Patient patient = Patient.newBuilder()
                .setName(resultPatient.get("p_patient_name").toString())
                .setSurname(resultPatient.get("p_patient_surname").toString())
                .setWeight(Float.parseFloat(resultPatient.get("p_patient_weight").toString()))
                .setHeight(Float.parseFloat(resultPatient.get("p_patient_height").toString()))
                .setLocation(resultPatient.get("p_patient_location").toString())
                .setPhoneNumber(resultPatient.get("p_patient_phone_number").toString())
                .build();

        Object patientId = resultPatient.get("p_patient_object_id");
        Object patientBirthDate = resultPatient.get("p_patient_birth_date");
        Object patientSex = resultPatient.get("p_patient_sex");

        patient.setId(patientId == null ? -1L : ((BigDecimal) patientId).longValue());
        patient.setBirthDate(patientBirthDate == null ? null : ((Timestamp) patientBirthDate).toLocalDateTime().toLocalDate());
        patient.setSex(patientSex == null ? null : Sex.valueOf(patientSex.toString()));

        return patient;
    }
}
