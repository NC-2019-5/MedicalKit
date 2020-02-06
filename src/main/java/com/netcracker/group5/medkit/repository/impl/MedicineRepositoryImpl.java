package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import com.netcracker.group5.medkit.util.SqlReturnListFromArray;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

@Repository
public class MedicineRepositoryImpl implements MedicineRepository {
    private static final Logger logger = LogManager.getLogger();
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
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_dosage", OracleTypes.ARRAY,
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
        List<String> medicineDosage = (List<String>) result.get("p_medicine_dosage");

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
                    .setDosage(medicineDosage.get(iterator.nextIndex()))
                    .setId(iterator.next())
                    .build();
            medicines.add(medicine);
        }
        logger.info("Medicines found!");
        return medicines;
    }

    @Override
    public List<Medicine> findAllByParams(long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("limit", limit)
                .addValue("offset", offset)
                .addValue("searchQuery", searchQuery);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("getAllMedicineObjectsByParams")
                .declareParameters(
                        new SqlOutParameter("p_medicine_object_id", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_medicine_name", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_manufacturer", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_medicine_dosage", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class))
                ).execute(parameterSource);

        List<Long> medicineId = (List<Long>) result.get("p_medicine_object_id");
        List<String> medicineName = (List<String>) result.get("p_medicine_name");
        List<String> medicineManufacturer = (List<String>) result.get("p_medicine_manufacturer");
        List<String> medicineDosage = (List<String>) result.get("p_medicine_dosage");

        List<Medicine> medicines = new ArrayList<>(medicineId.size());
        ListIterator<Long> iterator = medicineId.listIterator();

        while (iterator.hasNext()) {
            Medicine medicine = Medicine.newBuilder()
                    .setName(medicineName.get(iterator.nextIndex()))
                    .setManufacturer(medicineManufacturer.get(iterator.nextIndex()))
                    .setDosage(medicineDosage.get(iterator.nextIndex()))
                    .setId(iterator.next())
                    .build();
            medicines.add(medicine);
        }
        logger.info("Medicines with params found!");
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
            logger.error("Medicine not found, incorrect data!");
            return null;
        }
        logger.info("Medicine found!");

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
                .setDosage(result.get("p_medicine_dosage").toString())
                .build();
    }

    @Transactional
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
                .addValue("p_medicine_description", medicine.getDescription())
                .addValue("p_medicine_dosage", medicine.getDosage());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("saveMedicineObject")
                .execute(parameterSource);
        logger.info("Medicine saved!");

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
                .setDosage(result.get("p_medicine_dosage").toString())
                .build();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_object_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_PKG")
                .withProcedureName("deleteMedicineObject")
                .execute(parameterSource);
        logger.info("Medicine deleted!");
    }

    @Override
    public Long findMedicineIdByMedicineInstanceId(long medicineId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_mi_id", medicineId);

        Map<String, Object> result;
        try {
            result = new SimpleJdbcCall(jdbcTemplate)
                    .withCatalogName("MEDICINE_PKG")
                    .withProcedureName("getMedicineIdByMIId")
                    .execute(parameterSource);
            logger.info("Medicine id find by medicine instance id!");
        } catch (DataIntegrityViolationException ex){
            logger.info("Medicine not found, incorrect data!");
            return null;
        }

        return ((BigDecimal) result.get("p_medicine_id")).longValue();
    }
}
