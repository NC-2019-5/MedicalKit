package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient editPatient(Patient patient) {
        return patientRepository.save(patient);
    }
}
