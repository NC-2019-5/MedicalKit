package com.netcracker.group5.medkit.controller;
import com.netcracker.group5.medkit.model.domain.request.Notification;
import com.netcracker.group5.medkit.model.dto.notification.NotificationResponseItem;
import com.netcracker.group5.medkit.model.dto.request.NotificationRequestItem;
import com.netcracker.group5.medkit.service.NotificationService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Validated
@CrossOrigin
@RestController
@RequestMapping("/reminder")
@Api(value = "reminder")

public class ReminderController {
    @Autowired
    private NotificationService notificationService;
    @PutMapping
    @ApiOperation(value = "AutoDecrementMI")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> autoDecrementMI(@Valid
                                             @RequestBody NotificationRequestItem requestItem) {
        notificationService.autoDecrementMI(new Notification(requestItem));

        return ResponseEntity.ok().build();
    }
    @GetMapping
    @ApiOperation(value = "FindAllNotifications")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> findAllNotifications(@PageableDefault Pageable pageable,
                                                  @Size(max = 256, message = "Search query is too long")
                                                  @RequestParam(required = false) String searchQuery) {
        List<Notification> notifications = notificationService.findNotifications(pageable, searchQuery);
       // NotificationResponseItem responseItem = new NotificationResponseItem(notifications);

        return ResponseEntity.ok(notifications);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "DeletePrescription")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No data found")
    })
    @ApiImplicitParam(name = "Authorization", value = "Bearer token",
            required = true, dataType = "string", paramType = "header", defaultValue = "Bearer")
    public ResponseEntity<?> deleteNotification(@NotNull(message = "Id can not be empty")
                                                    @Positive(message = "Id must be greater than 0")
                                                    @PathVariable Long id) {
        try {
            notificationService.deleteNotification(id);

            return ResponseEntity.ok().build();
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
