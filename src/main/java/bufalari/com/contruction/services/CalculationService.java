package bufalari.com.contruction.services;

import bufalari.com.contruction.dto.CalculationDTO;
import bufalari.com.contruction.dto.CalculationResponseDTO;
import bufalari.com.contruction.utils.CalculationUtils;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public CalculationResponseDTO calculateMaterials(CalculationDTO calculation, String traceId) {
        CalculationResponseDTO response = new CalculationResponseDTO();

        // Implement the calculation logic for each type of material
        response.setTopPlates(CalculationUtils.calculateTopPlates(calculation));
        response.setHeaders(CalculationUtils.calculateHeaders(calculation));
        response.setDrywalls(CalculationUtils.calculateDrywalls(calculation));
        response.setCeilings(CalculationUtils.calculateCeilings(calculation));
        response.setPlywoods(CalculationUtils.calculatePlywoods(calculation));

        return response;
    }
}
