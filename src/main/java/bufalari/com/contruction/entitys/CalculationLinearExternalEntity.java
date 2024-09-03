package bufalari.com.contruction.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class CalculationLinearExternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String wallIdentification;

    @ManyToOne
    @JoinColumn(name = "floor_identification_id", nullable = false)
    private FloorIdentificationEntity floorIdentification;

    @OneToMany(mappedBy = "calculationLinearExternalEntity", cascade = CascadeType.ALL)
    private List<WallEntity> wallEntities;
}
