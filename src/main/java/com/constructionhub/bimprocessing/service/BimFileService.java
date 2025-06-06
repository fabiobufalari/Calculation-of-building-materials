package com.constructionhub.bimprocessing.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.constructionhub.bimprocessing.dto.BimFileDTO;
import com.constructionhub.bimprocessing.exception.FileStorageException;
import com.constructionhub.bimprocessing.exception.ResourceNotFoundException;
import com.constructionhub.bimprocessing.model.BimFile;
import com.constructionhub.bimprocessing.model.BimFile.ProcessingStatus;
import com.constructionhub.bimprocessing.repository.BimFileRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BIM File Service
 * 
 * EN: Service for managing BIM file operations including upload, storage, and retrieval.
 * Handles file storage and database operations for BIM files.
 * 
 * PT: Serviço para gerenciar operações de arquivos BIM, incluindo upload, armazenamento e recuperação.
 * Lida com armazenamento de arquivos e operações de banco de dados para arquivos BIM.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BimFileService {

    private final BimFileRepository bimFileRepository;
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    /**
     * EN: Store a BIM file and create a database entry
     * PT: Armazenar um arquivo BIM e criar uma entrada no banco de dados
     * 
     * @param file EN: Multipart file to store / PT: Arquivo multipart para armazenar
     * @param projectId EN: ID of the project / PT: ID do projeto
     * @param userId EN: ID of the uploading user / PT: ID do usuário que está fazendo o upload
     * @return EN: Created BIM file entity / PT: Entidade de arquivo BIM criada
     * @throws FileStorageException EN: If file storage fails / PT: Se o armazenamento do arquivo falhar
     */
    public BimFile storeBimFile(MultipartFile file, String projectId, Long userId) {
        try {
            // Create upload directory if it doesn't exist
            Path uploadPath = Paths.get(uploadDir).toAbsolutePath().normalize();
            Files.createDirectories(uploadPath);
            
            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String fileExtension = FilenameUtils.getExtension(originalFilename).toLowerCase();
            String storedFilename = UUID.randomUUID().toString() + "." + fileExtension;
            
            // Copy file to upload directory
            Path targetLocation = uploadPath.resolve(storedFilename);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            
            // Create BIM file entity
            BimFile bimFile = BimFile.builder()
                .originalFilename(originalFilename)
                .storedFilename(storedFilename)
                .contentType(file.getContentType())
                .fileSize(file.getSize())
                .fileExtension(fileExtension)
                .projectId(projectId)
                .uploadedBy(userId)
                .status(ProcessingStatus.PENDING)
                .uploadedAt(LocalDateTime.now())
                .build();
            
            return bimFileRepository.save(bimFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + file.getOriginalFilename(), ex);
        }
    }
    
    /**
     * EN: Get all BIM files
     * PT: Obter todos os arquivos BIM
     * 
     * @return EN: List of all BIM files / PT: Lista de todos os arquivos BIM
     */
    public List<BimFile> getAllBimFiles() {
        return bimFileRepository.findAll();
    }
    
    /**
     * EN: Get BIM file by ID
     * PT: Obter arquivo BIM por ID
     * 
     * @param id EN: ID of the BIM file / PT: ID do arquivo BIM
     * @return EN: BIM file with the specified ID / PT: Arquivo BIM com o ID especificado
     * @throws ResourceNotFoundException EN: If file not found / PT: Se o arquivo não for encontrado
     */
    public BimFile getBimFileById(Long id) {
        return bimFileRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("BIM file not found with id: " + id));
    }
    
    /**
     * EN: Get BIM files by project ID
     * PT: Obter arquivos BIM por ID do projeto
     * 
     * @param projectId EN: Project ID to search for / PT: ID do projeto a ser pesquisado
     * @return EN: List of BIM files for the project / PT: Lista de arquivos BIM para o projeto
     */
    public List<BimFile> getBimFilesByProjectId(String projectId) {
        return bimFileRepository.findByProjectId(projectId);
    }
    
    /**
     * EN: Update BIM file status
     * PT: Atualizar status do arquivo BIM
     * 
     * @param id EN: ID of the BIM file / PT: ID do arquivo BIM
     * @param status EN: New processing status / PT: Novo status de processamento
     * @param errorMessage EN: Error message if status is FAILED / PT: Mensagem de erro se o status for FAILED
     * @return EN: Updated BIM file / PT: Arquivo BIM atualizado
     * @throws ResourceNotFoundException EN: If file not found / PT: Se o arquivo não for encontrado
     */
    public BimFile updateBimFileStatus(Long id, ProcessingStatus status, String errorMessage) {
        BimFile bimFile = getBimFileById(id);
        bimFile.setStatus(status);
        
        if (status == ProcessingStatus.FAILED && errorMessage != null) {
            bimFile.setErrorMessage(errorMessage);
        }
        
        if (status == ProcessingStatus.COMPLETED || status == ProcessingStatus.FAILED) {
            bimFile.setProcessedAt(LocalDateTime.now());
        }
        
        return bimFileRepository.save(bimFile);
    }
    
    /**
     * EN: Delete BIM file
     * PT: Excluir arquivo BIM
     * 
     * @param id EN: ID of the BIM file to delete / PT: ID do arquivo BIM a ser excluído
     * @throws ResourceNotFoundException EN: If file not found / PT: Se o arquivo não for encontrado
     */
    public void deleteBimFile(Long id) {
        BimFile bimFile = getBimFileById(id);
        
        try {
            // Delete physical file
            Path filePath = Paths.get(uploadDir).resolve(bimFile.getStoredFilename()).normalize();
            Files.deleteIfExists(filePath);
            
            // Delete database entry
            bimFileRepository.delete(bimFile);
        } catch (IOException ex) {
            log.error("Error deleting file: {}", ex.getMessage());
            // Continue with database deletion even if file deletion fails
            bimFileRepository.delete(bimFile);
        }
    }
    
    /**
     * EN: Convert BimFile entity to DTO
     * PT: Converter entidade BimFile para DTO
     * 
     * @param bimFile EN: BIM file entity / PT: Entidade de arquivo BIM
     * @return EN: BIM file DTO / PT: DTO de arquivo BIM
     */
    public BimFileDTO convertToDTO(BimFile bimFile) {
        return BimFileDTO.builder()
            .id(bimFile.getId())
            .originalFilename(bimFile.getOriginalFilename())
            .contentType(bimFile.getContentType())
            .fileSize(bimFile.getFileSize())
            .fileExtension(bimFile.getFileExtension())
            .sourceSoftware(bimFile.getSourceSoftware())
            .projectId(bimFile.getProjectId())
            .uploadedBy(bimFile.getUploadedBy())
            .status(bimFile.getStatus())
            .errorMessage(bimFile.getErrorMessage())
            .outputPath(bimFile.getOutputPath())
            .uploadedAt(bimFile.getUploadedAt())
            .processedAt(bimFile.getProcessedAt())
            .build();
    }
}
