package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@NoArgsConstructor
public class CalculationDTO {

    @NotNull(message = "Client information is required")
    private ClientDTO clientDTO;

    @NotNull(message = "Attendant information is required")
    private AttendentDTO atendentDTO;

    @NotNull(message = "Company information is required")
    private CompanyDTO companyDTO;

    @NotNull(message = "Calculation structure is required")
    private List<FloorIdentificationDTO> calculationStructure;

    // Adicionando os campos necessários para os cálculos
    private double totalLinearInches;
    private int plateSize;
    private double totalHeaderLengthInches;
    private int headerSize;
    private double wallHeight;
    private double wallWidth;
    private int drywallSize;
    private double ceilingHeight;
    private double ceilingWidth;
    private int ceilingSize;
    private double plywoodHeight;
    private double plywoodWidth;
    private int plywoodSize;
}
