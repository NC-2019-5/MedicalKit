package com.netcracker.group5.medkit.model.dto.request;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.request.NotificationType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NotificationRequestItem {

    @NotNull(message = "Notification id is mandatory")
    @Positive(message = "Notification id must be greater than 0")
    private Long id;

    @NotNull(message = "User id is mandatory")
    @Positive(message = "User id must be greater than 0")
    private Long userId;

    @NotBlank(message = "Notification type can not be empty")
    private NotificationType type;

    @NotBlank(message = "Notification remind time can not be empty")
    private String remindTime;

    @NotBlank(message = "Medicine instance in notification can not be empty")
    private Long medicineInstanceId;

    @NotBlank(message = "Prescription item in notification can not be empty")
    private Long prescriptionItemId;

    @NotBlank(message = "Notification message can not be empty")
    private String message;

    public NotificationRequestItem(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public Long getMedicineInstanceId() {
        return medicineInstanceId;
    }

    public void setMedicineInstanceId(Long medicineInstanceId) {
        this.medicineInstanceId = medicineInstanceId;
    }

    public Long getPrescriptionItemId() {
        return prescriptionItemId;
    }

    public void setPrescriptionItemId(Long prescriptionItemId) {
        this.prescriptionItemId = prescriptionItemId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
