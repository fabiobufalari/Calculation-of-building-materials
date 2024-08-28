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

    @NotNull(message = "CalculationLinear details are required")
    private List<CalculationLinearExternalDTO> calculationLinear;

    @NotNull(message = "CalculationInternal details are required")
    private List<CalculationInternalDTO> calculationInternal;

    @NotNull(message = "CalculationExternal details are required")
    private List<CalculationExternalDTO> calculationExternal;
}
