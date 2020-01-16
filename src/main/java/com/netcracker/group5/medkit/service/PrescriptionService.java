package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PrescriptionService {

    List<Prescription> findPrescriptionsByPatientId(Pageable pageable);

    void addPrescription(Prescription prescription);

    void deletePrescriptionWithItems(Long id);

    List<PrescriptionItem> findPrescriptionItemsByPrescriptionId(Pageable pageable, Long prescriptionId);

    void addPrescriptionItem(Long prescriptionId, PrescriptionItem prescriptionItem);

}
