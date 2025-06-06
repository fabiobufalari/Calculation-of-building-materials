package com.constructionhub.bimprocessing.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.constructionhub.bimprocessing.dto.BimFileDTO;
import com.constructionhub.bimprocessing.model.BimFile;
import com.constructionhub.bimprocessing.model.User;
import com.constructionhub.bimprocessing.repository.UserRepository;
import com.constructionhub.bimprocessing.service.BimFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BIM File Controller
 * 
 * EN: REST controller for BIM file operations.
 * Provides endpoints for uploading, retrieving, and managing BIM files.
 * 
 * PT: Controlador REST para operações de arquivos BIM.
 * Fornece endpoints para upload, recuperação e gerenciamento de arquivos BIM.
 */
@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "BIM Files", description = "API for BIM file management")
public class BimFileController {

    private final BimFileService bimFileService;
    private final UserRepository userRepository;
    
    /**
     * EN: Upload a new BIM file
     * PT: Fazer upload de um novo arquivo BIM
     * 
     * @param file EN: File to upload / PT: Arquivo para upload
     * @param projectId EN: Project ID / PT: ID do projeto
     * @param userDetails EN: Authenticated user details / PT: Detalhes do usuário autenticado
     * @return EN: Response with created BIM file / PT: Resposta com arquivo BIM criado
     */
    @PostMapping("/upload")
    @Operation(summary = "Upload a BIM file", description = "Uploads a new BIM file and queues it for processing")
    public ResponseEntity<BimFileDTO> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("projectId") String projectId,
            @AuthenticationPrincipal UserDetails userDetails) {
        
        log.info("Uploading file: {} for project: {}", file.getOriginalFilename(), projectId);
        
        // Get user ID from authenticated user
        User user = userRepository.findByUsername(userDetails.getUsername())
            .orElseThrow(() -> new IllegalStateException("Authenticated user not found in database"));
        
        BimFile bimFile = bimFileService.storeBimFile(file, projectId, user.getId());
        return new ResponseEntity<>(bimFileService.convertToDTO(bimFile), HttpStatus.CREATED);
    }
    
    /**
     * EN: Get all BIM files
     * PT: Obter todos os arquivos BIM
     * 
     * @return EN: List of all BIM files / PT: Lista de todos os arquivos BIM
     */
    @GetMapping
    @Operation(summary = "Get all BIM files", description = "Retrieves all BIM files in the system")
    public ResponseEntity<List<BimFileDTO>> getAllFiles() {
        List<BimFileDTO> files = bimFileService.getAllBimFiles().stream()
            .map(bimFileService::convertToDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(files);
    }
    
    /**
     * EN: Get BIM file by ID
     * PT: Obter arquivo BIM por ID
     * 
     * @param id EN: BIM file ID / PT: ID do arquivo BIM
     * @return EN: BIM file with specified ID / PT: Arquivo BIM com ID especificado
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get BIM file by ID", description = "Retrieves a specific BIM file by its ID")
    public ResponseEntity<BimFileDTO> getFileById(@PathVariable Long id) {
        BimFile bimFile = bimFileService.getBimFileById(id);
        return ResponseEntity.ok(bimFileService.convertToDTO(bimFile));
    }
    
    /**
     * EN: Get BIM files by project ID
     * PT: Obter arquivos BIM por ID do projeto
     * 
     * @param projectId EN: Project ID / PT: ID do projeto
     * @return EN: List of BIM files for the project / PT: Lista de arquivos BIM para o projeto
     */
    @GetMapping("/project/{projectId}")
    @Operation(summary = "Get BIM files by project", description = "Retrieves all BIM files associated with a specific project")
    public ResponseEntity<List<BimFileDTO>> getFilesByProject(@PathVariable String projectId) {
        List<BimFileDTO> files = bimFileService.getBimFilesByProjectId(projectId).stream()
            .map(bimFileService::convertToDTO)
            .collect(Collectors.toList());
        
        return ResponseEntity.ok(files);
    }
    
    /**
     * EN: Delete BIM file
     * PT: Excluir arquivo BIM
     * 
     * @param id EN: BIM file ID to delete / PT: ID do arquivo BIM para excluir
     * @return EN: Response with no content / PT: Resposta sem conteúdo
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete BIM file", description = "Deletes a BIM file and its associated data")
    public ResponseEntity<Void> deleteFile(@PathVariable Long id) {
        bimFileService.deleteBimFile(id);
        return ResponseEntity.noContent().build();
    }
}
