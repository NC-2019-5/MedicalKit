package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.repository.PrescriptionRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import com.netcracker.group5.medkit.util.SqlReturnListFromArray;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.time.LocalDate;
import java.util.*;

@Repository
public class PrescriptionRepositoryImpl implements PrescriptionRepository {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    private void postConstruct() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Override
    public Optional<List<Prescription>> findPrescriptionsByPatientId(Long patientId, long limit, long offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId)
                .addValue("p_limit", limit)
                .addValue("p_offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("getAllPrescriptions")
                .declareParameters(
                        new SqlOutParameter("p_prescription_object_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_prescription_name_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_prescription_date_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(LocalDate.class)),
                        new SqlOutParameter("p_prescription_doctor_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_prescription_doctor_name_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_prescription_doctor_sn_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_rn_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Integer.class)))
                .execute(parameterSource);

        List<Long> prescriptionIdList = (List<Long>) result.get("p_prescription_object_id_tbl");
        List<String> prescriptionNameList = (List<String>) result.get("p_prescription_name_tbl");
        List<LocalDate> prescriptionDateList = (List<LocalDate>) result.get("p_prescription_date_tbl");
        List<Long> doctorIdList = (List<Long>) result.get("p_prescription_doctor_id_tbl");
        List<String> doctorNameList = (List<String>) result.get("p_prescription_doctor_name_tbl");
        List<String> doctorSurnameList = (List<String>) result.get("p_prescription_doctor_sn_tbl");

        List<Prescription> prescriptionList = new ArrayList<>();
        ListIterator<Long> iterator = prescriptionIdList.listIterator();

        while (iterator.hasNext()) {
            Doctor doctor = Doctor.newBuilder()
                    .setId(doctorIdList.get(iterator.nextIndex()))
                    .setName(doctorNameList.get(iterator.nextIndex()))
                    .setSurname(doctorSurnameList.get(iterator.nextIndex()))
                    .build();

            Prescription prescription = Prescription.newBuilder()
                    .setId(prescriptionIdList.get(iterator.nextIndex()))
                    .setName(prescriptionNameList.get(iterator.nextIndex()))
                    .setDate(prescriptionDateList.get(iterator.nextIndex()))
                    .setDoctor(doctor)
                    .build();

            prescriptionList.add(prescription);
            iterator.next();
        }
        logger.info("Prescriptions found!");

        return Optional.of(prescriptionList);
    }

    @Transactional
    @Override
    public Prescription save(Long patientId, Prescription prescription) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_name", prescription.getName())
                .addValue("p_prescription_date", prescription.getDate())
                .addValue("p_prescription_doctor_id", prescription.getDoctor().getId())
                .addValue("p_prescription_patient_id", patientId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("savePrescriptionObject")
                .execute(parameterSource);

        Prescription prescriptionResult = Prescription.newBuilder()
                .setId(((BigDecimal) result.get("p_prescription_object_id")).longValue())
                .setName((String) result.get("p_prescription_name"))
                .setDate(((Timestamp) result.get("p_prescription_date")).toLocalDateTime().toLocalDate())
                .setDoctor(Doctor.newBuilder()
                        .setId(Long.parseLong((String) result.get("p_prescription_doctor_id")))
                        .build())
                .build();
        logger.info("Prescription saved!");
        return prescriptionResult;
    }

    @Transactional
    @Override
    public void deletePrescriptionWithItems(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_object_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("deletePrescriptionAndItems")
                .execute(parameterSource);
        logger.info("Prescription with items deleted!");
    }

    @Override
    public Optional<List<PrescriptionItem>> findPrescriptionItemsByPrescriptionId(Long prescriptionId, long limit, long offset) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_id", prescriptionId)
                .addValue("p_limit", limit)
                .addValue("p_offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("getAllPrescriptionItems")
                .declareParameters(
                        new SqlOutParameter("p_pi_object_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_pi_medicine_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_pi_medicine_name_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_pi_start_date_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(LocalDate.class)),
                        new SqlOutParameter("p_pi_end_date_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(LocalDate.class)),
                        new SqlOutParameter("p_pi_taking_time_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_pi_description_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_pi_is_reminder_enabled_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_pi_dosage_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_rn_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Integer.class)))
                .execute(parameterSource);

        List<Long> prescriptionItemIdList = (List<Long>) result.get("p_pi_object_id_tbl");
        List<Long> medicineIdList = (List<Long>) result.get("p_pi_medicine_id_tbl");
        List<String> medicineNameList = (List<String>) result.get("p_pi_medicine_name_tbl");
        List<LocalDate> startDateList = (List<LocalDate>) result.get("p_pi_start_date_tbl");
        List<LocalDate> endDateList = (List<LocalDate>) result.get("p_pi_end_date_tbl");
        List<String> takingTimeList = (List<String>) result.get("p_pi_taking_time_tbl");
        List<String> descriptionList = (List<String>) result.get("p_pi_description_tbl");
        List<String> isReminderEnabledList = (List<String>) result.get("p_pi_is_reminder_enabled_tbl");
        List<String> dosageList = (List<String>) result.get("p_pi_dosage_tbl");

        List<PrescriptionItem> prescriptionItemList = new ArrayList<>();
        ListIterator<Long> iterator = prescriptionItemIdList.listIterator();

        while (iterator.hasNext()) {
            Medicine medicine = Medicine.newBuilder()
                    .setId(medicineIdList.get(iterator.nextIndex()))
                    .setName(medicineNameList.get(iterator.nextIndex()))
                    .build();

            Prescription prescription = Prescription.newBuilder()
                    .setId(prescriptionId)
                    .build();

            PrescriptionItem prescriptionItem = PrescriptionItem.newBuilder()
                    .setId(prescriptionItemIdList.get(iterator.nextIndex()))
                    .setMedicine(medicine)
                    .setStartDate(startDateList.get(iterator.nextIndex()))
                    .setEndDate(endDateList.get(iterator.nextIndex()))
                    .setTakingTime(takingTimeList.get(iterator.nextIndex()))
                    .setDescription(descriptionList.get(iterator.nextIndex()))
                    .setPrescription(prescription)
                    .setIsReminderEnabled(Boolean.parseBoolean(isReminderEnabledList.get(iterator.nextIndex())))
                    .setDosage(Double.parseDouble(dosageList.get(iterator.nextIndex())))
                    .build();

            prescriptionItemList.add(prescriptionItem);
            iterator.next();
        }
        logger.info("Prescription items found!");
        return Optional.of(prescriptionItemList);
    }

    @Transactional
    @Override
    public PrescriptionItem savePrescriptionItem(PrescriptionItem prescriptionItem) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_object_id", prescriptionItem.getPrescription().getId())
                .addValue("p_pi_medicine_id", prescriptionItem.getMedicine().getId())
                .addValue("p_pi_start_date", prescriptionItem.getStartDate())
                .addValue("p_pi_end_date", prescriptionItem.getEndDate())
                .addValue("p_pi_taking_time", prescriptionItem.getTakingTime())
                .addValue("p_pi_description", prescriptionItem.getDescription())
                .addValue("p_pi_is_reminder_enabled", prescriptionItem.getIsReminderEnabled())
                .addValue("p_pi_dosage", prescriptionItem.getDosage());

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("savePrescriptionItemObject")
                .execute(parameterSource);

        PrescriptionItem prescriptionItemResult = PrescriptionItem.newBuilder()
                .setId(((BigDecimal) result.get("p_pi_object_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(Long.parseLong((String) result.get("p_pi_medicine_id")))
                        .setName((String) result.get("p_pi_medicine_name"))
                        .build())
                .setPrescription(Prescription.newBuilder()
                        .setId(Long.parseLong((String) result.get("p_prescription_object_id")))
                        .build())
                .setStartDate(((Timestamp) result.get("p_pi_start_date")).toLocalDateTime().toLocalDate())
                .setEndDate(((Timestamp) result.get("p_pi_end_date")).toLocalDateTime().toLocalDate())
                .setTakingTime((String) result.get("p_pi_taking_time"))
                .setDescription((String) result.get("p_pi_description"))
                .setIsReminderEnabled(Boolean.parseBoolean((String) result.get("p_pi_is_reminder_enabled")))
                .setDosage(Double.parseDouble((String) result.get("p_pi_dosage")))
                .build();
        logger.info("Prescription item saved!");
        return prescriptionItemResult;
    }

    @Override
    public Optional<List<Long>> findActivePrescriptionItems(Long patientId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_patient_id", patientId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("getAllActivePrescriptionItems")
                .declareParameters(
                        new SqlOutParameter("p_pi_object_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)))
                .execute(parameterSource);

        List<Long> prescriptionItemIdList = (List<Long>) result.get("p_pi_object_id_tbl");
        logger.info("Active prescription items found!");
        return Optional.of(prescriptionItemIdList);
    }

    @Override
    public PrescriptionItem findPrescriptionItem(Long prescriptionItemId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_pi_object_id", prescriptionItemId);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("getPrescriptionItemById")
                .execute(parameterSource);
        logger.info("Prescription item found!");

        return PrescriptionItem.newBuilder()
                .setId(((BigDecimal) result.get("p_pi_object_id")).longValue())
                .setMedicine(Medicine.newBuilder()
                        .setId(Long.parseLong((String) result.get("p_pi_medicine_id")))
                        .setName((String) result.get("p_pi_medicine_name"))
                        .build())
                .setStartDate(((Timestamp) result.get("p_pi_start_date")).toLocalDateTime().toLocalDate())
                .setEndDate(((Timestamp) result.get("p_pi_end_date")).toLocalDateTime().toLocalDate())
                .setTakingTime((String) result.get("p_pi_taking_time"))
                .setDescription((String) result.get("p_pi_description"))
                .setIsReminderEnabled(Boolean.parseBoolean((String) result.get("p_pi_is_reminder_enabled")))
                .setDosage(Double.parseDouble((String) result.get("p_pi_dosage")))
                .build();
    }

    @Transactional
    @Override
    public void deletePrescriptionItem(Long prescriptionItemId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_item_id", prescriptionItemId);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("deletePrescriptionItem")
                .execute(parameterSource);
    }

    @Transactional
    @Override
    public void setIsReminderEnabled(Long prescriptionItemId, Boolean isReminderEnabled) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_prescription_item_id", prescriptionItemId)
                .addValue("p_is_reminder_enabled", isReminderEnabled);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("PRESCRIPTION_PKG")
                .withProcedureName("setIsReminderEnabled")
                .execute(parameterSource);
    }
}
