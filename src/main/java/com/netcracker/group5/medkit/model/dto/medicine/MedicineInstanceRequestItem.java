package com.netcracker.group5.medkit.model.dto.medicine;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

public class MedicineInstanceRequestItem {

    @NotBlank
    private String name;

    @NotBlank
    private String manufacturer;

    @NotBlank
    private LocalDate selfLife;

    @NotBlank
    private int amount;

    public MedicineInstanceRequestItem() {
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
    public String toString() {
        return "MedicineInstanceRequestItem{" +
                "name='" + name + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", selfLife='" + selfLife + '\'' +
                ", amount=" + amount +
                '}';
    }
}
