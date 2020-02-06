package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import com.netcracker.group5.medkit.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findAllMedicines(Pageable pageable, String searchQuery) {
        long limit = pageable.getOffset() + pageable.getPageSize();

        return medicineRepository.findAll(limit, pageable.getOffset(), searchQuery);
    }

    @Override
    public List<Medicine> findAllMedicinesByParams(Pageable pageable, String searchQuery) {
        long limit = pageable.getOffset() + pageable.getPageSize();

        return medicineRepository.findAllByParams(limit, pageable.getOffset(), searchQuery);
    }

    @Override
    public Medicine findMedicine(Long id) {
        return medicineRepository.find(id);
    }

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        return medicineRepository.save(medicine);
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.delete(id);
    }

    @Override
    public Long findMedicineIdByMedicineInstanceId(long medicineInstanceId) {
        return medicineRepository.findMedicineIdByMedicineInstanceId(medicineInstanceId);
    }
}
