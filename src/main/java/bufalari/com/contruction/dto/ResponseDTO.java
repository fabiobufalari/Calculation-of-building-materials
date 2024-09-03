package bufalari.com.contruction.dto;

import lombok.Data;
import java.util.List;

@Data
public class ResponseDTO {
    private ClientDTO clientDTO;
    private AttendentDTO atendentDTO;
    private CompanyDTO companyDTO;
    private List<CalculationStructure> calculationStructure;
    private double totalLinearCalculationExternal;
    private double totalLinearCalculationInternal;
}
