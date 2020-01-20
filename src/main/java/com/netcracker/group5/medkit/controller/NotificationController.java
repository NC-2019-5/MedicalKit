package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;


@Validated
@CrossOrigin
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;


}
