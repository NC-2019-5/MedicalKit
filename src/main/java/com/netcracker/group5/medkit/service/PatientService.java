package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;

public interface PatientService {
    Patient getPatient(Long id);

    Patient editPatient(Patient patient);

    void editPassword(Patient user, String oldPassword, String newPassword);
}
