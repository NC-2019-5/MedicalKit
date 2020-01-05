package com.netcracker.group5.medkit.model.dto.medicine;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class EditMedicineInstanceRequestItem {

    @NotBlank
    private long id;

    @NotBlank
    private LocalDate selfLife;

    @NotBlank
    private int amount;


    public EditMedicineInstanceRequestItem() {
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
