package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.dto.user.EditPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.GetPatientResponseItem;
import com.netcracker.group5.medkit.security.TokenHandler;
import com.netcracker.group5.medkit.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@CrossOrigin
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private TokenHandler tokenHandler;

    @GetMapping("/profile/account")
    public ResponseEntity<?> getPatient(HttpServletRequest request) {
        Long id = tokenHandler.extractUserId(tokenHandler.getTokenFromHttpRequest(request));

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.getPatient(id)));
    }

    @PutMapping("/profile/edit")
    public ResponseEntity<?> editPatient(@Valid @RequestBody EditPatientRequestItem requestItem) {
        Patient patient = new Patient(requestItem);

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.editPatient(patient)));
    }
}
