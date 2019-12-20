package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

public interface MedicineRepository {
    Medicine findAll(Long id);
}
