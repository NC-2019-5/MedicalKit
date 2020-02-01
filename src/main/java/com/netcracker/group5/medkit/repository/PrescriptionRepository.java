package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;
import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;

import java.util.List;
import java.util.Optional;

public interface PrescriptionRepository {

    Optional<List<Prescription>> findPrescriptionsByPatientId(Long patientId, long limit, long offset);

    Prescription save(Long patientId, Prescription prescription);

    void deletePrescriptionWithItems(Long id);

    Optional<List<PrescriptionItem>> findPrescriptionItemsByPrescriptionId(Long prescriptionId, long limit, long offset);

    PrescriptionItem savePrescriptionItem(PrescriptionItem prescriptionItem);

    Optional<List<Long>> findActivePrescriptionItems(Long patientId);

    PrescriptionItem findPrescriptionItem(Long prescriptionItemId);

    void deletePrescriptionItem(Long prescriptionItemId);
}
