package bufalari.com.contruction.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class DoorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double doorWidthFoot;
    private double doorWidthInches;
    private double doorHeightFoot;
    private double doorHeightInches;
    private double doorThickness;

    @ManyToOne
    @JoinColumn(name = "wall_id") // Ensure the column name matches the field in the database
    private WallEntity wallEntity;
}
