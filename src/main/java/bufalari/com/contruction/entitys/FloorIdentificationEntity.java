package bufalari.com.contruction.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "floor_identification")
@Data
@NoArgsConstructor
public class FloorIdentificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String floorIdentification;

    @ManyToOne
    @JoinColumn(name = "calculation_id", nullable = false)
    private CalculationEntity calculation;

    @OneToMany(mappedBy = "floorIdentification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalculationLinearExternalEntity> calculationLinear;

    @OneToMany(mappedBy = "floorIdentification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalculationInternalEntity> calculationInternal;

    @OneToMany(mappedBy = "floorIdentification", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CalculationExternalEntity> calculationExternal;
}
