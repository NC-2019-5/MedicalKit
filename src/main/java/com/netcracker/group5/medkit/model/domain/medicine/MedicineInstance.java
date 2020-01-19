package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.SaveMedicineInstanceRequestItem;

import java.time.LocalDate;
import java.util.Objects;

public class MedicineInstance implements Requestable {

    private Long id;
    private Medicine medicine;
    private LocalDate selfLife;
    private int amount;

    public MedicineInstance(SaveMedicineInstanceRequestItem requestItem) {
        this.medicine = Medicine.newBuilder()
                .setId(requestItem.getMedicineId())
                .build();
        this.selfLife = requestItem.getSelfLife();
        this.amount = requestItem.getAmount();
    }

    public MedicineInstance(EditMedicineInstanceRequestItem requestItem) {
        this.id = requestItem.getId();
        this.medicine = Medicine.newBuilder()
                .setId(requestItem.getMedicineId())
                .build();
        this.selfLife = requestItem.getSelfLife();
        this.amount = requestItem.getAmount();
    }

    private MedicineInstance() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineInstance that = (MedicineInstance) o;
        return amount == that.amount &&
                id.equals(that.id) &&
                Objects.equals(selfLife, that.selfLife);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, medicine, selfLife, amount);
    }

    public static Builder newBuilder() {
        return new MedicineInstance().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder setId(Long id) {
            MedicineInstance.this.id = id;
            return this;
        }

        public Builder setMedicine(Medicine medicine) {
            MedicineInstance.this.medicine = medicine;
            return this;
        }

        public Builder setSelfLife(LocalDate selfLife) {
            MedicineInstance.this.selfLife = selfLife;
            return this;
        }

        public Builder setAmount(int amount) {
            MedicineInstance.this.amount = amount;
            return this;
        }

        public MedicineInstance build() {
            return MedicineInstance.this;
        }
    }
}
