package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.logging.Logger;

@Repository
public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository {

    @Autowired
    public void setDs(DataSource dataSource) {
        setDataSource(dataSource);
    }

    @Override
    @Transactional
    public User findUserByEmail(String email) {
        assert getJdbcTemplate() != null;

        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(getJdbcTemplate());
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", email);

        String query = "SELECT id, email, password ,role FROM AppUser WHERE email = :email";

        final SqlRowSet rowSet = namedParameterJdbcTemplate.queryForRowSet(query, params);

        while (rowSet.next()) {
            Logger.getAnonymousLogger().info(String.valueOf(rowSet.getLong(1)));
            Logger.getAnonymousLogger().info(String.valueOf(rowSet.getString(2)));
            Logger.getAnonymousLogger().info(String.valueOf(rowSet.getString(3)));
            Logger.getAnonymousLogger().info(String.valueOf(rowSet.getString(4)));
        }

        rowSet.beforeFirst();

        if (rowSet.next()) {
            return User.newUserBuilder()
                    .setId(rowSet.getLong(1))
                    .setEmail(rowSet.getString(2))
                    .setPassword(rowSet.getString(3))
                    .setRole(Role.valueOf(rowSet.getString(4)))
                    .build();
        }

        return null;
    }
}
