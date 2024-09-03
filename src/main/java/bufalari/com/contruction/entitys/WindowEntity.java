package bufalari.com.contruction.entitys;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class WindowEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double windowWidthFoot;
    private double windowWidthInches;
    private double windowHeightFoot;
    private double windowHeightInches;
    private double windowThickness;

    @ManyToOne
    @JoinColumn(name = "wall_id")
    private WallEntity wallEntity;
}
