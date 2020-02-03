package com.netcracker.group5.medkit.controller;

import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.dto.notification.NotificationResponseItem;
import com.netcracker.group5.medkit.model.dto.request.NotificationRequestItem;
import com.netcracker.group5.medkit.service.NotificationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/notification")
@Api(value = "notification")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @GetMapping
    @ApiOperation(value = "FindAllMNotifications")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findAllMNotifications(@PageableDefault Pageable pageable,
                                                  @Size(max = 256, message = "Search query is too long")
                                                  @RequestParam(required = false) String searchQuery) {
        List<Notification> notifications = notificationService.findMNotifications(pageable, searchQuery);
        //NotificationResponseItem responseItem = new NotificationResponseItem(notifications);

        return ResponseEntity.ok(notifications);
    }

}
