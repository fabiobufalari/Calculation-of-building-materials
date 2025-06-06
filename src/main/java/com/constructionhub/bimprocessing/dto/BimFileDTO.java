package com.constructionhub.bimprocessing.dto;

import java.time.LocalDateTime;

import com.constructionhub.bimprocessing.model.BimFile.ProcessingStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * BIM File DTO
 * 
 * EN: Data Transfer Object for BIM file information.
 * Used for transferring BIM file data between layers without exposing entity details.
 * 
 * PT: Objeto de Transferência de Dados para informações de arquivo BIM.
 * Usado para transferir dados de arquivo BIM entre camadas sem expor detalhes da entidade.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BimFileDTO {

    /**
     * EN: Unique identifier for the BIM file
     * PT: Identificador único para o arquivo BIM
     */
    private Long id;

    /**
     * EN: Original filename of the uploaded file
     * PT: Nome original do arquivo carregado
     */
    private String originalFilename;

    /**
     * EN: MIME type of the file
     * PT: Tipo MIME do arquivo
     */
    private String contentType;

    /**
     * EN: Size of the file in bytes
     * PT: Tamanho do arquivo em bytes
     */
    private Long fileSize;

    /**
     * EN: File extension (e.g., dwg, ifc)
     * PT: Extensão do arquivo (ex: dwg, ifc)
     */
    private String fileExtension;

    /**
     * EN: Type of BIM software that created the file
     * PT: Tipo de software BIM que criou o arquivo
     */
    private String sourceSoftware;

    /**
     * EN: ID of the project this file belongs to
     * PT: ID do projeto ao qual este arquivo pertence
     */
    private String projectId;

    /**
     * EN: ID of the user who uploaded the file
     * PT: ID do usuário que carregou o arquivo
     */
    private Long uploadedBy;

    /**
     * EN: Processing status of the file
     * PT: Status de processamento do arquivo
     */
    private ProcessingStatus status;

    /**
     * EN: Error message if processing failed
     * PT: Mensagem de erro se o processamento falhou
     */
    private String errorMessage;

    /**
     * EN: Path to the processed output file
     * PT: Caminho para o arquivo de saída processado
     */
    private String outputPath;

    /**
     * EN: Date and time when the file was uploaded
     * PT: Data e hora em que o arquivo foi carregado
     */
    private LocalDateTime uploadedAt;

    /**
     * EN: Date and time when the file was processed
     * PT: Data e hora em que o arquivo foi processado
     */
    private LocalDateTime processedAt;
}
