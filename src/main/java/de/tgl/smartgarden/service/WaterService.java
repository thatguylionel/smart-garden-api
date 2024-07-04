package de.tgl.smartgarden.service;

import de.tgl.smartgarden.models.WateringEvent;
import de.tgl.smartgarden.repositories.WateringEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

public interface WaterService {
    WateringEvent waterGarden(Long gardenId, double waterAmount);

    List<WateringEvent> getWateringHistory(Long gardenId);

    void scheduleWatering(Long gardenId, LocalDateTime time);

    @Service
    public class WaterServiceImpl implements WaterService {
        private static final Logger logger = LoggerFactory.getLogger(WaterServiceImpl.class);

        private final WateringEventRepository wateringEventRepository;
        private final SensorService sensorService;

        public WaterServiceImpl(WateringEventRepository wateringEventRepository, SensorService sensorService) {
            this.wateringEventRepository = wateringEventRepository;
            this.sensorService = sensorService;
        }

        @Override
        public WateringEvent waterGarden(Long gardenId, double waterAmount) {
            // TODO: Implement soil moisture check when SensorService is updated
            // For now, we'll just log a message
            logger.info("Watering garden {} with {} units of water", gardenId, waterAmount);

            // Simulate watering process
            try {
                Thread.sleep(1000); // Simulate a delay for watering
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                logger.error("Watering process was interrupted", e);
            }

            WateringEvent event = new WateringEvent(gardenId, waterAmount, LocalDateTime.now());
            return wateringEventRepository.save(event);
        }

        @Override
        public List<WateringEvent> getWateringHistory(Long gardenId) {
            return wateringEventRepository.findByGardenIdOrderByTimestampDesc(gardenId);
        }

        @Override
        public void scheduleWatering(Long gardenId, LocalDateTime time) {
            // TODO: Implement scheduling logic
            logger.info("Scheduled watering for garden {} at {}", gardenId, time);
        }
    }
}

