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
                .addValue("p_input_word", email);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("getUserByEmail")
                .execute(parameterSource);

        return User.newUserBuilder()
                .setId(((BigDecimal) result.get("p_user_object_id")).longValue())
                .setEmail((String) result.get("p_user_email"))
                .setPassword((String) result.get("p_user_password"))
                .setRole(Role.valueOf((String) result.get("p_user_role")))
                .build();
    }

    /*
     * Invokes method from Patient/Doctor repository
     * (depends on user.role)
     */
    @Override
    public User save(User user) {
        return patientRepository.save((Patient) user);
    }
}
