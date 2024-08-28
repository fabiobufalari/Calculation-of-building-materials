package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class InternalWallDTO {

    @NotBlank(message = "WallEntity identification is required")
    private String identifyWall;

    @NotBlank(message = "Intersection is required")
    private String intersection;

    @NotNull(message = "WallEntity length in feet is required")
    private Double wallLengthFoot;

    @NotNull(message = "WallEntity length in inches is required")
    private Double wallLengthInches;

    @NotNull(message = "WallEntity height in feet is required")
    private Double wallHeightFoot;

    @NotNull(message = "WallEntity height in inches is required")
    private Double wallHeightInches;

    @NotNull(message = "WallEntity thickness is required")
    private Double wallThickness;

    @NotBlank(message = "Side of wall is required")
    private String sideOfWall;

    @NotNull(message = "Stud spacing is required")
    private Double studSpacing;

    @NotNull(message = "Is wet area is required")
    private Boolean isWetArea;

    @NotNull(message = "Windows are required")
    private List<WindowDTO> windows;

    @NotNull(message = "Doors are required")
    private List<DoorDTO> doors;
}
