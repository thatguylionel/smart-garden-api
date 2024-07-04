package de.tgl.smartgarden.web;

import de.tgl.smartgarden.models.Garden;
import de.tgl.smartgarden.models.Notification;
import de.tgl.smartgarden.models.Sensor;
import de.tgl.smartgarden.models.WateringEvent;
import de.tgl.smartgarden.service.GardenService;
import de.tgl.smartgarden.service.NotificationService;
import de.tgl.smartgarden.service.SensorService;
import de.tgl.smartgarden.service.WaterService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/gardens")
public class GardenController {
    private static final Logger logger = LoggerFactory.getLogger(GardenController.class);
    private final GardenService gardenService;
    private final SensorService sensorService;
    private final NotificationService notificationService;
    private final WaterService waterService;

    public GardenController(GardenService gardenService, SensorService sensorService,
                            NotificationService notificationService, WaterService waterService) {
        this.gardenService = gardenService;
        this.sensorService = sensorService;
        this.notificationService = notificationService;
        this.waterService = waterService;
    }

    @GetMapping
    public ResponseEntity<List<Garden>> getAllGardens() {
        List<Garden> gardens = gardenService.getAll();
        return ResponseEntity.ok(gardens);
    }

    @PostMapping
    public ResponseEntity<Garden> addGarden(@Valid @RequestBody Garden garden) {
        Garden savedGarden = gardenService.add(garden);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGarden);
    }

    @GetMapping("/{gardenId}/sensors")
    public ResponseEntity<List<Sensor>> getSensors(@PathVariable Long gardenId) {
        logger.info("Fetching sensors for garden {}", gardenId);
        List<Sensor> sensors = sensorService.getAllByGardenId(gardenId);
        return ResponseEntity.ok(sensors);
    }

    @PostMapping("/{gardenId}/sensors")
    public ResponseEntity<Sensor> addSensor(@PathVariable Long gardenId, @Valid @RequestBody Sensor sensor) {
        logger.info("Adding sensor for garden {}", gardenId);
        Sensor savedSensor = sensorService.addSensor(gardenId, sensor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSensor);
    }


    @PostMapping("/{gardenId}/water")
    public ResponseEntity<WateringEvent> waterGarden(@PathVariable Long gardenId, @RequestParam(defaultValue = "1.0") double waterAmount) {
        logger.info("Watering garden {} with {} units of water", gardenId, waterAmount);
        WateringEvent result = waterService.waterGarden(gardenId, waterAmount);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PostMapping("/{gardenId}/prune")
    public ResponseEntity<String> pruneGarden(@PathVariable Long gardenId) {
        logger.info("Pruning garden {}", gardenId);
        // Implement pruning logic in a separate service
        return ResponseEntity.ok("Garden pruned successfully");
    }

    @GetMapping("/{gardenId}/notifications")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long gardenId) {
        logger.info("Fetching notifications for garden {}", gardenId);
        List<Notification> notifications = notificationService.getAllByGardenId(gardenId);
        return ResponseEntity.ok(notifications);
    }

    @PostMapping("/{gardenId}/notifications")
    public ResponseEntity<Notification> addNotification(@PathVariable Long gardenId,
                                                        @Valid @RequestBody Notification notification) {
        logger.info("Adding notification for garden {}", gardenId);
        Notification savedNotification = notificationService.addNotification(gardenId, notification);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedNotification);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        logger.error("An error occurred", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
    }
}