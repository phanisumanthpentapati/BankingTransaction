package org.example.notificationservice.service;

import org.example.notificationservice.entity.Notification;
import org.example.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final EmailService emailService;

    public NotificationService(
            NotificationRepository notificationRepository,
            EmailService emailService) {

        this.notificationRepository = notificationRepository;
        this.emailService = emailService;
    }

    // Create notification
    public Notification createNotification(Notification notification) {

        if (notification.getNotificationDate() == null) {
            notification.setNotificationDate(
                    LocalDateTime.now()
            );
        }

        Notification savedNotification =
                notificationRepository.save(notification);

        // Send email
        emailService.sendEmail(
                "mnew70325@gmail.com",
                "BankEase Transaction Notification",
                "Dear Customer,\n\n"
                        + notification.getMessage()
                        + "\n\n"
                        + "Account Number: "
                        + notification.getAccountNumber()
                        + "\n"
                        + "Transaction Type: "
                        + notification.getType()
                        + "\n"
                        + "Date: "
                        + notification.getNotificationDate()
                        + "\n\n"
                        + "Thank you for using BankEase.\n\n"
                        + "Regards,\n"
                        + "BankEase Team"
        );

        return savedNotification;
    }

    // Get all notifications
    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    // Get notification by ID
    public Notification getNotificationById(Long id) {

        return notificationRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Notification not found"
                        ));
    }

    // Get notifications by account number
    public List<Notification> getNotificationsByAccountNumber(
            String accountNumber) {

        return notificationRepository
                .findByAccountNumber(accountNumber);
    }

    // Delete notification
    public void deleteNotification(Long id) {

        if (!notificationRepository.existsById(id)) {
            throw new RuntimeException(
                    "Notification not found"
            );
        }

        notificationRepository.deleteById(id);
    }
}