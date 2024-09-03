package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FloorCalculationDTO {

    private String floorIdentification;
    private Long idCalculation;
    private List<InternalCalculationDTO> linearCalculationInternal;
    private List<ExternalCalculationDTO> linearCalculationExternal;
}
