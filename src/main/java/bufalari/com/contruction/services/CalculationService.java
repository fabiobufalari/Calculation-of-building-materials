package bufalari.com.contruction.services;

import bufalari.com.contruction.dto.*;
import bufalari.com.contruction.utils.CalculationUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CalculationService {

    public CalculationResponseDTO calculateMaterials(CalculationDTO calculation, String traceId) {
        CalculationResponseDTO response = new CalculationResponseDTO();

        double totalInternalWallLength = 0.0;
        double totalExternalWallLength = 0.0;

        List<FloorCalculationDTO> floorCalculations = new ArrayList<>();

        // Iterar sobre cada estrutura de cálculo fornecida
        for (FloorIdentificationDTO floor : calculation.getCalculationStructure()) {
            FloorCalculationDTO floorCalculation = new FloorCalculationDTO();
            floorCalculation.setFloorIdentification(floor.getFloorIdentification());
            floorCalculation.setIdCalculation(floor.getIdCalculation());

            List<InternalCalculationDTO> internalCalculations = new ArrayList<>();
            for (CalculationInternalDTO internalCalc : floor.getLinearCalculationInternal()) {
                InternalCalculationDTO internalCalculation = new InternalCalculationDTO();
                internalCalculation.setWallIdentification(internalCalc.getWallIdentification());

                double wallLengthFoot = 0.0;
                double wallHeightFoot = 0.0;

                // Somar comprimento e altura de todas as paredes internas
                for (InternalWallDTO wall : internalCalc.getWalls()) {
                    wallLengthFoot += wall.getTotalWallLengthFoot();
                    wallHeightFoot += wall.getTotalWallHeightFoot();
                }

                InternalWallDTO internalWall = new InternalWallDTO();
                internalWall.setTotalWallLengthFoot(wallLengthFoot);
                internalWall.setTotalWallHeightFoot(wallHeightFoot);

                internalCalculation.setWalls(internalWall);
                internalCalculations.add(internalCalculation);

                totalInternalWallLength += wallLengthFoot;
            }
            floorCalculation.setLinearCalculationInternal(internalCalculations);

            List<ExternalCalculationDTO> externalCalculations = new ArrayList<>();
            for (CalculationExternalDTO externalCalc : floor.getLinearCalculationExternal()) {
                ExternalCalculationDTO externalCalculation = new ExternalCalculationDTO();
                externalCalculation.setWallIdentification(externalCalc.getWallIdentification());

                double wallLengthFoot = 0.0;
                double wallHeightFoot = 0.0;

                // Somar comprimento e altura de todas as paredes externas
                for (ExternalWallDTO wall : externalCalc.getWalls()) {
                    wallLengthFoot += wall.getTotalWallLengthFoot();
                    wallHeightFoot += wall.getTotalWallHeightFoot();
                }

                ExternalWallDTO externalWall = new ExternalWallDTO();
                externalWall.setTotalWallLengthFoot(wallLengthFoot);
                externalWall.setTotalWallHeightFoot(wallHeightFoot);

                externalCalculation.setWalls(externalWall);
                externalCalculations.add(externalCalculation);

                totalExternalWallLength += wallLengthFoot;
            }
            floorCalculation.setLinearCalculationExternal(externalCalculations);
            floorCalculations.add(floorCalculation);
        }

        response.setCalculationStructure(floorCalculations);
        response.setTotalLinearCalculationInternal(totalInternalWallLength);
        response.setTotalLinearCalculationExternal(totalExternalWallLength);

        // Definir os outros campos do response
        response.setClientDTO(calculation.getClientDTO());
        response.setAttendentDTO(calculation.getAtendentDTO());
        response.setCompanyDTO(calculation.getCompanyDTO());

        return response;
    }
}
