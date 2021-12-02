package com.codegym.controller;

import com.codegym.model.entity.Notification;
import com.codegym.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("notifications")
public class NotificationRestController {
    @Autowired
    private INotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification) {
        return new ResponseEntity<>(notificationService.save(notification), HttpStatus.OK);
    }
}
