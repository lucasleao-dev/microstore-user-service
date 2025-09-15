package com.notification_service;

import com.notification_service.model.Notification;
import com.notification_service.repository.NotificationRepository;
import com.notification_service.service.NotificationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class NotificationServiceTests {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void testCreateNotification() {
        Notification notification = new Notification();
        notification.setMessage("Teste de notificação");

        Notification saved = notificationService.createNotification(notification);

        assertNotNull(saved.getId());
        assertEquals("Teste de notificação", saved.getMessage());
        assertNotNull(saved.getCreatedAt());
    }

    @Test
    void testGetAllNotifications() {
        List<Notification> notifications = notificationService.getAllNotifications();
        assertNotNull(notifications);
    }
}
