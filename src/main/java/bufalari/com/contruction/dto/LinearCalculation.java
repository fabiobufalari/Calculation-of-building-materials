package bufalari.com.contruction.dto;

import lombok.Data;
import java.util.List;

@Data
public class LinearCalculation {
    private String wallIdentification;
    private List<Wall> walls;
}
