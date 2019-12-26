package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.EditPasswordRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientResponseItem;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginUserRequestItem loginUserRequestItem) {
        return userService.login(loginUserRequestItem.getEmail(), loginUserRequestItem.getPassword());
    }

    @PostMapping("/register")
    public RegisterPatientResponseItem registerUser(@Valid @RequestBody RegisterPatientRequestItem registerPatientRequestItem) {
        System.out.println(registerPatientRequestItem);
        Patient patient = (Patient) userService.registerUser(new Patient(registerPatientRequestItem));
        return new RegisterPatientResponseItem(patient.getName());
    }

    public ResponseEntity<?> editPassword(@RequestBody EditPasswordRequestItem requestItem) {
        userService.editPassword(user, requestItem.getOldPassword(), requestItem.getNewPassword());
        return ResponseEntity.ok().build();
    }
}
