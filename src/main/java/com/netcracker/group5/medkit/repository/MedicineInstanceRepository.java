package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;

import java.util.List;
import java.util.Optional;

public interface MedicineInstanceRepository {
    Optional<List<MedicineInstance>> findMedicineInstances(int limit, long offset, String search);
    MedicineInstance save(MedicineInstance medicineInstance);
}
