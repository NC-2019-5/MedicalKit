package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.dto.user.GetPatientResponseItem;
import com.netcracker.group5.medkit.service.PatientService;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController

public class PatientController {
    @Autowired
    private PatientService patientService;

    @RequestMapping(value = "/profile/account",
            produces = "application/json",
            method = RequestMethod.GET)
    public GetPatientResponseItem onLoad(@RequestParam long id) {
        return new GetPatientResponseItem(patientService.getPatient(id));
    }



}
