package de.tgl.smartgarden.repositories;

import de.tgl.smartgarden.models.PlantTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantTypesRepository extends JpaRepository<PlantTypes, Long> {
}
