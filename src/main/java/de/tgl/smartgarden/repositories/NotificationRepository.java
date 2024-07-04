package de.tgl.smartgarden.repositories;

import de.tgl.smartgarden.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Finds all notifications for a given garden ID.
     *
     * @param gardenId the ID of the garden
     * @return a list of notifications for the specified garden
     */
    List<Notification> findAllByGardenId(Long gardenId);

    /**
     * Finds all unread notifications for a given garden ID.
     *
     * @param gardenId the ID of the garden
     * @return a list of unread notifications for the specified garden
     */
    List<Notification> findAllByGardenIdAndReadAtIsNull(Long gardenId);

    /**
     * Finds all notifications of a specific type for a given garden ID.
     *
     * @param gardenId the ID of the garden
     * @param type     the type of notification
     * @return a list of notifications of the specified type for the specified garden
     */
    List<Notification> findAllByGardenIdAndType(Long gardenId, Notification.NotificationType type);
}
