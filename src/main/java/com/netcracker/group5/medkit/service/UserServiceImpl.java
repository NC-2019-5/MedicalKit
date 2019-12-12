package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Location;
import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterUserResponseItem;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginUserResponseItem login(LoginUserRequestItem loginUserRequestItem) {
        User user = userRepository.findUserByEmail(loginUserRequestItem.getEmail());
        LoginUserResponseItem responseItem = new LoginUserResponseItem();

        if (user != null && user.getPassword().equals(loginUserRequestItem.getPassword())) {
            responseItem.setId(user.getId());
            responseItem.setEmail(user.getEmail());
            responseItem.setRole(user.getRole());
            responseItem.setPassword(user.getPassword());
        }

        return responseItem;
    }

    @Override
    public RegisterUserResponseItem registerUser(RegisterUserRequestItem registerUserRequestItem) {
        User patient = Patient.newBuilder()
                .setId(21L)
                .setName(registerUserRequestItem.getName())
                .setSurname(registerUserRequestItem.getSurname())
                .setBirthDate(registerUserRequestItem.getBirthDate())
                .setPhoneNumber(registerUserRequestItem.getPhoneNumber())
                .setHeight(registerUserRequestItem.getHeight())
                .setWeight(registerUserRequestItem.getWeight())
                .setEmail(registerUserRequestItem.getEmail())
                .setPassword(registerUserRequestItem.getPassword())
                .setLocation(new Location(1L, registerUserRequestItem.getLocation(), "test", "test", "1"))
                .setSex(registerUserRequestItem.getSex())
                .setRole(Role.PATIENT)
                .build();

        RegisterUserResponseItem responseItem = new RegisterUserResponseItem();
        Patient savedPatient = (Patient) userRepository.save(patient);

        responseItem.setId(savedPatient.getId());
        responseItem.setName(savedPatient.getName());
        responseItem.setSurname(savedPatient.getSurname());
        responseItem.setBirthDate(savedPatient.getBirthDate());
        responseItem.setSex(savedPatient.getSex());
        responseItem.setWeight(savedPatient.getWeight());
        responseItem.setHeight(savedPatient.getHeight());
        responseItem.setLocation(savedPatient.getLocation());
        responseItem.setPhoneNumber(savedPatient.getPhoneNumber());
        responseItem.setEmail(savedPatient.getEmail());
        responseItem.setPassword(savedPatient.getPassword());
        responseItem.setRole(savedPatient.getRole());

        return responseItem;
    }
}
