package org.example.notificationservice.controller;

import jakarta.validation.Valid;
import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    // Create notification
    @PostMapping
    public Notification createNotification(
            @Valid @RequestBody Notification notification) {

        return notificationService.createNotification(notification);
    }

    // Get all notifications
    @GetMapping
    public List<Notification> getAllNotifications() {

        return notificationService.getAllNotifications();
    }

    // Get notification by ID
    @GetMapping("/{id}")
    public Notification getNotificationById(
            @PathVariable Long id) {

        return notificationService.getNotificationById(id);
    }

    // Get notifications by account number
    @GetMapping("/account/{accountNumber}")
    public List<Notification> getNotificationsByAccountNumber(
            @PathVariable String accountNumber) {

        return notificationService
                .getNotificationsByAccountNumber(accountNumber);
    }

    // Delete notification
    @DeleteMapping("/{id}")
    public String deleteNotification(
            @PathVariable Long id) {

        notificationService.deleteNotification(id);

        return "Notification deleted successfully";
    }
}