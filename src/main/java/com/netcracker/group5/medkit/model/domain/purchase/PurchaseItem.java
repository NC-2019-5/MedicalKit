package com.netcracker.group5.medkit.model.domain.purchase;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.dto.purchase.AddPurchaseItemRequest;

import java.util.Objects;

public class PurchaseItem {

    private Long id;
    private Medicine medicine;
    private int amount;

    private PurchaseItem() {
    }

    public PurchaseItem(AddPurchaseItemRequest request) {
        this.medicine = Medicine.newBuilder()
                .setId(request.getMedicineId())
                .build();
        this.amount = request.getAmount();
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

    @Override
    public String toString() {
        return "PurchaseItem{" +
                "id=" + id +
                ", medicine=" + medicine +
                ", amount=" + amount +
                '}';
    }

    public static Builder newBuilder() {
        return new PurchaseItem().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            PurchaseItem.this.id = id;
            return this;
        }

        public Builder setMedicine(Medicine medicine) {
            PurchaseItem.this.medicine = medicine;
            return this;
        }

        public Builder setAmount(int amount) {
            PurchaseItem.this.amount = amount;
            return this;
        }

        public PurchaseItem build() {
            return PurchaseItem.this;
        }
    }
}
