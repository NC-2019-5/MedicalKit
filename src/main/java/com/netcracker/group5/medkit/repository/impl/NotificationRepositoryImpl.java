package com.netcracker.group5.medkit.repository.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.domain.request.NotificationType;
import com.netcracker.group5.medkit.model.domain.user.Doctor;
import com.netcracker.group5.medkit.repository.NotificationRepository;
import com.netcracker.group5.medkit.util.SqlArray;
import com.netcracker.group5.medkit.util.SqlReturnListFromArray;
import oracle.jdbc.OracleTypes;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Repository
public class NotificationRepositoryImpl implements NotificationRepository {
    private static final Logger logger = LogManager.getLogger();
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
        logger.info("Reminders created!");

    }

    @Override
    public void bulkDeleteNotifications() {
        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("deleteAllReminders")
                .execute();
        logger.info("Reminders deleted!");
    }

    @Override
    public void deleteNotification(Long id) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_n_object_id", id);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("deletePINotification")
                .execute(parameterSource);
        logger.info("Reminder deleted!");
    }

    @Override
    public Optional<List<Notification>> findNotifications(Long patientId, long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_id", patientId)
                .addValue("p_limit", offset + limit)
                .addValue("p_offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("getAllReminders")
                .declareParameters(
                        new SqlOutParameter("p_reminder_object_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_reminder_time_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_reminder_type_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_reminder_date_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(LocalDate.class)),
                        new SqlOutParameter("p_prescription_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_medicine_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_user_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_mes_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_rn_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Integer.class)))
                .execute(parameterSource);

        List<Long> reminderIdList = (List<Long>) result.get("p_reminder_object_id_tbl");
        List<LocalDateTime> reminderTimeList = (List<LocalDateTime>) result.get("p_reminder_time_tbl");
        List<String> reminderTypeList = (List<String>) result.get("p_reminder_type_tbl");
        //List<LocalDate> reminderDateList = (List<LocalDate>) result.get("p_reminder_date_tbl");
        List<Long> prescrIdList = (List<Long>) result.get("p_prescription_id_tbl");
        List<Long> medIdList = (List<Long>) result.get("p_medicine_id_tbl");
        List<Long> userIdList = (List<Long>) result.get("p_user_id_tbl");
        List<String> messageList = (List<String>) result.get("p_mes_tbl");
        List<Notification> notificationList = new ArrayList<>();
        ListIterator<Long> iterator = reminderIdList.listIterator();

        while (iterator.hasNext()) {
            Notification notification = Notification.newBuilder().setId(reminderIdList.get(iterator.nextIndex()))
            .setRemindTime(reminderTimeList.get(iterator.nextIndex()))
            .setType(NotificationType.REMINDER)
            .setUserId(userIdList.get(iterator.nextIndex()))
            .setPrescriptionItem(prescrIdList.get(iterator.nextIndex()))
            .setMedicineInstance(medIdList.get(iterator.nextIndex()))
            .setMessage(messageList.get(iterator.nextIndex()))
            .build();
            notificationList.add(notification);
            iterator.next();
        }
        logger.info("Reminders found!");

        return Optional.of(notificationList);
    }
    public void bulkCreateMNotifications(Long userId) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_object_id", userId);

        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("createNotifications")
                .execute(parameterSource);
        logger.info("Notifications created!");
    }
    @Override
    public void bulkDeleteMNotifications() {
        new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("deleteAllNotifications")
                .execute();
        logger.info("Notifications deleted!");
    }

    @Override
    public Optional<List<Notification>> findMNotifications(Long patientId, long limit, long offset, String searchQuery) {
        SqlParameterSource parameterSource = new MapSqlParameterSource()
                .addValue("p_user_id", patientId)
                .addValue("p_limit", offset + limit)
                .addValue("p_offset", offset);

        Map<String, Object> result = new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName("NOTIFICATIONS_PKG")
                .withProcedureName("getAllNotifications")
                .declareParameters(
                        new SqlOutParameter("p_reminder_object_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_reminder_time_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_reminder_type_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_reminder_date_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_DATES, SqlReturnListFromArray.of(LocalDate.class)),
                        //new SqlOutParameter("p_prescription_id_tbl", OracleTypes.ARRAY,
                            //    SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_medicine_id_tbl", OracleTypes.ARRAY,
                           SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_user_id_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Long.class)),
                        new SqlOutParameter("p_mes_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_STRINGS, SqlReturnListFromArray.of(String.class)),
                        new SqlOutParameter("p_rn_tbl", OracleTypes.ARRAY,
                                SqlArray.ARRAY_OF_NUMBERS, SqlReturnListFromArray.of(Integer.class)))
                .execute(parameterSource);

        List<Long> reminderIdList = (List<Long>) result.get("p_reminder_object_id_tbl");
        List<LocalDateTime> reminderTimeList = (List<LocalDateTime>) result.get("p_reminder_time_tbl");
        List<String> reminderTypeList = (List<String>) result.get("p_reminder_type_tbl");
        //List<LocalDate> reminderDateList = (List<LocalDate>) result.get("p_reminder_date_tbl");
        //List<Long> prescrIdList = (List<Long>) result.get("p_prescription_id_tbl");
        List<Long> medIdList = (List<Long>) result.get("p_medicine_id_tbl");
        List<Long> userIdList = (List<Long>) result.get("p_user_id_tbl");
        List<String> messageList = (List<String>) result.get("p_mes_tbl");
        List<Notification> notificationList = new ArrayList<>();
        ListIterator<Long> iterator = reminderIdList.listIterator();

        while (iterator.hasNext()) {

            Notification notification = Notification.newBuilder().setId(reminderIdList.get(iterator.nextIndex()))
                    .setRemindTime(reminderTimeList.get(iterator.nextIndex()))
                    .setType(NotificationType.NOTIFICATION)
                    .setUserId(userIdList.get(iterator.nextIndex()))
                    .setMedicineInstance(medIdList.get(iterator.nextIndex()))
                    .setMessage(messageList.get(iterator.nextIndex()))
                    .build();
            notificationList.add(notification);
            iterator.next();
        }
        logger.info("Notifications found!");

        return Optional.of(notificationList);
    }
}
