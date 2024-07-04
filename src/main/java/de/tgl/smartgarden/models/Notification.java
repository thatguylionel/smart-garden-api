package de.tgl.smartgarden.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garden_id", nullable = false)
    private Long gardenId;

    @Column(nullable = false)
    private String message;

    @Column(name = "notification_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "read_at")
    private LocalDateTime readAt;

    public enum NotificationType {
        WATER_NEEDED,
        PRUNE_NEEDED,
        PEST_DETECTED,
        TEMPERATURE_ALERT,
        HUMIDITY_ALERT,
        GENERAL
    }
}