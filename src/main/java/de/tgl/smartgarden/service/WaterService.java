package de.tgl.smartgarden.service;

import org.springframework.stereotype.Service;

public interface WaterService {

    String add(String gardenid);

    @Service
    class WaterServiceImpl implements WaterService {
        @Override
        public String add(String gardenid) {
            return "Water added";
        }
    }
}
