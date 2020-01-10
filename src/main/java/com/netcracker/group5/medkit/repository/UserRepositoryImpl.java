package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.Map;

@Repository
public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        setDataSource(dataSource);
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public User findUserById(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_object_id", id);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("getUserObject")
                .execute(parameterSource);

        return User.newUserBuilder()
                .setId(((BigDecimal) result.get("p_user_object_id")).longValue())
                .setEmail(result.get("p_user_email").toString())
                .setPassword(result.get("p_user_password").toString())
                .setRole(Role.getRoleByName((String) result.get("p_user_role")))
                .build();
    }

    @Override
    public User findUserByEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_email", email);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("getUserByEmail")
                .execute(parameterSource);

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
            User savedUser = saveUser(user);

            Patient patientToBeSaved = patientRepository.findByUserId(savedUser.getId());
            Patient editedPatient = (Patient) user;

            editedPatient.setPassword(savedUser.getPassword());
            editedPatient.setRole(savedUser.getRole());
            editedPatient.setId(patientToBeSaved.getId());
            editedPatient.setSex(patientToBeSaved.getSex());

            Patient savedPatient = patientRepository.save(savedUser.getId(), (Patient) user);

            savedPatient.setEmail(savedUser.getEmail());
            savedPatient.setPassword(savedUser.getPassword());
            savedPatient.setRole(savedUser.getRole());

            return savedPatient;
        }

        throw new IllegalArgumentException("User role" + user.getRole() + " does not exist");
    }

    public User saveUser(User user) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_object_id", user.getId())
                .addValue("p_user_email", user.getEmail())
                .addValue("p_user_password", user.getPassword())
                .addValue("p_user_role", user.getRole().getRoleName());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("saveUserObject")
                .execute(parameterSource);

        return User.newUserBuilder()
                .setId(((BigDecimal) result.get("p_user_object_id")).longValue())
                .setEmail(result.get("p_user_email").toString())
                .setPassword(result.get("p_user_password").toString())
                .setRole(Role.getRoleByName((String) result.get("p_user_role")))
                .build();
    }

    @Override
    public boolean isExistUserWithEmail(String email) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_email", email);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("isExistUserWithEmail")
                .execute(parameterSource);

        return Boolean.parseBoolean(result.get("p_return_statement").toString());
    }
}
