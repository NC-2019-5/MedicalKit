package com.netcracker.group5.medkit.service;

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
        return null;
    }
}
