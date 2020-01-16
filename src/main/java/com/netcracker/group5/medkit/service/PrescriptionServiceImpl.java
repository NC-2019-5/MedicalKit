package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.PrescriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Override
    public List<Prescription> findPrescriptionsByPatientId(Pageable pageable) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByUserId(currentUser.getId());

        long limit = pageable.getOffset() + pageable.getPageSize();

        return prescriptionRepository.findPrescriptionsByPatientId(patient.getId(), limit, pageable.getOffset())
                .orElse(null);
    }

    @Override
    public void addPrescription(Prescription prescription) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientRepository.findByUserId(currentUser.getId());

        prescriptionRepository.save(patient.getId(), prescription);
    }

    @Override
    public void deletePrescriptionWithItems(Long id) {
        prescriptionRepository.deletePrescriptionWithItems(id);
    }

    @Override
    public List<PrescriptionItem> findPrescriptionItemsByPrescriptionId(Pageable pageable, Long prescriptionId) {
        long limit = pageable.getOffset() + pageable.getPageSize();

        return prescriptionRepository.findPrescriptionItemsByPrescriptionId(prescriptionId, limit, pageable.getOffset())
                .orElse(null);
    }

    @Override
    public void addPrescriptionItem(Long prescriptionId, PrescriptionItem prescriptionItem) {
        prescriptionRepository.savePrescriptionItem(prescriptionId, prescriptionItem);
    }

}
