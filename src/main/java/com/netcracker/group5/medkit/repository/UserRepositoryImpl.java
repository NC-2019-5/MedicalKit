package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

@Repository
public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        String query = "SELECT id, email, password ,role FROM AppUser WHERE email = ?";
        Object[] params = new Object[]{email};

        return jdbcTemplate.queryForObject(query, params, (resultSet, i) -> User.newUserBuilder()
                .setId(resultSet.getLong("id"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setRole(Role.valueOf(resultSet.getString("role")))
                .build());
    }

    @Override
    public User savePatient(User user) {

        return null;
    }
}
