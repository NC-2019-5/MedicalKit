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

    @Override
    public MedicineInstance edit(MedicineInstance medicineInstance) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_", medicineInstance.getSelfLife())
                .addValue("p_", medicineInstance.getAmount())
                .addValue("p_id", medicineInstance.getId());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("save")
                .returningResultSet("my_result", BeanPropertyRowMapper.newInstance(MedicineInstance.class))
                .execute(parameterSource);

        MedicineInstance medicineInstance1 = (MedicineInstance) result.get("my_result");


        return medicineInstance1;
    }

    @Override
    public MedicineInstance save(MedicineInstance medicineInstance) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_", medicineInstance.getName())
                .addValue("p_", medicineInstance.getManufacturer())
                .addValue("p_", medicineInstance.getSelfLife())
                .addValue("p_", medicineInstance.getAmount())
                .addValue("p_id", null);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("save")
                .returningResultSet("my_result", BeanPropertyRowMapper.newInstance(MedicineInstance.class))
                .execute(parameterSource);

        MedicineInstance medicineInstance1 = (MedicineInstance) result.get("my_result");


        return medicineInstance1;
    }
}
