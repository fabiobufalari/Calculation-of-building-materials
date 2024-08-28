package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class DoorDTO {

    @NotNull(message = "DoorEntity width in feet is required")
    private Double doorWidthFoot;

    @NotNull(message = "DoorEntity width in inches is required")
    private Double doorWidthInches;

    @NotNull(message = "DoorEntity height in feet is required")
    private Double doorHeightFoot;

    @NotNull(message = "DoorEntity height in inches is required")
    private Double doorHeightInches;

    @NotNull(message = "DoorEntity thickness is required")
    private Double doorThickness;
}
