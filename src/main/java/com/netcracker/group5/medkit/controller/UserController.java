package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.POST)
    public String login(@Valid @RequestBody LoginUserRequestItem loginUserRequestItem) {
        return userService.login(loginUserRequestItem.getEmail(), loginUserRequestItem.getPassword());
    }

    @RequestMapping(value = "/register",
            consumes = "application/json",
            produces = "application/json",
            method = RequestMethod.POST)
    public String registerUser(@Valid @RequestBody RegisterPatientRequestItem registerPatientRequestItem) {
        System.out.println(registerPatientRequestItem);
        Patient patient = (Patient) userService.registerUser(new Patient(registerPatientRequestItem));
        return patient.getName();
    }
}
