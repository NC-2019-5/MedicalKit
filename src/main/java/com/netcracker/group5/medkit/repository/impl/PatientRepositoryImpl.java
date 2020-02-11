package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.Sex;
import com.netcracker.group5.medkit.repository.PatientRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Map;

@Repository
public class PatientRepositoryImpl implements PatientRepository {
    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Transactional
    @Override
    public Patient save(Long userId, Patient patient) {
        SqlParameterSource parameterSourcePatient = new MapSqlParameterSource()
                .addValue("p_patient_object_id", patient.getId())
                .addValue("p_patient_email", patient.getEmail())
                .addValue("p_patient_password", patient.getPassword())
                .addValue("p_patient_role", patient.getRole().getRoleName())
                .addValue("p_patient_name", patient.getName())
                .addValue("p_patient_surname", patient.getSurname())
                .addValue("p_patient_birth_date", patient.getBirthDate())
                .addValue("p_patient_sex", patient.getSex().name())
                .addValue("p_patient_weight", patient.getWeight())
                .addValue("p_patient_height", patient.getHeight())
                .addValue("p_patient_location", patient.getLocation())
                .addValue("p_patient_phone_number", patient.getPhoneNumber());

        Map<String, Object> resultPatient = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("savePatientObject")
                .execute(parameterSourcePatient);
        logger.info("Patient saved!");

        return buildPatientFromResult(resultPatient);
    }

    @Override
    public Patient findById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_object_id", id);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("getPatientObject")
                .execute(parameterSource);
        logger.info("Patient found by it's id!");
        return buildPatientFromResult(result);

    }

    private Patient buildPatientFromResult(Map<String, Object> resultPatient) {
        Patient patient = Patient.newBuilder()
                .setEmail((String) resultPatient.get("p_patient_email"))
                .setPassword((String) resultPatient.get("p_patient_password"))
                .setRole(Role.getRoleByName((String) resultPatient.get("p_patient_role")))
                .setName((String) resultPatient.get("p_patient_name"))
                .setSurname((String) resultPatient.get("p_patient_surname"))
                .setWeight(Float.parseFloat((String) resultPatient.get("p_patient_weight")))
                .setHeight(Float.parseFloat((String) resultPatient.get("p_patient_height")))
                .setLocation((String) resultPatient.get("p_patient_location"))
                .setPhoneNumber((String) resultPatient.get("p_patient_phone_number"))
                .build();

        Object patientId = resultPatient.get("p_patient_object_id");
        Object patientBirthDate = resultPatient.get("p_patient_birth_date");
        Object patientSex = resultPatient.get("p_patient_sex");

        patient.setId(patientId == null ? -1L : ((BigDecimal) patientId).longValue());
        patient.setBirthDate(patientBirthDate == null ? null : ((Timestamp) patientBirthDate).toLocalDateTime().toLocalDate());
        patient.setSex(patientSex == null ? null : Sex.valueOf(patientSex.toString()));
        logger.info("Patient builded!");
        return patient;
    }
}
