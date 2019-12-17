package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Patient;

public interface PatientService {
    Patient getPatient(long id);

    Patient editPatient(Patient patient);
}
