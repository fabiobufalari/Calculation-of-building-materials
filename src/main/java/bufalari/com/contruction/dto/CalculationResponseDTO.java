package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CalculationResponseDTO {


    private ClientDTO clientDTO;
    private AttendentDTO attendentDTO;
    private CompanyDTO companyDTO;
    private List<FloorCalculationDTO> calculationStructure;
    private double totalLinearCalculationInternal;
    private double totalLinearCalculationExternal;
    private String errorMessage;

    public CalculationResponseDTO(String errorMessage) {
      this.errorMessage = errorMessage;
    }
}
