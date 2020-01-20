package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.MedicineInstanceRepository;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.service.MedicineInstanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineInstanceServiceImpl implements MedicineInstanceService {

    @Autowired
    private MedicineInstanceRepository medicineInstanceRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<MedicineInstance> findMedicineInstances(Pageable pageable, String searchQuery) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long limit = pageable.getOffset() + pageable.getPageSize();

        return medicineInstanceRepository.findMedicineInstances(currentUser.getId(), limit, pageable.getOffset(), searchQuery).orElse(null);
    }

    @Override
    public MedicineInstance createMedicineInstance(MedicineInstance medicineInstance) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return medicineInstanceRepository.save(currentUser.getId(), medicineInstance);
    }

    @Override
    public void deleteMedicineInstance(Long id) {

        medicineInstanceRepository.delete(id);
    }
}

