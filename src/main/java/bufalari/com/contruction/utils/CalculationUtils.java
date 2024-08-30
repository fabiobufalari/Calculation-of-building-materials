package bufalari.com.contruction.utils;

import bufalari.com.contruction.dto.CalculationDTO;

public class CalculationUtils {

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
