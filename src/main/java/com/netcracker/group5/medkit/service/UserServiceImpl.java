package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        return ":(";
    }

    @Override
    public User registerUser(User user) {
        if (!userRepository.isExistUserWithEmail(user.getEmail())) {
            return userRepository.save(user);
        }
        throw new IllegalArgumentException("Email " + user.getEmail() + " is already taken.");
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(s);
        System.out.println("user.getUsername() = " + user.getUsername());
        return user;
    }
}
