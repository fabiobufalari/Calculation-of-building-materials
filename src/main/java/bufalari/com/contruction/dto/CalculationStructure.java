package bufalari.com.contruction.dto;

import lombok.Data;
import java.util.List;

@Data
public class CalculationStructure {
    private String floorIdentification;
    private int idCalculation;
    private List<LinearCalculation> linearCalculationInternal;
    private List<LinearCalculation> linearCalculationExternal;
}
