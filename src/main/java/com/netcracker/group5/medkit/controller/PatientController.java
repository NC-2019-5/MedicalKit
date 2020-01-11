package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.EditPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.GetPatientResponseItem;
import com.netcracker.group5.medkit.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/profile/account")
    public ResponseEntity<?> getPatient() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.getPatientByUserId(currentUser.getId())));
    }

    @PutMapping("/profile/edit")
    public ResponseEntity<?> editPatient(@Valid @RequestBody EditPatientRequestItem requestItem) {
        Patient patient = new Patient(requestItem);

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.editPatient(patient)));
    }
}
