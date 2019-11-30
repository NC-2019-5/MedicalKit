package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;

import java.time.LocalDateTime;
import java.util.Objects;

public class MedicineInstance implements Requestable {

    private Long id;
    private Medicine medicine;
    private LocalDateTime selfLife;
    private int amount;

    public MedicineInstance(Long id, Medicine medicine, LocalDateTime selfLife, int amount) {
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

    public LocalDateTime getSelfLife() {
        return selfLife;
    }

    public void setSelfLife(LocalDateTime selfLife) {
        this.selfLife = selfLife;
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
        MedicineInstance that = (MedicineInstance) o;
        return amount == that.amount &&
                id.equals(that.id) &&
                medicine.equals(that.medicine) &&
                Objects.equals(selfLife, that.selfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, selfLife, amount);
    }
}
