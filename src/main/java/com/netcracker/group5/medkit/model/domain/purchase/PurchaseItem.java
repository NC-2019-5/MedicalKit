package com.netcracker.group5.medkit.model.domain.purchase;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;


public class PurchaseItem {

    private long id;
    private Medicine medicine;
    private int amount;

    public PurchaseItem(long id, Medicine medicine, int amount) {
        this.id = id;
        this.medicine = medicine;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
