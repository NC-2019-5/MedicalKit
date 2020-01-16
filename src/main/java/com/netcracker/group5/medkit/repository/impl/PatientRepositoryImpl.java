package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Sex;
import com.netcracker.group5.medkit.repository.PatientRepository;
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
    public Patient save(Long userId, Patient patient) {
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
                .addValue("p_patient_user_id", userId);

        Map<String, Object> resultPatient = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("savePatientObject")
                .execute(parameterSourcePatient);

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

        return buildPatientFromResult(result);
    }

    @Override
    public Patient findByUserId(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_user_id", id);

        Map<String, Object> result;

        try {
            result = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName("PATIENT_PKG")
                    .withProcedureName("getPatientByUserId")
                    .execute(parameterSource);

            return buildPatientFromResult(result);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
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
