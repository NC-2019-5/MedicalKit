package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginUserResponseItem login(String login, String password) {
        User user = userRepository.findUserByEmail(login);
        LoginUserResponseItem responseItem = new LoginUserResponseItem();

        if (user.getEmail().equals(login) && user.getPassword().equals(password)) {
            responseItem.setId(user.getId());
            responseItem.setEmail(user.getEmail());
            responseItem.setRole(user.getRole());
            responseItem.setPassword(user.getPassword());
        }

        return responseItem;
    }
}
