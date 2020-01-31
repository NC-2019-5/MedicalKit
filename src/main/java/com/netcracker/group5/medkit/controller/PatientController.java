package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.EditPasswordRequestItem;
import com.netcracker.group5.medkit.model.dto.user.EditPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.GetPatientResponseItem;
import com.netcracker.group5.medkit.service.PatientService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(value = "/account")
@RequestMapping("/account")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @ApiOperation(value = "GetPatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> getPatient() {
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.getPatient(currentUser.getId())));
    }

    @PutMapping
    @ApiOperation(value = "EditPatient")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> editPatient(@Valid
                                         @RequestBody EditPatientRequestItem requestItem) {
        Patient patient = new Patient(requestItem);

        return ResponseEntity.ok(new GetPatientResponseItem(patientService.editPatient(patient)));
    }


    @PutMapping("/password")
    @ApiOperation(value = "EditPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> editPassword(@Valid
                                          @RequestBody EditPasswordRequestItem requestItem) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        patientService.editPassword(user, requestItem.getPassword(), requestItem.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
