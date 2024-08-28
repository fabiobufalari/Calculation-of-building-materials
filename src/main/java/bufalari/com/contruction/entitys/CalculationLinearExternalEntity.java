package bufalari.com.contruction.entitys;

import javax.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CalculationLinearExternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String wallIdentification;

    @ManyToOne
    @JoinColumn(name = "floor_identification_id", nullable = false)
    private FloorIdentificationEntity floorIdentification; // Relacionamento com FloorIdentificationEntity

    @OneToMany(mappedBy = "calculationLinearExternalEntity", cascade = CascadeType.ALL)
    private List<WallEntity> wallEntities;

}
