package bufalari.com.contruction.controllers;

import bufalari.com.contruction.dto.CalculationDTO;
import bufalari.com.contruction.dto.CalculationResponseDTO;
import bufalari.com.contruction.services.CalculationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/construction")
public class ConstructionController {

    private final CalculationService calculationService;

    public ConstructionController(CalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<CalculationResponseDTO> calculateMaterials(@RequestBody CalculationDTO calculation) {
        String traceId = UUID.randomUUID().toString();
        try {
            CalculationResponseDTO response = calculationService.calculateMaterials(calculation, traceId);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new CalculationResponseDTO("Internal Server Error. TraceID: " + traceId));
        }
    }
}
