package bufalari.com.contruction.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CalculationExternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String wallIdentification;

    @OneToMany(mappedBy = "calculationExternalEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WallEntity> walls;
}
