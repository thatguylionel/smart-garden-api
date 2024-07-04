package de.tgl.smartgarden.service;

import de.tgl.smartgarden.models.Sensor;
import de.tgl.smartgarden.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SensorService {
    List<Sensor> getAllByGardenId(Long gardenId);

    Sensor getSensor(Long sensorId);

    Sensor addSensor(Long gardenId, Sensor sensor);

    @Service
    class SensorServiceImpl implements SensorService {

        private final SensorRepository sensorRepository;

        @Autowired
        public SensorServiceImpl(SensorRepository sensorRepository) {
            this.sensorRepository = sensorRepository;
        }

        @Override
        public List<Sensor> getAllByGardenId(Long gardenId) {
            return sensorRepository.findAllByGardenId(gardenId);
        }

        @Override
        public Sensor getSensor(Long sensorId) {
            return sensorRepository.findById(sensorId)
                    .orElseThrow(() -> new RuntimeException("Sensor not found"));
        }

        @Override
        public Sensor addSensor(Long gardenId, Sensor sensor) {
            sensor.setGardenId(gardenId);
            return sensorRepository.save(sensor);
        }
    }
}