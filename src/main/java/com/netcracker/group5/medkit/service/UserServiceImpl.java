package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String login(String email, String password) {
        User user = userRepository.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return "Login successful! Email: " + user.getEmail() + " , password: " + user.getPassword();
        }

        return "Can not login. Wrong email or password";
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean emailValidate(String email) {
        User user = userRepository.findUserByEmail(email);
        return user!=null;
    }
}
