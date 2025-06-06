package com.constructionhub.bimprocessing.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BIM File Entity
 * 
 * EN: Entity class representing a BIM file in the system.
 * Stores metadata about uploaded BIM files and their processing status.
 * 
 * PT: Classe de entidade que representa um arquivo BIM no sistema.
 * Armazena metadados sobre arquivos BIM carregados e seu status de processamento.
 */
@Entity
@Table(name = "bim_files")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BimFile {

    /**
     * EN: Unique identifier for the BIM file
     * PT: Identificador único para o arquivo BIM
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Original filename of the uploaded file
     * PT: Nome original do arquivo carregado
     */
    @Column(nullable = false)
    private String originalFilename;

    /**
     * EN: System-generated filename for storage
     * PT: Nome de arquivo gerado pelo sistema para armazenamento
     */
    @Column(nullable = false, unique = true)
    private String storedFilename;

    /**
     * EN: MIME type of the file
     * PT: Tipo MIME do arquivo
     */
    @Column(nullable = false)
    private String contentType;

    /**
     * EN: Size of the file in bytes
     * PT: Tamanho do arquivo em bytes
     */
    @Column(nullable = false)
    private Long fileSize;

    /**
     * EN: File extension (e.g., dwg, ifc)
     * PT: Extensão do arquivo (ex: dwg, ifc)
     */
    @Column(nullable = false)
    private String fileExtension;

    /**
     * EN: Type of BIM software that created the file
     * PT: Tipo de software BIM que criou o arquivo
     */
    @Column(name = "source_software")
    private String sourceSoftware;

    /**
     * EN: ID of the project this file belongs to
     * PT: ID do projeto ao qual este arquivo pertence
     */
    @Column(name = "project_id")
    private String projectId;

    /**
     * EN: ID of the user who uploaded the file
     * PT: ID do usuário que carregou o arquivo
     */
    @Column(name = "uploaded_by", nullable = false)
    private Long uploadedBy;

    /**
     * EN: Processing status of the file
     * PT: Status de processamento do arquivo
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProcessingStatus status;

    /**
     * EN: Error message if processing failed
     * PT: Mensagem de erro se o processamento falhou
     */
    @Column(name = "error_message")
    private String errorMessage;

    /**
     * EN: Path to the processed output file
     * PT: Caminho para o arquivo de saída processado
     */
    @Column(name = "output_path")
    private String outputPath;

    /**
     * EN: Date and time when the file was uploaded
     * PT: Data e hora em que o arquivo foi carregado
     */
    @Column(name = "uploaded_at", nullable = false, updatable = false)
    private LocalDateTime uploadedAt;

    /**
     * EN: Date and time when the file was processed
     * PT: Data e hora em que o arquivo foi processado
     */
    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    /**
     * EN: Processing status enum for BIM files
     * PT: Enum de status de processamento para arquivos BIM
     */
    public enum ProcessingStatus {
        /**
         * EN: File is pending processing
         * PT: Arquivo está pendente de processamento
         */
        PENDING,
        
        /**
         * EN: File is currently being processed
         * PT: Arquivo está sendo processado atualmente
         */
        PROCESSING,
        
        /**
         * EN: File was processed successfully
         * PT: Arquivo foi processado com sucesso
         */
        COMPLETED,
        
        /**
         * EN: File processing failed
         * PT: Processamento do arquivo falhou
         */
        FAILED,
        
        /**
         * EN: File format is not supported
         * PT: Formato do arquivo não é suportado
         */
        UNSUPPORTED_FORMAT
    }
}
