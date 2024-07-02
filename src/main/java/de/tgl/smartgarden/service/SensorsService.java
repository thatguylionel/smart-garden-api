package de.tgl.smartgarden.service;

import org.springframework.stereotype.Service;

import java.util.List;

public interface SensorsService {
    List<String> getAll();

    String getSensor(int sensorId);

    String addSensor(String sensor);

    @Service
    class SensorsServiceImpl implements SensorsService {

        @Override
        public List<String> getAll() {
            return List.of("Sensor 1", "Sensor 2", "Sensor 3", "Sensor 4");
        }

        @Override
        public String getSensor(int sensorId) {
            return "";
        }

        @Override
        public String addSensor(String sensor) {
            return "";
        }
    }
}
