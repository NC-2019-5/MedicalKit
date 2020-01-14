package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.medicine.MedicineInstance;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.MedicineInstanceRepository;
import com.netcracker.group5.medkit.repository.PatientRepository;
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
        Patient patient = patientRepository.findByUserId(currentUser.getId());
        long limit = pageable.getOffset() + pageable.getPageSize();

        return medicineInstanceRepository.findMedicineInstances(patient.getId(), limit, pageable.getOffset(), searchQuery).orElse(null);
    }

    @Override
    public MedicineInstance createMedicineInstance(MedicineInstance medicineInstance) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByUserId(currentUser.getId());

        return medicineInstanceRepository.save(patient.getId(), medicineInstance);
    }

    @Override
    public void deleteMedicineInstance(Long id) {

        medicineInstanceRepository.delete(id);
    }
}

