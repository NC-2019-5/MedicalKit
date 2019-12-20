package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.dto.user.EditPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.GetPatientResponseItem;
import com.netcracker.group5.medkit.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/profile/account",
            produces = "application/json",
            method = RequestMethod.GET)
    public GetPatientResponseItem getPatient(@NotNull @RequestParam Long id) {
        return new GetPatientResponseItem(patientService.getPatient(id));
    }


    @RequestMapping(value = "/profile/edit",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.PUT)
    public GetPatientResponseItem editPatient(@Valid @RequestBody EditPatientRequestItem requestItem) {
        Patient patient = new Patient(requestItem);
        return new GetPatientResponseItem(patientService.editPatient(patient));
    }
}
