package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MedicineService {
    List<Medicine> findAllMedicines(Pageable pageable);

    void deleteMedicine(Long id);
}
