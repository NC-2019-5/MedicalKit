package com.netcracker.group5.medkit.model.dto.prescription;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class SetIsReminderEnabledRequest {

    @NotNull(message = "Prescription item id is mandatory")
    @Positive(message = "Prescription item id must be greater than 0")
    private Long prescriptionItemId;

    @NotNull(message = "Field isReminderEnabled is mandatory")
    private Boolean isReminderEnabled;

    public SetIsReminderEnabledRequest() {
    }

    public Long getPrescriptionItemId() {
        return prescriptionItemId;
    }

    public void setPrescriptionItemId(Long prescriptionItemId) {
        this.prescriptionItemId = prescriptionItemId;
    }

    public Boolean getIsReminderEnabled() {
        return isReminderEnabled;
    }

    public void setIsReminderEnabled(Boolean isReminderEnabled) {
        this.isReminderEnabled = isReminderEnabled;
    }
}
