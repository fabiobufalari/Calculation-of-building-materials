package com.constructionhub.bimprocessing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.constructionhub.bimprocessing.model.BimFile;
import com.constructionhub.bimprocessing.model.BimFile.ProcessingStatus;

/**
 * BIM File Repository
 * 
 * EN: Repository interface for BIM file entity operations.
 * Provides methods to interact with the bim_files table in the database.
 * 
 * PT: Interface de repositório para operações da entidade de arquivo BIM.
 * Fornece métodos para interagir com a tabela bim_files no banco de dados.
 */
@Repository
public interface BimFileRepository extends JpaRepository<BimFile, Long> {
    
    /**
     * EN: Find BIM files by project ID
     * PT: Encontrar arquivos BIM por ID do projeto
     * 
     * @param projectId EN: Project ID to search for / PT: ID do projeto a ser pesquisado
     * @return EN: List of BIM files for the project / PT: Lista de arquivos BIM para o projeto
     */
    List<BimFile> findByProjectId(String projectId);
    
    /**
     * EN: Find BIM files by processing status
     * PT: Encontrar arquivos BIM por status de processamento
     * 
     * @param status EN: Processing status to search for / PT: Status de processamento a ser pesquisado
     * @return EN: List of BIM files with the specified status / PT: Lista de arquivos BIM com o status especificado
     */
    List<BimFile> findByStatus(ProcessingStatus status);
    
    /**
     * EN: Find BIM files by uploader user ID
     * PT: Encontrar arquivos BIM por ID do usuário que fez o upload
     * 
     * @param uploadedBy EN: User ID of uploader / PT: ID do usuário que fez o upload
     * @return EN: List of BIM files uploaded by the user / PT: Lista de arquivos BIM carregados pelo usuário
     */
    List<BimFile> findByUploadedBy(Long uploadedBy);
    
    /**
     * EN: Find BIM file by stored filename
     * PT: Encontrar arquivo BIM pelo nome de arquivo armazenado
     * 
     * @param storedFilename EN: Stored filename to search for / PT: Nome de arquivo armazenado a ser pesquisado
     * @return EN: Optional containing the BIM file if found / PT: Optional contendo o arquivo BIM se encontrado
     */
    Optional<BimFile> findByStoredFilename(String storedFilename);
    
    /**
     * EN: Find BIM files by project ID and processing status
     * PT: Encontrar arquivos BIM por ID do projeto e status de processamento
     * 
     * @param projectId EN: Project ID to search for / PT: ID do projeto a ser pesquisado
     * @param status EN: Processing status to search for / PT: Status de processamento a ser pesquisado
     * @return EN: List of BIM files matching criteria / PT: Lista de arquivos BIM que correspondem aos critérios
     */
    List<BimFile> findByProjectIdAndStatus(String projectId, ProcessingStatus status);
    
    /**
     * EN: Find BIM files by file extension
     * PT: Encontrar arquivos BIM por extensão de arquivo
     * 
     * @param fileExtension EN: File extension to search for / PT: Extensão de arquivo a ser pesquisada
     * @return EN: List of BIM files with the specified extension / PT: Lista de arquivos BIM com a extensão especificada
     */
    List<BimFile> findByFileExtension(String fileExtension);
}
