package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findMedicines(int page, int size, String orderBy, String direction, String searchQuery) {
        medicineRepository.findAll(5L);
        return null;
    }
}
