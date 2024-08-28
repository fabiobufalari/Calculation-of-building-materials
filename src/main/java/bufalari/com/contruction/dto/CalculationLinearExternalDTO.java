package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CalculationLinearExternalDTO {

    @NotBlank(message = "WallEntity identification is required")
    private String wallIdentification;

    @NotNull(message = "Walls are required")
    private List<WallDTO> walls;
}
