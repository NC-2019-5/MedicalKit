package com.netcracker.group5.medkit.repository;

import com.netcracker.group5.medkit.model.domain.user.Patient;

public interface PatientRepository {
    Patient save(Patient patient);
}