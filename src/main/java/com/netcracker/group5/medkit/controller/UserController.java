package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.dto.user.LoginUserRequestItem;
import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @CrossOrigin
    @RequestMapping("/login")
    @ResponseBody
    public LoginUserResponseItem login(@RequestBody LoginUserRequestItem loginUserRequestItem) {
        return userService.login(loginUserRequestItem);
    }
}
