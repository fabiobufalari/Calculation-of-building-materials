package bufalari.com.contruction.entitys;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CalculationInternalEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String wallIdentification;

    @OneToMany(mappedBy = "calculationInternalEntity", cascade = CascadeType.ALL)
    private List<WallEntity> walls;
}
