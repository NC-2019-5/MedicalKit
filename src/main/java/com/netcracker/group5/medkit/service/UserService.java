package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    String login(String email, String password);

    User registerUser(User user);

    User getUserByEmail(String email);

    void editPassword(User user, String oldPassword, String newPassword);
}
