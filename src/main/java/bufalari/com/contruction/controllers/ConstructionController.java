package bufalari.com.contruction.controllers;

import bufalari.com.contruction.entitys.CalculationEntity;
import bufalari.com.contruction.exceptions.CustomException;
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
    public ResponseEntity<?> calculateMaterials(@RequestBody CalculationEntity calculation) {
        String traceId = UUID.randomUUID().toString();
        try {
            return ResponseEntity.ok(calculationService.calculateMaterials(calculation, traceId));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage() + " TraceID: " + e.getTraceId());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Internal Server Error. TraceID: " + traceId);
        }
    }
}
