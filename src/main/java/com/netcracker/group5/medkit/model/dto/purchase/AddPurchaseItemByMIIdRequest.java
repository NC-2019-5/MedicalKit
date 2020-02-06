package com.netcracker.group5.medkit.model.dto.purchase;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

public class AddPurchaseItemByMIIdRequest {

    @NotNull(message = "Medicine instance id is mandatory")
    @Positive(message = "Medicine instance id must be greater than 0")
    private Long medicineInstanceId;

    @NotNull(message = "Medicine amount is mandatory")
    @PositiveOrZero(message = "Medicine amount must be greater than 0")
    private Integer amount;

    public AddPurchaseItemByMIIdRequest(){}

    public Long getMedicineInstanceId() {
        return medicineInstanceId;
    }

    public void setMedicineInstanceId(Long medicineInstanceId) {
        this.medicineInstanceId = medicineInstanceId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}
