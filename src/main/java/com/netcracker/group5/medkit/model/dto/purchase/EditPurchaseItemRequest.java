package com.netcracker.group5.medkit.model.dto.purchase;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class EditPurchaseItemRequest {

    @NotNull(message = "Purchase item id is mandatory")
    @Positive(message = "Purchase item id must be greater than 0")
    private Long id;

    @NotNull(message = "Medicine id is mandatory")
    @Positive(message = "Medicine id must be greater than 0")
    private Long medicineId;

    @NotNull(message = "Medicine amount is mandatory")
    @PositiveOrZero(message = "Medicine amount must be greater than 0")
    private int amount;

    public EditPurchaseItemRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Long medicineId) {
        this.medicineId = medicineId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
