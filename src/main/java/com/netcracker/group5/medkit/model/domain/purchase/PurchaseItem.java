package com.netcracker.group5.medkit.model.domain.purchase;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.Objects;

public class PurchaseItem {

    private Long id;
    private Medicine medicine;
    private int amount;

    public PurchaseItem(Long id, Medicine medicine, int amount) {
        this.id = id;
        this.medicine = medicine;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PurchaseItem that = (PurchaseItem) o;
        return amount == that.amount &&
                id.equals(that.id) &&
                Objects.equals(medicine, that.medicine);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, amount);
    }
}
