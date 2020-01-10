package com.netcracker.group5.medkit.model.dto.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;

import java.util.List;

public class MedicineInstanceResponseItem implements Requestable {

    private List<MedicineInstance> medicineInstances;

    public MedicineInstanceResponseItem(List<MedicineInstance> medicineInstances) {
        this.medicineInstances = medicineInstances;
    }

    public List<MedicineInstance> getMedicineInstances() {
        return medicineInstances;
    }

    public void setMedicineInstances(List<MedicineInstance> medicineInstances) {
        this.medicineInstances = medicineInstances;
    }
}
