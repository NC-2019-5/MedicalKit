package com.netcracker.group5.medkit.service.impl;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.PatientRepository;
import com.netcracker.group5.medkit.repository.UserRepository;
import com.netcracker.group5.medkit.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id);
    }

    @Override
    public Patient editPatient(Patient patient) {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        patient.setId(currentUser.getId());
        patient.setPassword(currentUser.getPassword());

        return (Patient) userRepository.saveByRole(patient);
    }

    @Override
    public void editPassword(User user, String oldPassword, String newPassword) {
        if (passwordEncoder.matches(oldPassword, user.getPassword())) {
            Patient patient = patientRepository.findById(user.getId());
            patient.setPassword(passwordEncoder.encode(newPassword));
            patientRepository.save(patient.getId(), patient);
        } else {
            throw new IllegalArgumentException("Incorrect password");
        }
    }

}
