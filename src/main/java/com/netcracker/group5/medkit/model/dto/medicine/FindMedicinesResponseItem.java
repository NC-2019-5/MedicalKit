package com.netcracker.group5.medkit.model.dto.medicine;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.List;

public class FindMedicinesResponseItem implements Requestable {

    private List<Medicine> medicines;

    public FindMedicinesResponseItem(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }
}
