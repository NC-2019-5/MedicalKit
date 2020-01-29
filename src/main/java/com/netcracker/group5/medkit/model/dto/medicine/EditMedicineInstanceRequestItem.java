package com.netcracker.group5.medkit.model.dto.medicine;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;

public class EditMedicineInstanceRequestItem {

    @NotNull(message = "Medicine instance id is mandatory")
    @Positive(message = "Medicine instance id must be greater than 0")
    private long id;

    @NotNull(message = "Medicine is mandatory")
    private Medicine medicine;

    @NotNull(message = "Medicine self life is mandatory")
    private LocalDate selfLife;

    @NotNull(message = "Medicine amount is mandatory")
    @PositiveOrZero(message = "Medicine amount must be greater than 0")
    private double amount;

    public EditMedicineInstanceRequestItem() {
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
