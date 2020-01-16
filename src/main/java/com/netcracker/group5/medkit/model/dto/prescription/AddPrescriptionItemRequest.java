package com.netcracker.group5.medkit.model.dto.prescription;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;

import java.time.LocalDate;

public class AddPrescriptionItemRequest {

    private Long prescriptionId;
    private Long medicineId;
    private LocalDate startDate;
    private LocalDate endDate;
    private int takingDurationDays;
    private String takingTime;
    private String description;
    private boolean isReminderEnabled;

    public AddPrescriptionItemRequest(){

    }

    public Long getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Long prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getTakingDurationDays() {
        return takingDurationDays;
    }

    public void setTakingDurationDays(int takingDurationDays) {
        this.takingDurationDays = takingDurationDays;
    }

    public String getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(String takingTime) {
        this.takingTime = takingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsReminderEnabled() {
        return isReminderEnabled;
    }

    public void setIsReminderEnabled(boolean isReminderEnabled) {
        this.isReminderEnabled = isReminderEnabled;
    }
}
