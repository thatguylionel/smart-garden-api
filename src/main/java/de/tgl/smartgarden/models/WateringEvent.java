package de.tgl.smartgarden.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "watering_events")
@NoArgsConstructor
@AllArgsConstructor
public class WateringEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garden_id", nullable = false)
    private Long gardenId;

    @Column(nullable = false)
    private double waterAmount;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(length = 500)
    private String notes;

    // Constructor with fields
    public WateringEvent(Long gardenId, double waterAmount, LocalDateTime timestamp) {
        this.gardenId = gardenId;
        this.waterAmount = waterAmount;
        this.timestamp = timestamp;
    }
}
