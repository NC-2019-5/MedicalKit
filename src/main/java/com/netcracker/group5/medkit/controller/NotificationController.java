package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.dto.request.NotificationRequestItem;
import com.netcracker.group5.medkit.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Validated
@CrossOrigin
@RestController
@RequestMapping("/notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @PutMapping
    public ResponseEntity<?> autoDecrementMI(@Valid
                                             @RequestBody NotificationRequestItem requestItem) {
        notificationService.autoDecrementMI(new Notification(requestItem));

        return ResponseEntity.ok().build();
    }

}
