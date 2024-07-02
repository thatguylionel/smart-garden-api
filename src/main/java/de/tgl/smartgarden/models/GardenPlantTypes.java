package de.tgl.smartgarden.models;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Entity(name = "Garden_PlantTypes")
@Data
public class GardenPlantTypes {
    @EmbeddedId
    private GardenPlantTypeId id;

    @ManyToOne
    @MapsId("gardenId")
    @JoinColumn(name = "Garden_id")
    private Garden garden;

    @ManyToOne
    @MapsId("plantTypeId")
    @JoinColumn(name = "PlantTypes_id")
    private PlantTypes plantType;

    @Embeddable
    @Data
    static
    class GardenPlantTypeId implements Serializable {
        @Column(name = "Garden_id")
        private Long gardenId;

        @Column(name = "PlantTypes_id")
        private Long plantTypeId;
    }
}
