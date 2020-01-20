package com.netcracker.group5.medkit.model.dto.request;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.request.NotificationType;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class NotificationRequestItem {

    @NotNull(message = "Notification id is mandatory")
    @Positive(message = "Notification id must be greater than 0")
    private Long id;
    private Long userId;
    private NotificationType type;
    private String remindTime;
    private MedicineInstance medicineInstance;
    private PrescriptionItem prescriptionItem;
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

    public MedicineInstance getMedicineInstance() {
        return medicineInstance;
    }

    public void setMedicineInstance(MedicineInstance medicineInstance) {
        this.medicineInstance = medicineInstance;
    }

    public PrescriptionItem getPrescriptionItem() {
        return prescriptionItem;
    }

    public void setPrescriptionItem(PrescriptionItem prescriptionItem) {
        this.prescriptionItem = prescriptionItem;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
