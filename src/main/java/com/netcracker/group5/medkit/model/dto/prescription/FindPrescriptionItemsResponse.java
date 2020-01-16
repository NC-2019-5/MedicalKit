package com.netcracker.group5.medkit.model.dto.prescription;

import com.netcracker.group5.medkit.model.domain.prescription.PrescriptionItem;

import java.util.List;

public class FindPrescriptionItemsResponse {

    private List<PrescriptionItem> prescriptionItems;

    public FindPrescriptionItemsResponse(List<PrescriptionItem> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }

    public List<PrescriptionItem> getPrescriptionItems() {
        return prescriptionItems;
    }

    public void setPrescriptionItems(List<PrescriptionItem> prescriptionItems) {
        this.prescriptionItems = prescriptionItems;
    }
}
