package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import com.netcracker.group5.medkit.util.SqlReturnListFromArray;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
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
    public List<Medicine> findAll(long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("limit", limit)
                .addValue("offset", offset)
                .addValue("searchQuery", searchQuery);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("getAllMedicineObjects")
                .declareParameters(
                        new SqlOutParameter("p_medicine_object_id", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_medicine_name", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_manufacturer", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_prod_form", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_contrs", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_inters", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_pk_content", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_taking_method", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_description", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class))
                ).execute(parameterSource);

        List<Long> medicineId = (List<Long>) result.get("p_medicine_object_id");
        List<String> medicineName = (List<String>) result.get("p_medicine_name");
        List<String> medicineManufacturer = (List<String>) result.get("p_medicine_manufacturer");
        List<String> medicineProdForm = (List<String>) result.get("p_medicine_prod_form");
        List<String> medicineContrs = (List<String>) result.get("p_medicine_contrs");
        List<String> medicineInters = (List<String>) result.get("p_medicine_inters");
        List<String> medicinePkContent = (List<String>) result.get("p_medicine_pk_content");
        List<String> medicineTakingMethod = (List<String>) result.get("p_medicine_taking_method");
        List<String> medicineDescription = (List<String>) result.get("p_medicine_description");

        List<Medicine> medicines = new ArrayList<>(medicineId.size());
        ListIterator<Long> iterator = medicineId.listIterator();

        while (iterator.hasNext()) {
            Medicine medicine = Medicine.newBuilder()
                    .setName(medicineName.get(iterator.nextIndex()))
                    .setManufacturer(medicineManufacturer.get(iterator.nextIndex()))
                    .setProductionForm(medicineProdForm.get(iterator.nextIndex()))
                    .setContraindications(medicineContrs.get(iterator.nextIndex()))
                    .setInteractions(medicineInters.get(iterator.nextIndex()))
                    .setPackageContent(medicinePkContent.get(iterator.nextIndex()))
                    .setTakingMethod(medicineTakingMethod.get(iterator.nextIndex()))
                    .setDescription(medicineDescription.get(iterator.nextIndex()))
                    .setId(iterator.next())
                    .build();
            medicines.add(medicine);
        }
        return medicines;
    }

    @Override
    public Medicine find(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_object_id", id);

        Map<String, Object> result;

        try {
            result = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName("MEDICINE_PKG")
                    .withProcedureName("getMedicineObject")
                    .execute(parameterSource);
        } catch (DataIntegrityViolationException ex){
            return null;
        }

        return Medicine.newBuilder()
                .setId(((BigDecimal) result.get("p_medicine_object_id")).longValue())
                .setName(result.get("p_medicine_name").toString())
                .setManufacturer(result.get("p_medicine_manufacturer").toString())
                .setProductionForm(result.get("p_medicine_prod_form").toString())
                .setContraindications(result.get("p_medicine_contrs").toString())
                .setInteractions(result.get("p_medicine_inters").toString())
                .setPackageContent(result.get("p_medicine_pk_content").toString())
                .setTakingMethod(result.get("p_medicine_taking_method").toString())
                .setDescription(result.get("p_medicine_description").toString())
                .build();
    }

    @Override
    public Medicine save(Medicine medicine) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_object_id", medicine.getId())
                .addValue("p_medicine_name", medicine.getName())
                .addValue("p_medicine_manufacturer", medicine.getManufacturer())
                .addValue("p_medicine_prod_form", medicine.getProductionForm())
                .addValue("p_medicine_contrs", medicine.getContraindications())
                .addValue("p_medicine_inters", medicine.getInteractions())
                .addValue("p_medicine_pk_content", medicine.getPackageContent())
                .addValue("p_medicine_taking_method", medicine.getTakingMethod())
                .addValue("p_medicine_description", medicine.getDescription());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("saveMedicineObject")
                .execute(parameterSource);

        return Medicine.newBuilder()
                .setId(((BigDecimal) result.get("p_medicine_object_id")).longValue())
                .setName(result.get("p_medicine_name").toString())
                .setManufacturer(result.get("p_medicine_manufacturer").toString())
                .setProductionForm(result.get("p_medicine_prod_form").toString())
                .setContraindications(result.get("p_medicine_contrs").toString())
                .setInteractions(result.get("p_medicine_inters").toString())
                .setPackageContent(result.get("p_medicine_pk_content").toString())
                .setTakingMethod(result.get("p_medicine_taking_method").toString())
                .setDescription(result.get("p_medicine_description").toString())
                .build();
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_object_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("deleteMedicineObject")
                .execute(parameterSource);
    }
}
