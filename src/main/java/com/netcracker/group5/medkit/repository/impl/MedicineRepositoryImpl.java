package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class MedicineRepositoryImpl implements MedicineRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public List<Medicine> findAll(int limit, long offset) {
        System.out.println("limit = " + limit);
        System.out.println("offset = " + offset);

        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_med_object_id", 5);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("getMedicineById")
                .execute(parameterSource);

        Medicine medicine = Medicine.newBuilder()
                .setId((Long) result.get("p_med_object_id"))
                .setName((String) result.get("p_med_name"))
                .setManufacturer((String) result.get("p_med_manufacturer"))
                .setProductionForm((String) result.get("p_med_prod_form"))
                .setContraindications((String) result.get("p_med_contrs"))
                .setInteractions(null)
                .setPackageContent((String) result.get("p_med_pk_content"))
                .setTakingMethod((String) result.get("p_med_taking_method"))
                .setDescription((String) result.get("p_med_description"))
                .build();

        System.out.println("result = " + result);
        System.out.println(medicine);

        return Collections.singletonList(medicine);
    }
}
