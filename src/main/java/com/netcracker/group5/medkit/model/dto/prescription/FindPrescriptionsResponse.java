package com.netcracker.group5.medkit.model.dto.prescription;

import com.netcracker.group5.medkit.model.domain.prescription.Prescription;

import java.util.List;

public class FindPrescriptionsResponse {

    private List<Prescription> prescriptions;

    public FindPrescriptionsResponse(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}
