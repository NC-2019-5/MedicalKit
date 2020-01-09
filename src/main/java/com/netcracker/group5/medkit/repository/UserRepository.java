package com.netcracker.group5.medkit.repository;


import com.netcracker.group5.medkit.model.domain.user.User;

public interface UserRepository {

    User findUserById(Long id);

    User findUserByEmail(String email);

    User save(User user);

    boolean isExistUserWithEmail(String email);
}
