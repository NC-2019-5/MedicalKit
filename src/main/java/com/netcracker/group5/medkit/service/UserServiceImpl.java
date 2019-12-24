package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.repository.UserRepository;
import com.netcracker.group5.medkit.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new CustomUserDetails(userRepository.findUserByEmail(email));
    }
}
