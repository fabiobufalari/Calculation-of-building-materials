package bufalari.com.contruction.entitys;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "calculation")
@Data
@NoArgsConstructor
public class CalculationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "calculation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FloorIdentificationEntity> floorIdentifications;
}
