package bufalari.com.contruction.services;


import bufalari.com.contruction.exceptions.CustomException;
import bufalari.com.contruction.entitys.CalculationEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculationService {

    public String calculateMaterials(CalculationEntity calculation, String traceId) throws CustomException {
        // Implement the calculation logic here
        // Convert feet and inches to meters or necessary units
        // Perform calculations for each wall, window, door, etc.
        try {
            // Your calculation logic here
            return "CalculationEntity successful.";
        } catch (Exception e) {
            throw new CustomException("Error during calculation", 400, traceId);
        }
    }
}
