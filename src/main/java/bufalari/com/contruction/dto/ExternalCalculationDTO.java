package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExternalCalculationDTO {

    private String wallIdentification;
    private ExternalWallDTO walls;
}
