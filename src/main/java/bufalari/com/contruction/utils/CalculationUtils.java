package bufalari.com.contruction.utils;

import bufalari.com.contruction.dto.*;

import java.util.List;

public class CalculationUtils {

    public static double calculateTotalWallLength(CalculationDTO calculation) {
        double totalWallLength = 0.0;

        // Iterar sobre cada estrutura de cálculo fornecida
        for (FloorIdentificationDTO structure : calculation.getCalculationStructure()) {
            totalWallLength += internalCalculateWallLength(structure.getLinearCalculationInternal());
            totalWallLength += calculateWallLength(structure.getLinearCalculationExternal());
        }

        return totalWallLength;
    }

    private static double internalCalculateWallLength(List<CalculationInternalDTO> linearCalculations) {
        double wallLengthTotal = 0.0;

        for (CalculationInternalDTO calc : linearCalculations) {
            for (InternalWallDTO wall : calc.getWalls()) {
                double wallLengthFoot = wall.getTotalWallLengthFoot();
                double windowTotalFoot = calculateTotalWindowLength(wall.getWindows());
                double doorTotalFoot = calculateTotalDoorLength(wall.getDoors());

                // Subtrair o comprimento das janelas e portas do comprimento total da parede
                double effectiveWallLength = wallLengthFoot - (windowTotalFoot + doorTotalFoot);
                wallLengthTotal += effectiveWallLength;
            }
        }

        return wallLengthTotal;
    }

    private static double calculateWallLength(List<CalculationExternalDTO> linearCalculations) {
        double wallLengthTotal = 0.0;

        for (CalculationExternalDTO calc : linearCalculations) {
            for (ExternalWallDTO wall : calc.getWalls()) {
                double wallLengthFoot = wall.getTotalWallLengthFoot();
                double windowTotalFoot = calculateTotalWindowLength(wall.getWindows());
                double doorTotalFoot = calculateTotalDoorLength(wall.getDoors());

                // Subtrair o comprimento das janelas e portas do comprimento total da parede
                double effectiveWallLength = wallLengthFoot - (windowTotalFoot + doorTotalFoot);
                wallLengthTotal += effectiveWallLength;
            }
        }

        return wallLengthTotal;
    }

    private static double calculateTotalWindowLength(List<WindowDTO> windows) {
        double totalWindowLength = 0.0;
        for (WindowDTO window : windows) {
            totalWindowLength += window.getTotalWindowWidthFoot();
        }
        return totalWindowLength;
    }

    private static double calculateTotalDoorLength(List<DoorDTO> doors) {
        double totalDoorLength = 0.0;
        for (DoorDTO door : doors) {
            totalDoorLength += door.getTotalDoorWidthFoot();
        }
        return totalDoorLength;
    }

    public static double externalCalculateTotalDoorAndWindowLength(CalculationDTO calculation) {
        double totalDoorAndWindowLength = 0.0;

        // Iterar sobre cada estrutura de cálculo fornecida
        for (FloorIdentificationDTO structure : calculation.getCalculationStructure()) {
            totalDoorAndWindowLength += internalCalculateTotalDoorAndWindowLength(structure.getLinearCalculationInternal());
            totalDoorAndWindowLength += externalCalculateTotalDoorAndWindowLength(structure.getLinearCalculationExternal());
        }

        return totalDoorAndWindowLength;
    }

    private static double internalCalculateTotalDoorAndWindowLength(List<CalculationInternalDTO> linearCalculations) {
        double totalDoorAndWindowLength = 0.0;

        for (CalculationInternalDTO calc : linearCalculations) {
            for (InternalWallDTO wall : calc.getWalls()) {
                // Calcular o comprimento total das janelas
                totalDoorAndWindowLength += calculateTotalWindowLength(wall.getWindows());

                // Calcular o comprimento total das portas
                totalDoorAndWindowLength += calculateTotalDoorLength(wall.getDoors());
            }
        }

        return totalDoorAndWindowLength;
    }

    private static double externalCalculateTotalDoorAndWindowLength(List<CalculationExternalDTO> linearCalculations) {
        double totalDoorAndWindowLength = 0.0;

        for (CalculationExternalDTO calc : linearCalculations) {
            for (ExternalWallDTO wall : calc.getWalls()) {
                // Calcular o comprimento total das janelas
                totalDoorAndWindowLength += calculateTotalWindowLength(wall.getWindows());

                // Calcular o comprimento total das portas
                totalDoorAndWindowLength += calculateTotalDoorLength(wall.getDoors());
            }
        }

        return totalDoorAndWindowLength;
    }

    public static CalculationDTO calculateTotalLinearCalculations(CalculationDTO calculation) {
        double totalWallLength = calculateTotalWallLength(calculation);
        double totalDoorAndWindowLength = externalCalculateTotalDoorAndWindowLength(calculation);

        // Atualizar os valores totais de comprimento linear para saída
        calculation.setTotalLinearCalculationInternal(totalWallLength);
        calculation.setTotalLinearCalculationExternal(totalDoorAndWindowLength);

        return calculation;
    }

    public static int calculateTopPlates(CalculationDTO calculation) {
        double totalLinearFeet = convertToFeet(calculation.getTotalLinearInches());
        int plateSize = calculation.getPlateSize();
        return (int) Math.ceil((totalLinearFeet * 3) / plateSize);
    }

    public static int calculateHeaders(CalculationDTO calculation) {
        double totalLength = convertToFeet(calculation.getTotalHeaderLengthInches());
        int headerSize = calculation.getHeaderSize();
        return (int) Math.ceil(totalLength / headerSize);
    }

    public static int calculateDrywalls(CalculationDTO calculation) {
        double totalArea = calculateArea(calculation.getWallHeight(), calculation.getWallWidth());
        int drywallSize = calculation.getDrywallSize();
        return (int) Math.ceil(totalArea / drywallSize);
    }

    public static int calculateCeilings(CalculationDTO calculation) {
        double totalArea = calculateArea(calculation.getCeilingHeight(), calculation.getCeilingWidth());
        int ceilingSize = calculation.getCeilingSize();
        return (int) Math.ceil(totalArea / ceilingSize);
    }

    public static int calculatePlywoods(CalculationDTO calculation) {
        double totalArea = calculateArea(calculation.getPlywoodHeight(), calculation.getPlywoodWidth());
        int plywoodSize = calculation.getPlywoodSize();
        return (int) Math.ceil(totalArea / plywoodSize);
    }

    public static double convertToFeet(double inches) {
        return inches / 12;
    }

    public static double calculateArea(double height, double width) {
        return height * width;
    }
}
