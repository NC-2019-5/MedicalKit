package com.netcracker.group5.medkit.service;

import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;

public interface UserService {

    LoginUserResponseItem login(LoginUserRequestItem loginUserRequestItem);
}
