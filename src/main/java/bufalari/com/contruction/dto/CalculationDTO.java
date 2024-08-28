package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CalculationDTO {

    @NotNull(message = "Calculation structure is required")
    private List<FloorIdentificationDTO> calculationStructure;
}