package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicineInstanceService {
    List<MedicineInstance> findMedicineInstances(Pageable pageable, String searchQuery);

    MedicineInstance findMedicineInstance(Long medicineInstanceId);

    MedicineInstance saveMedicineInstance(MedicineInstance medicineInstance);

    void deleteMedicineInstance(Long id);
}
