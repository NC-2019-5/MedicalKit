package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.AuthTokenResponse;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientResponseItem;
import com.netcracker.group5.medkit.repository.impl.NotificationRepositoryImpl;
import com.netcracker.group5.medkit.security.TokenHandler;
import com.netcracker.group5.medkit.service.NotificationAutoGeneratorService;
import com.netcracker.group5.medkit.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@Api(value = "user")
@RequestMapping("/")
public class UserController {
    private static final Logger logger = LogManager.getLogger();
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationAutoGeneratorService notificationAutoGeneratorService;

    @PostMapping("login")
    @ApiOperation(value = "Login")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> login(@Valid
                                   @RequestBody LoginUserRequestItem requestItem) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestItem.getEmail(), requestItem.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password");
        }

        User user = userService.getUserByEmail(requestItem.getEmail());
        String token = tokenHandler.generateToken(user);

        if (user.getRole().equals(Role.PATIENT)) {
            notificationAutoGeneratorService.generateNotification(user.getId());
            notificationAutoGeneratorService.generateMNotification(user.getId());
        }

        return ResponseEntity.ok(new AuthTokenResponse(token));

    }

    @PostMapping("register")
    @ApiOperation(value = "RegisterUser")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    public ResponseEntity<?> registerUser(@Valid
                                          @RequestBody RegisterPatientRequestItem registerPatientRequestItem) {
        Patient patient = (Patient) userService.registerUser(new Patient(registerPatientRequestItem));
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new RegisterPatientResponseItem(patient.getEmail()));
    }
}
