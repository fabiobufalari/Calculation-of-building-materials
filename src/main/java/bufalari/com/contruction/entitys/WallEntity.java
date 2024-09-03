package bufalari.com.contruction.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class WallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String wallName;
    private double wallLengthFoot;
    private double wallLengthInches;
    private double wallHeightFoot;
    private double wallHeightInches;
    private double wetArea;

    @OneToMany(mappedBy = "wallEntity")
    private List<WindowEntity> windows;

    @OneToMany(mappedBy = "wallEntity")
    private List<DoorEntity> doors;

    @ManyToOne
    @JoinColumn(name = "calculation_internal_id")
    private CalculationInternalEntity calculationInternalEntity;

    @ManyToOne
    @JoinColumn(name = "calculation_external_id")
    private CalculationExternalEntity calculationExternalEntity;
}
