package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.prescription.AddPrescriptionItemRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class PrescriptionItem implements Requestable {

    private Long id;
    private Medicine medicine;
    private LocalDate startDate;
    private LocalDate endDate;
    private int takingDurationDays;
    private String takingTime;
    private String description;
    private Prescription prescription;
    private boolean isReminderEnabled;

    public PrescriptionItem() {
    }

    public PrescriptionItem(AddPrescriptionItemRequest addPrescriptionItemRequest) {
        this.medicine = Medicine.newBuilder()
                .setId(addPrescriptionItemRequest.getMedicineId())
                .build();
        this.startDate = addPrescriptionItemRequest.getStartDate();
        this.endDate = addPrescriptionItemRequest.getEndDate();
        this.takingDurationDays = addPrescriptionItemRequest.getTakingDurationDays();
        this.takingTime = addPrescriptionItemRequest.getTakingTime();
        this.description = addPrescriptionItemRequest.getDescription();
        this.prescription = Prescription.newBuilder()
                .setId(addPrescriptionItemRequest.getPrescriptionId())
                .build();
        this.isReminderEnabled = addPrescriptionItemRequest.getIsReminderEnabled();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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

    public Prescription getPrescription() {
        return prescription;
    }

    public void setPrescription(Prescription prescription) {
        this.prescription = prescription;
    }

    public boolean getIsReminderEnabled() {
        return isReminderEnabled;
    }

    public void setIsReminderEnabled(boolean reminderEnabled) {
        isReminderEnabled = reminderEnabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionItem that = (PrescriptionItem) o;
        return takingDurationDays == that.takingDurationDays &&
                isReminderEnabled == that.isReminderEnabled &&
                id.equals(that.id) &&
                medicine.equals(that.medicine) &&
                startDate.equals(that.startDate) &&
                endDate.equals(that.endDate) &&
                takingTime.equals(that.takingTime) &&
                description.equals(that.description) &&
                prescription.equals(that.prescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, startDate, endDate, takingDurationDays, takingTime, description, prescription, isReminderEnabled);
    }

    @Override
    public String toString() {
        return "PrescriptionItem{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", takingDurationDays=" + takingDurationDays +
                ", takingTime='" + takingTime + '\'' +
                ", description='" + description + '\'' +
                ", prescription=" + prescription +
                ", isReminderEnabled=" + isReminderEnabled +
                '}';
    }

    public static Builder newBuilder() {
        return new PrescriptionItem().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            PrescriptionItem.this.id = id;
            return this;
        }

        public Builder setMedicine(Medicine medicine) {
            PrescriptionItem.this.medicine = medicine;
            return this;
        }

        public Builder setStartDate(LocalDate startDate) {
            PrescriptionItem.this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            PrescriptionItem.this.endDate = endDate;
            return this;
        }

        public Builder setTakingDurationDays(int takingDurationDays) {
            PrescriptionItem.this.takingDurationDays = takingDurationDays;
            return this;
        }

        public Builder setTakingTime(String takingTime) {
            PrescriptionItem.this.takingTime = takingTime;
            return this;
        }

        public Builder setDescription(String description) {
            PrescriptionItem.this.description = description;
            return this;
        }

        public Builder setPrescription(Prescription prescription) {
            PrescriptionItem.this.prescription = prescription;
            return this;
        }

        public Builder setIsReminderEnabled(boolean isReminderEnabled){
            PrescriptionItem.this.isReminderEnabled = isReminderEnabled;
            return this;
        }

        public PrescriptionItem build() {
            return PrescriptionItem.this;
        }
    }
}
