package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class MedicineRepositoryImpl implements MedicineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Medicine findAll(Long id) {
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("getMedicineById");

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_med_object_id", id);

        Map<String, Object> result = simpleJdbcCall.execute(parameterSource);

        Medicine medicine = Medicine.newBuilder()
                .setId((Long) result.get("p_med_object_id"))
                .setName((String) result.get("p_med_name"))
                .build();

        System.out.println("result = " + result);
        System.out.println(medicine);
        return null;
    }
}
