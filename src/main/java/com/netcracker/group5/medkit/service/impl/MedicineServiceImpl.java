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
    public List<Medicine> findAllMedicines(Pageable pageable) {
        long limit = pageable.getOffset() + pageable.getPageSize();
        return medicineRepository.findAll(limit, pageable.getOffset());
    }

    @Override
    public void deleteMedicine(Long id) {
        medicineRepository.delete(id);
    }
}
