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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class MedicineInstanceRepositoryImpl implements MedicineInstanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
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
    public MedicineInstance save(MedicineInstance medicineInstance) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_n", medicineInstance.getName())
                .addValue("p_m", medicineInstance.getManufacturer())
                .addValue("p_s", medicineInstance.getSelfLife())
                .addValue("p_a", medicineInstance.getAmount())
                .addValue("p_id", medicineInstance.getId());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("save")
                .execute(parameterSource);

        MedicineInstance medicineInstanceDB = MedicineInstance.newBuilder()
                .setId(((BigDecimal) result.get("p_id")).longValue())
                .setName(result.get("p_n").toString())
                .setManufacturer(result.get("p_m").toString())
                .setSelfLife((LocalDate) result.get("p_s"))
                .setAmount((int) result.get("p_a"))
                .build();


        return medicineInstanceDB;
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_id", id);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("delete")
                .execute(parameterSource);
    }
}
