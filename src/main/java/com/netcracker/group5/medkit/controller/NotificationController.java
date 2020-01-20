package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.dto.request.NotificationRequestItem;
import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@Validated
@CrossOrigin
@RestController
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PutMapping("/notification")
    public ResponseEntity<?> autoDecrementMI(@Valid
                                             @RequestBody NotificationRequestItem requestItem){
        notificationService.autoDecrementMI(new Notification(requestItem));

        return ResponseEntity.ok().build();
    }

}
