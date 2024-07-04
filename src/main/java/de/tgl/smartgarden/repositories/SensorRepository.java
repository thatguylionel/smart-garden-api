package de.tgl.smartgarden.repositories;

import de.tgl.smartgarden.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    List<Sensor> findAllByGardenId(Long gardenId);
}
