package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.List;

public interface MedicineRepository {
    List<Medicine> findAll(long limit, long offset, String searchQuery);

    List<Medicine> findAllByParams(long limit, long offset, String searchQuery);

    Medicine find(Long id);

    Medicine save(Medicine medicine);

    void delete(Long id);

    Long findMedicineIdByMedicineInstanceId(long medicineId);
}
