package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private static final Logger log = Logger.getLogger(UserRepositoryImpl.class);
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public User findUserByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_email", email);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("getUserByEmail")
                .execute(parameterSource);
        log.info("User found by email!");

        return User.newUserBuilder()
                .setId(((BigDecimal) result.get("p_user_object_id")).longValue())
                .setEmail((String) result.get("p_user_email"))
                .setPassword((String) result.get("p_user_password"))
                .setRole(Role.getRoleByName((String) result.get("p_user_role")))
                .build();
    }

    /*
     * Invokes method from Patient/Doctor repository
     * (depends on user.role)
     */
    @Override
    public User saveByRole(User user) {
        if (user.getRole().equals(Role.PATIENT)) {

            Patient editedPatient = (Patient) user;

            if (user.getId() != null) {
                Optional<Patient> patientToBeSaved = Optional.of(patientRepository.findById(user.getId()));

                editedPatient.setId(patientToBeSaved.get().getId());
                editedPatient.setSex(patientToBeSaved.get().getSex());
                editedPatient.setPassword(patientToBeSaved.get().getPassword());
                editedPatient.setEmail(patientToBeSaved.get().getEmail());
                editedPatient.setRole(patientToBeSaved.get().getRole());
            }
            log.info("User saved by role!");
            return patientRepository.save(user.getId(), editedPatient);
        }
        log.error("Invalid role!");
        throw new IllegalArgumentException("User role" + user.getRole() + " does not exist");
    }

    @Override
    public boolean isExistUserWithEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_email", email);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("isExistUserWithEmail")
                .execute(parameterSource);
        log.info("Existent of user is known now!");

        return Boolean.parseBoolean(result.get("p_return_statement").toString());
    }
}
