package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.RegisterUserResponseItem;

public interface UserService {

    LoginUserResponseItem login(LoginUserRequestItem loginUserRequestItem);

    RegisterUserResponseItem registerUser(RegisterUserRequestItem registerUserRequestItem);
}
