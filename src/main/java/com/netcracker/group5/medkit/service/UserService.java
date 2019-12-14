package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.domain.user.User;
import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;

public interface UserService {

    LoginUserResponseItem login(LoginUserRequestItem loginUserRequestItem);

    User registerUser(User user);
}
