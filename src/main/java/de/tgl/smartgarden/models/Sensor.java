package de.tgl.smartgarden.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "sensors")
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "garden_id", nullable = false)
    private Long gardenId;

    @Enumerated(EnumType.STRING)
    @Column(name = "sensor_type", nullable = false)
    private SensorType type;

    @Column(name = "sensor_value", nullable = false)
    private Double sensorValue;

    @Column(name = "unit_of_measurement", nullable = false)
    private String unitOfMeasurement;

    @Column(name = "last_reading_at", nullable = false)
    private LocalDateTime lastReadingAt;

    public Sensor(Long id, Long gardenId, SensorType type, Double sensorValue, String unitOfMeasurement) {
        this.id = id;
        this.gardenId = gardenId;
        this.type = type;
        this.sensorValue = sensorValue;
        this.unitOfMeasurement = unitOfMeasurement;
        this.lastReadingAt = LocalDateTime.now();
    }

    public enum SensorType {
        TEMPERATURE,
        HUMIDITY,
        LIGHT,
        SOIL_MOISTURE,
        PH
    }
}