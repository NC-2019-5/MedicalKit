package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.awt.print.Pageable;
import java.util.List;

public interface MedicineRepository {
    List<Medicine> findAll(long limit, long offset);

    Medicine find(Long id);

    Medicine save(Medicine medicine);

    void delete(Long id);
}
