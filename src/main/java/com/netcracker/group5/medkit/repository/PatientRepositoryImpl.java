package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
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
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_object_id", patient.getId())
                .addValue("p_patient_full_name", patient.getName() + " " + patient.getSurname())
                .addValue("p_patient_name", patient.getName())
                .addValue("p_patient_surname", patient.getSurname())
                .addValue("p_patient_birth_date", patient.getBirthDate())
                .addValue("p_patient_sex", patient.getSex())
                .addValue("p_patient_weight", patient.getWeight())
                .addValue("p_patient_height", patient.getHeight())
                .addValue("p_patient_location_id", patient.getLocation())
                .addValue("p_patient_phone_number", patient.getPhoneNumber())
                .addValue("p_patient_user_id", null);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PATIENT_PKG")
                .withProcedureName("savePatientObject_2")
                .execute(parameterSource);

        System.out.println(result);

        return buildPatientFromResult(result);
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

    private Patient buildPatientFromResult(Map<String, Object> result) {
        Patient patient = Patient.newBuilder()
                .setName((String) result.get("p_patient_name"))
                .setSurname((String) result.get("p_patient_surname"))
                .setBirthDate(((Timestamp) result.get("p_patient_birth_date")).toLocalDateTime().toLocalDate())
                .setSex(Sex.valueOf((String) result.get("p_patient_sex")))
                .setWeight(Float.parseFloat((String) result.get("p_patient_weight")))
                .setHeight(Float.parseFloat((String) result.get("p_patient_height")))
                .setLocation((String) result.get("p_patient_location_id"))
                .setPhoneNumber((String) result.get("p_patient_phone_number"))
                .build();

        Object patientId = result.get("p_patient_object_id");
        patient.setId(patientId == null ? -1L : ((BigDecimal) patientId).longValue());

        return patient;
    }
}
