package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MedicineInstanceRepositoryImpl implements MedicineInstanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct(){
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<List<MedicineInstance>> findMedicineInstances(int limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("limit", limit)
                .addValue("offset", offset)
                .addValue("p_input_word", searchQuery);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("USER_PKG")
                .withProcedureName("getUserByEmail")
                .returningResultSet("my_result", BeanPropertyRowMapper.newInstance(MedicineInstance.class))
                .execute(parameterSource);

        List<MedicineInstance> medicineInstances = (List<MedicineInstance>) result.get("my_result");

        return Optional.ofNullable(medicineInstances);
    }
}
