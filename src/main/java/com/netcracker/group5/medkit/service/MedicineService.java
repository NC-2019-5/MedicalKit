package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicineService {
    List<Medicine> findAllMedicines(Pageable pageable);

    Medicine saveMedicine(Medicine medicine);

    void deleteMedicine(Long id);
}
