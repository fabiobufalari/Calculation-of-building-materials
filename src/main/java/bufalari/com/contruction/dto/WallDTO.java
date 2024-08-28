package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class WallDTO {

    @NotBlank(message = "WallEntity name is required")
    private String wallName;

    @NotNull(message = "WallEntity length in feet is required")
    private Double wallLengthFoot;

    @NotNull(message = "WallEntity length in inches is required")
    private Double wallLengthInches;

    @NotNull(message = "WallEntity height in feet is required")
    private Double wallHeightFoot;

    @NotNull(message = "WallEntity height in inches is required")
    private Double wallHeightInches;

    @NotNull(message = "Wet area is required")
    private Double wetArea;

    @NotNull(message = "Windows are required")
    private List<WindowDTO> windows;

    @NotNull(message = "Doors are required")
    private List<DoorDTO> doors;
}
