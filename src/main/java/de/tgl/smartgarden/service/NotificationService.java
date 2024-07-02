package de.tgl.smartgarden.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface NotificationService {
    List<String> getAll(String gardenid);

    @Service
    class NotificationServiceImpl implements NotificationService {

        @Override
        public List<String> getAll(String gardenid) {
            return List.of("Notification 1", "Notification 2", "Notification 3", "Notification 4");
        }
    }
}
