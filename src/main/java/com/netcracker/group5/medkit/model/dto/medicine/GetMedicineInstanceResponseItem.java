package com.netcracker.group5.medkit.model.dto.medicine;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Sex;

import java.time.LocalDate;

public class GetMedicineInstanceResponseItem {

    private Long id;
    private String name;
    private String manufacturer;
    private LocalDate selfLife;
    private int amount;

    public GetMedicineInstanceResponseItem() {
    }

    public GetMedicineInstanceResponseItem(MedicineInstance medicineInstance) {
        this.id = medicineInstance.getId();
        this.name = medicineInstance.getName();
        this.manufacturer = medicineInstance.getManufacturer();
        this.selfLife = medicineInstance.getSelfLife();
        this.amount = medicineInstance.getAmount();
    }
}
