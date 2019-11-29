package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;

import java.util.Date;

public class MedicineInstance implements Requestable {

    private Long id;
    private Medicine medicine;
    private Date selfLife;
    private int amount;

    public MedicineInstance(Long id, Medicine medicine, Date selfLife, int amount) {
        this.id = id;
        this.medicine = medicine;
        this.selfLife = selfLife;
        this.amount = amount;
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

    public Date getSelfLife() {
        return selfLife;
    }

    public void setSelfLife(Date selfLife) {
        this.selfLife = selfLife;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
