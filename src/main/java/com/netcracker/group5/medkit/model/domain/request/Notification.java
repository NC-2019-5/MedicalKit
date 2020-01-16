package com.netcracker.group5.medkit.model.domain.request;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;

public class Notification {

    private Long id;
    private Long userId;
    private NotificationType type;
    private String remindTime;
    private MedicineInstance medicineInstance;
    private PrescriptionItem prescriptionItem;
    private String message;

    private Notification() {
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

    public static Builder newInstance() {
        return new Notification().new Builder();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            Notification.this.id = id;
            return this;
        }

        public Builder setUserId(Long userId) {
            Notification.this.userId = userId;
            return this;
        }

        public Builder setType(NotificationType type) {
            Notification.this.type = type;
            return this;
        }

        public Builder setRemindTime(String remindTime) {
            Notification.this.remindTime = remindTime;
            return this;
        }

        public Builder setMedicineInstance(MedicineInstance medicineInstance) {
            Notification.this.medicineInstance = medicineInstance;
            return this;
        }

        public Builder setPrescriptionItem(PrescriptionItem purchaseItem) {
            Notification.this.prescriptionItem = purchaseItem;
            return this;
        }

        public Builder setMessage(String message) {
            Notification.this.message = message;
            return this;
        }

        public Notification build() {
            return Notification.this;
        }
    }
}
