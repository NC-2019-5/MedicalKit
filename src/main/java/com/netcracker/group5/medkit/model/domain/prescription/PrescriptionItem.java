package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.time.LocalDateTime;
import java.util.Objects;

public class PrescriptionItem implements Requestable {

    private Long id;
    private Medicine medicine;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int takingDurationDays;
    private LocalDateTime takingTime;
    private String description;

    public PrescriptionItem(Long id, Medicine medicine, LocalDateTime startDate, LocalDateTime endDate, int takingDurationDays, LocalDateTime takingTime, String description) {
        this.id = id;
        this.medicine = medicine;
        this.startDate = startDate;
        this.endDate = endDate;
        this.takingDurationDays = takingDurationDays;
        this.takingTime = takingTime;
        this.description = description;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public int getTakingDurationDays() {
        return takingDurationDays;
    }

    public void setTakingDurationDays(int takingDurationDays) {
        this.takingDurationDays = takingDurationDays;
    }

    public LocalDateTime getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(LocalDateTime takingTime) {
        this.takingTime = takingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrescriptionItem that = (PrescriptionItem) o;
        return takingDurationDays == that.takingDurationDays &&
                id.equals(that.id) &&
                Objects.equals(medicine, that.medicine) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(takingTime, that.takingTime) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, startDate, endDate, takingDurationDays, takingTime, description);
    }
}
