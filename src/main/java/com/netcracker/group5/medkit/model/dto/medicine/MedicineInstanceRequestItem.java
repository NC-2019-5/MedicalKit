package com.netcracker.group5.medkit.model.dto.medicine;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class MedicineInstanceRequestItem {

    private Long medicineInstanceId;

    @NotNull
    private Long medicineId;

    private LocalDate selfLife;

    @NotNull
    private int amount;

    public MedicineInstanceRequestItem() {
    }

    public Long getMedicineInstanceId() {
        return medicineInstanceId;
    }

    public void setMedicineInstanceId(Long medicineInstanceId) {
        this.medicineInstanceId = medicineInstanceId;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public LocalDate getSelfLife() {
        return selfLife;
    }

    public void setSelfLife(LocalDate selfLife) {
        this.selfLife = selfLife;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "MedicineInstanceRequestItem{" +
                "medicineInstanceId=" + medicineInstanceId +
                ", medicineId=" + medicineId +
                ", selfLife=" + selfLife +
                ", amount=" + amount +
                '}';
    }
}
