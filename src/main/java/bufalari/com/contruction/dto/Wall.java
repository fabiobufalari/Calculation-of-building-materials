package bufalari.com.contruction.dto;

import lombok.Data;
import java.util.List;

@Data
public class Wall {
    private String identifyWall;
    private String intersection;
    private double totalWallLengthFoot;
    private double totalWallHeightFoot;
    private double wallThickness;
    private String sideOfWall;
    private double studSpacing;
    private boolean isWetArea;
    private List<Window> windows;
    private List<Door> doors;
}
