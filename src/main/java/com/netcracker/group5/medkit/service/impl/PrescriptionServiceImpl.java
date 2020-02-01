package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.PrescriptionRepository;
import com.netcracker.group5.medkit.service.PrescriptionService;
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

        long limit = pageable.getOffset() + pageable.getPageSize();

        return prescriptionRepository.findPrescriptionsByPatientId(currentUser.getId(), limit, pageable.getOffset())
                .orElse(null);
    }

    @Override
    public Prescription addPrescription(Prescription prescription) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return prescriptionRepository.save(currentUser.getId(), prescription);
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
    public List<Long> findActivePrescriptionItems(Long patientId) {
        return prescriptionRepository.findActivePrescriptionItems(patientId).orElse(null);
    }

    @Override
    public PrescriptionItem findPrescriptionItem(Long prescriptionItemId) {
        return prescriptionRepository.findPrescriptionItem(prescriptionItemId);
    }

    @Override
    public PrescriptionItem addPrescriptionItem(PrescriptionItem prescriptionItem) {
        return prescriptionRepository.savePrescriptionItem(prescriptionItem);
    }

    @Override
    public void deletePrescriptionItem(Long prescriptionItemId) {
        prescriptionRepository.deletePrescriptionItem(prescriptionItemId);
    }
}
