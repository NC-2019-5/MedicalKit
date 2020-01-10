package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient editPatient(Patient patient) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userRepository.findUserByEmail(userEmail);

        Patient patientToBeEdited = patientRepository.findByUserId(user.getId());

        patient.setPassword(user.getPassword());
        patient.setRole(user.getRole());
        patient.setId(user.getId());
        patient.setSex(patientToBeEdited.getSex());

        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientByUserId(Long id) {
        User user = userRepository.findUserById(id);
        Patient patient = patientRepository.findByUserId(id);

        patient.setEmail(user.getEmail());
        patient.setPassword(user.getPassword());
        patient.setRole(user.getRole());

        return patient;
    }
}
