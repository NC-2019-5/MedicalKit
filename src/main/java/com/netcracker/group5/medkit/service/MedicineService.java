package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicineService {
    List<Medicine> findAllMedicines(Pageable pageable, String searchQuery);

    List<Medicine> findAllMedicinesByParams(Pageable pageable, String searchQuery);

    Medicine saveMedicine(Medicine medicine);

    Medicine findMedicine(Long id);

    void deleteMedicine(Long id);
}
