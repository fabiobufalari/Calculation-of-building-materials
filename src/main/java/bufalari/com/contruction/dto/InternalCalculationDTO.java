package bufalari.com.contruction.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InternalCalculationDTO {

    private String wallIdentification;
    private InternalWallDTO walls;
}
