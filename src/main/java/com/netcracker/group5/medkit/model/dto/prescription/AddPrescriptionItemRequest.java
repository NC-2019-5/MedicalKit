package com.netcracker.group5.medkit.model.dto.prescription;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

public class AddPrescriptionItemRequest {

    @NotNull(message = "Prescription id is mandatory")
    @Positive(message = "Prescription id must be greater than 0")
    private Long prescriptionId;

    @NotNull(message = "Medicine id is mandatory")
    @Positive(message = "Medicine id must be greater than 0")
    private Long medicineId;

    @NotNull(message = "Prescription start date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;

    @NotNull(message = "Prescription end date is mandatory")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    @NotNull(message = "Prescription taking duration days can not be empty")
    @Positive(message = "Prescription taking duration days must be at least 1 day")
    private int takingDurationDays;

    @NotBlank(message = "Prescription taking time can not be empty")
    private String takingTime;

    @NotBlank(message = "Prescription description can not be empty")
    private String description;

    @NotNull(message = "Field isReminderEnabled is mandatory")
    private Boolean isReminderEnabled;

    private double dosage;

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

    public void setIsReminderEnabled(Boolean isReminderEnabled) {
        this.isReminderEnabled = isReminderEnabled;
    }

    public boolean isReminderEnabled() {
        return isReminderEnabled;
    }

    public void setReminderEnabled(boolean reminderEnabled) {
        isReminderEnabled = reminderEnabled;
    }

    public double getDosage() {
        return dosage;
    }

    public void setDosage(double dosage) {
        this.dosage = dosage;
    }
}
