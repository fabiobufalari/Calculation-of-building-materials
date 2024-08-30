package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CalculationResponseDTO {
    private ClientDTO clientDTO;
    private AttendentDTO atendentDTO;
    private CompanyDTO companyDTO;
    private int topPlates;
    private int headers;
    private int drywalls;
    private int ceilings;
    private int plywoods;
    private String errorMessage;

    public CalculationResponseDTO(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
