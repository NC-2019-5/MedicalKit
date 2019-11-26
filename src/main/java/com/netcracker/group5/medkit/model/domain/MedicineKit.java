package com.netcracker.group5.medkit.model.domain;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class MedicineKit {

    private long id;
    private List<MedicineInstance> medicineInstances;
}
