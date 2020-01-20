package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public void bulkCreateNotifications(Long userId, List<Long> prescriptionItemIdList) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescr_item_id", SqlArray.of(prescriptionItemIdList, SqlArray.ARRAY_OF_NUMBERS))
                .addValue("p_user_object_id", userId);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("createReminders")
                .declareParameters(
                        new SqlParameter("p_prescr_item_id", OracleTypes.ARRAY, SqlArray.ARRAY_OF_NUMBERS)
                )
                .execute(parameterSource);
    }

    @Override
    public void bulkDeleteNotifications() {
        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("deleteAllReminders")
                .execute();
    }

    @Override
    public void deleteNotification(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("(p_n_object_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("deletePINotification")
                .execute(parameterSource);
    }
}
