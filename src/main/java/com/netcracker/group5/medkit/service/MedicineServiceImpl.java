package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.Medicine;
import com.netcracker.group5.medkit.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Override
    public List<Medicine> findMedicines(Pageable pageable) {
        System.out.println("pageable = " + pageable);
        System.out.println("pageable.getPageSize() = " + pageable.getPageSize());
        System.out.println("pageable.getOffset() = " + pageable.getOffset());
        System.out.println("pageable.getPageNumber() = " + pageable.getPageNumber());

        medicineRepository.findAll(5L);
        return null;
    }
}
