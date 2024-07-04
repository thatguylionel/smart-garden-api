package de.tgl.smartgarden.service;

import de.tgl.smartgarden.models.Notification;
import de.tgl.smartgarden.repositories.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {
    List<Notification> getAllByGardenId(Long gardenId);

    Notification addNotification(Long gardenId, Notification notification);

    @Service
    class NotificationServiceImpl implements NotificationService {
        private final NotificationRepository notificationRepository;

        public NotificationServiceImpl(NotificationRepository notificationRepository) {
            this.notificationRepository = notificationRepository;
        }

        @Override
        public List<Notification> getAllByGardenId(Long gardenId) {
            return notificationRepository.findAllByGardenId(gardenId);
        }

        @Override
        public Notification addNotification(Long gardenId, Notification notification) {
            notification.setGardenId(gardenId);
            return notificationRepository.save(notification);
        }
    }
}

