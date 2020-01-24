package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.purchase.PurchaseItem;
import com.netcracker.group5.medkit.repository.MedicineInstanceRepository;
import oracle.jdbc.OracleTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.*;

@Repository
public class MedicineInstanceRepositoryImpl implements MedicineInstanceRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<List<MedicineInstance>> findMedicineInstances(Long patientId, long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId)
                .addValue("limit", offset + limit)
                .addValue("offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("getMedicineInstances")
                .declareParameters(
                        new SqlOutParameter("p_medicine_instance_id_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(4);
                                    BigDecimal[] bigDecIds = (BigDecimal[]) sqlArray.getArray();
                                    List<Long> idList = new ArrayList<>();

                                    for (BigDecimal bigDecId : bigDecIds) {
                                        idList.add(bigDecId.longValue());
                                    }

                                    return idList;
                                }),
                        new SqlOutParameter("p_medicine_id_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(5);
                                    BigDecimal[] bigDecMedicineIds = (BigDecimal[]) sqlArray.getArray();
                                    List<Long> medicineIdList = new ArrayList<>();

                                    for (BigDecimal bigDecMedicineId : bigDecMedicineIds) {
                                        medicineIdList.add(bigDecMedicineId.longValue());
                                    }

                                    return medicineIdList;
                                }),
                        new SqlOutParameter("p_self_life_array", OracleTypes.ARRAY, "ARRAY_OF_DATES",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(6);
                                    Timestamp[] timestamps = (Timestamp[]) sqlArray.getArray();
                                    List<LocalDate> localDates = new ArrayList<>();

                                    for(Timestamp timestamp: timestamps){
                                        localDates.add(timestamp.toLocalDateTime().toLocalDate());
                                    }

                                    return localDates;
                                }),
                        new SqlOutParameter("p_amount_array", OracleTypes.ARRAY, "ARRAY_OF_NUMBERS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(7);
                                    BigDecimal[] bigDecAmounts = (BigDecimal[]) sqlArray.getArray();
                                    List<Double> amountList = new ArrayList<>();

                                    for (BigDecimal bigDecAmount : bigDecAmounts) {
                                        amountList.add(bigDecAmount.doubleValue());
                                    }

                                    return amountList;
                                }),
                        new SqlOutParameter("p_medicine_names", OracleTypes.ARRAY, "ARRAY_OF_STRINGS",
                                ((callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(8);
                                    String[] medicineNames = (String[]) sqlArray.getArray();

                                    return Arrays.asList(medicineNames);
                                })),
                        new SqlOutParameter("p_medicine_manufacturers", OracleTypes.ARRAY, "ARRAY_OF_STRINGS",
                                (callableStatement, i, i1, s) -> {
                                    Array sqlArray = callableStatement.getArray(9);
                                    String[] medicineManufacturers = (String[]) sqlArray.getArray();

                                    return Arrays.asList(medicineManufacturers);
                                }))
                .execute(parameterSource);

        List<Long> idList = (List<Long>) result.get("p_medicine_instance_id_array");
        List<Long> medicineIdList = (List<Long>) result.get("p_medicine_id_array");
        List<LocalDate> datesList = (List<LocalDate>) result.get("p_self_life_array");
        List<Double> amountList = (List<Double>) result.get("p_amount_array");
        List<String> medicineNames = (List<String>) result.get("p_medicine_names");
        List<String> medicineManufacturers = (List<String>) result.get("p_medicine_manufacturers");

        List<MedicineInstance> medicineInstances = new ArrayList<>();
        ListIterator<Long> iterator = idList.listIterator();

        while (iterator.hasNext()) {
            Medicine medicine = Medicine.newBuilder()
                    .setId(medicineIdList.get(iterator.nextIndex()))
                    .setName(medicineNames.get(iterator.nextIndex()))
                    .setManufacturer(medicineManufacturers.get(iterator.nextIndex()))
                    .build();

            MedicineInstance medicineInstance = MedicineInstance.newBuilder()
                    .setSelfLife(datesList.get(iterator.nextIndex()))
                    .setAmount(amountList.get(iterator.nextIndex()))
                    .setId(iterator.next())
                    .setMedicine(medicine)
                    .build();

            medicineInstances.add(medicineInstance);
        }

        return Optional.of(medicineInstances);
    }

    @Override
    public MedicineInstance findMedicineInstance(Long patientId, Long medicineInstanceId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId)
                .addValue("p_medicine_instance_id", medicineInstanceId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("getMedicineInstance")
                .execute(parameterSource);

        return MedicineInstance.newBuilder()
                .setId(((BigDecimal) result.get("p_medicine_instance_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(((BigDecimal) result.get("p_medicine_id")).longValue())
                        .build())
                .setSelfLife(((Timestamp) result.get("p_self_life")).toLocalDateTime().toLocalDate())
                .setAmount(((BigDecimal) result.get("p_amount")).doubleValue())
                .build();
    }

    @Override
    public MedicineInstance save(Long patientId, MedicineInstance medicineInstance) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_instance_id", medicineInstance.getId())
                .addValue("p_medicine_id", medicineInstance.getMedicine().getId())
                .addValue("p_self_life", medicineInstance.getSelfLife())
                .addValue("p_amount", medicineInstance.getAmount())
                .addValue("p_patient_id", patientId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("saveMedicineInstance")
                .execute(parameterSource);

        return MedicineInstance.newBuilder()
                .setId(((BigDecimal) result.get("p_medicine_instance_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(((BigDecimal) result.get("p_medicine_id")).longValue())
                        .build())
                .setSelfLife(((Timestamp) result.get("p_self_life")).toLocalDateTime().toLocalDate())
                .setAmount(((BigDecimal) result.get("p_amount")).doubleValue())
                .build();
    }

    @Override
    public void delete(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_medicine_instance_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("MEDICINE_INSTANCE_PKG")
                .withProcedureName("deleteMedicineInstance")
                .execute(parameterSource);
    }
}
