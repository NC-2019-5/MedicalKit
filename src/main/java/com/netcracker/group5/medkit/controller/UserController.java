package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.AuthTokenResponse;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientResponseItem;
import com.netcracker.group5.medkit.security.TokenHandler;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@CrossOrigin
@RestController
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginUserRequestItem requestItem) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestItem.getEmail(), requestItem.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        User user = (User) userService.loadUserByUsername(requestItem.getEmail());
        System.out.println(user);
        String token = tokenHandler.generateToken(user);

        return ResponseEntity.ok(new AuthTokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterPatientRequestItem registerPatientRequestItem) {
        Patient patient = (Patient) userService.registerUser(new Patient(registerPatientRequestItem));
        return ResponseEntity.ok(new RegisterPatientResponseItem(patient.getEmail()));
    }
}
