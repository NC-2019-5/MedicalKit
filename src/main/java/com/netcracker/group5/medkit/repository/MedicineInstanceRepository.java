package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;

import java.util.List;
import java.util.Optional;

public interface MedicineInstanceRepository {
    Optional<List<MedicineInstance>> findMedicineInstances(Long patientId, long limit, long offset, String searchQuery);

    MedicineInstance save(Long patientId, MedicineInstance medicineInstance);

    void delete(Long id);
}
