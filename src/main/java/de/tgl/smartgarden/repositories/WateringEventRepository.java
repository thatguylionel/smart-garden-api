package de.tgl.smartgarden.repositories;


import de.tgl.smartgarden.models.WateringEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WateringEventRepository extends JpaRepository<WateringEvent, Long> {
    List<WateringEvent> findByGardenIdOrderByTimestampDesc(Long gardenId);
}
