package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class FloorIdentificationDTO {

    @NotBlank(message = "Floor identification is required")
    private String floorIdentification;

    @NotNull(message = "ID of calculation is required")
    private Long idCalculation;

    @NotNull(message = "CalculationInternal details are required")
    private List<CalculationInternalDTO> linearCalculationInternal;

    @NotNull(message = "CalculationExternal details are required")
    private List<CalculationExternalDTO> linearCalculationExternal;

    @NotNull(message = "CalculationLinear details are required")
    private List<CalculationLinearExternalDTO> calculationLinear;

    private double totalLinearCalculationExternal;

    private double totalWalls;
}
