package de.tgl.smartgarden.web;

import de.tgl.smartgarden.models.Garden;
import de.tgl.smartgarden.service.GardenService;
import de.tgl.smartgarden.service.NotificationService;
import de.tgl.smartgarden.service.SensorsService;
import de.tgl.smartgarden.service.WaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gardens")
public class GardenController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GardenController.class);
    private final GardenService gardenService;
    private final SensorsService sensorsService;
    private final NotificationService notificationService;
    private final WaterService waterService;

    public GardenController(GardenService gardenService, SensorsService sensorsService, NotificationService notificationService, WaterService waterService) {
        this.gardenService = gardenService;
        this.sensorsService = sensorsService;
        this.notificationService = notificationService;
        this.waterService = waterService;
    }

    /**
     * Retrieve a list of all gardens associated with a user.
     *
     * @return A List of type {@link Garden}
     */
    @GetMapping(produces = "application/json")
    public List<Garden> getAll() {
        return gardenService.getAll();
    }

    /**
     * Create a new garden with details such as name, location and plant types.
     *
     * @param garden object
     * @return the id
     */
    @PostMapping
    public String addGarden(@RequestBody Garden garden) {
        return gardenService.add(garden);
    }

    /**
     * Retrieve the current temperature, humidity and light values for a specific garden.
     *
     * @param id of type {@link Long}
     * @return list of sensors
     */
    @GetMapping("/{garden_id}/sensors")
    public List<String> getSensors(@PathVariable("garden_id") Long id) {
        LOGGER.info("Garden {} sensors", id);
        return sensorsService.getAll();
    }

    /**
     * Trigger an irrigation cycle for a specific garden.
     *
     * @param id of type {@link Long}
     * @return n/a
     */
    @PostMapping("/{garden_id}/water")
    public String addWater(@PathVariable("garden_id") String id) {
        LOGGER.info("Garden {} water", id);
        return waterService.add(id);
    }

    /**
     * Triggers a pruning cycle for a specific garden.
     *
     * @param id of type {@link Long}
     * @return n/a
     */
    @PostMapping("/{garden_id}/prune")
    public String prune(@PathVariable("garden_id") String id) {
        LOGGER.info("Garden {} prune", id);
        return "Garden pruned";
    }

    /**
     * Retrieves a list of notifications for a specific garden, e.g. when it is time to water or prune.
     *
     * @param id of type {@link Long}
     * @return n/a
     */
    @GetMapping("/{garden_id}/notifications")
    public List<String> getNotifications(@PathVariable("garden_id") String id) {
        LOGGER.info("Garden {} notifications", id);
        return notificationService.getAll(id);
    }

    /**
     * Create a new notification for a specific garden.
     *
     * @param id of type {@link Long}
     * @return n/a
     */
    @PostMapping("/{garden_id}/notifications")
    public String addNotification(@PathVariable("garden_id") String id) {
        LOGGER.info("Garden {} notification", id);
        return "Notification added";
    }

}
