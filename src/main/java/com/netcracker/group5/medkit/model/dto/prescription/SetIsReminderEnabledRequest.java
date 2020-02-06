package com.netcracker.group5.medkit.model.dto.prescription;

public class SetIsReminderEnabledRequest {

    private Long prescriptionItemId;
    private Boolean isReminderEnabled;

    public Long getPrescriptionItemId() {
        return prescriptionItemId;
    }

    public void setPrescriptionItemId(Long prescriptionItemId) {
        this.prescriptionItemId = prescriptionItemId;
    }

    public Boolean getIsReminderEnabled() {
        return isReminderEnabled;
    }

    public void setIsReminderEnabled(Boolean reminderEnabled) {
        isReminderEnabled = reminderEnabled;
    }
}
