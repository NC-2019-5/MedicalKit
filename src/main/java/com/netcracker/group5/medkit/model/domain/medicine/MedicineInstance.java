package com.netcracker.group5.medkit.model.domain.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.dto.medicine.EditMedicineInstanceRequestItem;
import com.netcracker.group5.medkit.model.dto.medicine.MedicineInstanceRequestItem;
import java.time.LocalDate;
import java.util.Objects;

public class MedicineInstance implements Requestable {

    private Long id;
    private String name;
    private String manufacturer;
    private LocalDate selfLife;
    private int amount;

    public MedicineInstance(MedicineInstanceRequestItem medicineInstanceRequestItem) {
        this.name = name;
        this.manufacturer = manufacturer;
        this.selfLife = selfLife;
        this.amount = amount;
    }

    public MedicineInstance(EditMedicineInstanceRequestItem editMedicineInstanceRequestItem) {
        this.id = id;
        this.selfLife = selfLife;
        this.amount = amount;
    }

    private MedicineInstance() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
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
        return Objects.hash(id, name, manufacturer,  selfLife, amount);
    }

    @Override
    public String toString() {
        return "MedicineInstance{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", selfLife=" + selfLife +
                ", amount=" + amount +
                '}';
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

        public Builder setName(String name) {
            MedicineInstance.this.name = name;
            return this;
        }

        public Builder setManufacturer(String manufacturer) {
            MedicineInstance.this.manufacturer = manufacturer;
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
