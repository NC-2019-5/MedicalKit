package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.List;

public interface MedicineRepository {
    List<Medicine> findAll(int limit, long offset);
}
