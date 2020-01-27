package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.PasswordResetToken;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.AuthTokenResponse;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterPatientResponseItem;
import com.netcracker.group5.medkit.security.TokenHandler;
import com.netcracker.group5.medkit.service.MailService;
import com.netcracker.group5.medkit.service.NotificationAutoGeneratorService;
import com.netcracker.group5.medkit.service.PasswordResetTokenService;
import com.netcracker.group5.medkit.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin
@RestController
@Api(value = "user")
@RequestMapping("/")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenHandler tokenHandler;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationAutoGeneratorService notificationAutoGeneratorService;

    @Autowired
    private PasswordResetTokenService passwordResetTokenServiceService;

    @Autowired
    private MailService mailService;

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

//        if (user.getRole().equals(Role.PATIENT)) {
//            notificationAutoGeneratorService.generateNotification(user.getId());
//        }

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

    @PostMapping("forgot-password")
    @ApiOperation(value = "ForgotPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No exist user with this email")
    })
    public ResponseEntity<?> sendForgotPasswordMail(@RequestBody String email,
                                                    HttpServletRequest request){

        if (userService.isExistUserWithEmail(email)){

            PasswordResetToken token = new PasswordResetToken();
            token.setUserEmail(email);
            token.setToken(UUID.randomUUID().toString());
            token.setCratedDate(LocalDateTime.now());
            passwordResetTokenServiceService.addToken(token);

            String appUrl = request.getScheme() + "://" + request.getServerName();
            mailService.sendForgotPasswordMail(email, appUrl + "/reset?token=" + token.getToken());

            return ResponseEntity.status(HttpStatus.OK).build();

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("reset")
    @ApiOperation(value = "ResetPassword")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No valid token")
    })
    public ResponseEntity<?> resetPassword(@RequestParam String token, @RequestBody String newPassword){
        String userEmail = passwordResetTokenServiceService.getUserEmailByToken(token);
        if (!userEmail.isEmpty()){
            userService.updatePasswordByEmail(userEmail, newPassword);
            passwordResetTokenServiceService.deleteToken(token);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
