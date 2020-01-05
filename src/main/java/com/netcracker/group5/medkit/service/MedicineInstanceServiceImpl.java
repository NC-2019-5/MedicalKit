package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.repository.MedicineInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineInstanceServiceImpl implements MedicineInstanceService {

    @Autowired
    private MedicineInstanceRepository medicineInstanceRepository;

    @Override
    public List<MedicineInstance> findMedicineInstances(Pageable pageable, String searchQuery) {
        return medicineInstanceRepository.findMedicineInstances(pageable.getPageSize(), pageable.getOffset(), searchQuery).orElse(null);
    }

    @Override
    public MedicineInstance createMedicineInstance(MedicineInstance medicineInstance) {
        return medicineInstanceRepository.save(medicineInstance);
    }

    @Override
    public MedicineInstance editMedicineInstance(MedicineInstance medicineInstance) {
        return medicineInstanceRepository.edit(medicineInstance);
    }
}

