package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ExternalWallDTO {

    private String identifyWall;
    private String intersection;
    private double wallLengthFoot; // Campo adicionado
    private double wallHeightFoot; // Campo adicionado
    private double totalWallLengthFoot;
    private double totalWallHeightFoot;
    private double wallThickness;
    private String sideOfWall;
    private double studSpacing;
    private boolean isWetArea;
    private List<WindowDTO> windows;
    private List<DoorDTO> doors;

    // Getters e setters gerados automaticamente pelo Lombok
}
