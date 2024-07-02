package de.tgl.smartgarden.service;

import de.tgl.smartgarden.models.Garden;
import de.tgl.smartgarden.repositories.GardenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface GardenService {
    List<Garden> getAll();

    String add(Garden garden);

    @Service
    class GardenServiceImpl implements GardenService {
        private final GardenRepository gardenRepository;

        public GardenServiceImpl(GardenRepository gardenRepository) {
            this.gardenRepository = gardenRepository;
        }

        @Override
        public List<Garden> getAll() {
            return gardenRepository.findAll();
        }

        @Override
        public String add(Garden garden) {
            gardenRepository.save(garden);
            return String.valueOf(garden.getId());
        }

    }
}
