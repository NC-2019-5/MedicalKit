package com.netcracker.group5.medkit.model.dto.medicine;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Sex;

import java.time.LocalDate;

public class GetMedicineInstanceResponseItem {

    private Long id;
    private Medicine medicine;
    private LocalDate selfLife;
    private double amount;

    public GetMedicineInstanceResponseItem() {
    }

    public GetMedicineInstanceResponseItem(MedicineInstance medicineInstance) {
        this.id = medicineInstance.getId();
        this.medicine = medicineInstance.getMedicine();
        this.selfLife = medicineInstance.getSelfLife();
        this.amount = medicineInstance.getAmount();
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

    public LocalDate getSelfLife() {
        return selfLife;
    }

    public void setSelfLife(LocalDate selfLife) {
        this.selfLife = selfLife;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
