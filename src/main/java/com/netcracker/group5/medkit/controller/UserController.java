package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.dto.user.LoginUserResponseItem;
import com.netcracker.group5.medkit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public LoginUserResponseItem login(@RequestParam String login, @RequestParam String password) {
        return userService.login(login, password);
    }
}
