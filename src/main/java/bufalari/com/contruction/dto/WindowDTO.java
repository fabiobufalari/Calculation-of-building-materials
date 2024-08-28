package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class WindowDTO {

    @NotNull(message = "WindowEntity width in feet is required")
    private Double windowWidthFoot;

    @NotNull(message = "WindowEntity width in inches is required")
    private Double windowWidthInches;

    @NotNull(message = "WindowEntity height in feet is required")
    private Double windowHeightFoot;

    @NotNull(message = "WindowEntity height in inches is required")
    private Double windowHeightInches;

    @NotNull(message = "WindowEntity thickness is required")
    private Double windowThickness;
}
