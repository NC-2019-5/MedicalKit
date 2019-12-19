package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;

import java.util.List;

public interface MedicineService {
    List<Medicine> findMedicines(int page, int size, String orderBy, String direction, String searchQuery);
}
