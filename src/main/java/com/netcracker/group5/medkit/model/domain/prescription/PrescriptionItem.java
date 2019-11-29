package com.netcracker.group5.medkit.model.domain.prescription;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.Date;

public class PrescriptionItem implements Requestable {

    private Long id;
    private Medicine medicine;
    private Date startDate;
    private Date endDate;
    private int takingDurationDays;
    private Date takingTime;
    private String description;

    public PrescriptionItem(Long id, Medicine medicine, Date startDate, Date endDate, int takingDurationDays, Date takingTime, String description) {
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getTakingDurationDays() {
        return takingDurationDays;
    }

    public void setTakingDurationDays(int takingDurationDays) {
        this.takingDurationDays = takingDurationDays;
    }

    public Date getTakingTime() {
        return takingTime;
    }

    public void setTakingTime(Date takingTime) {
        this.takingTime = takingTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
