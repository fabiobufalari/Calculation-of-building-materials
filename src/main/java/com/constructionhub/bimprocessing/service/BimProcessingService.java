package com.constructionhub.bimprocessing.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.constructionhub.bimprocessing.model.BimFile;
import com.constructionhub.bimprocessing.model.BimFile.ProcessingStatus;
import com.constructionhub.bimprocessing.parser.BimParser;
import com.constructionhub.bimprocessing.repository.BimFileRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * BIM Processing Service
 * 
 * EN: Service for processing BIM files using appropriate parsers.
 * Handles the asynchronous processing of uploaded BIM files and extraction of construction data.
 * 
 * PT: Serviço para processamento de arquivos BIM usando analisadores apropriados.
 * Lida com o processamento assíncrono de arquivos BIM carregados e extração de dados de construção.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class BimProcessingService {

    private final BimFileRepository bimFileRepository;
    private final BimFormatDetectionService formatDetectionService;
    private final List<BimParser> parsers;
    
    @Value("${file.upload-dir}")
    private String uploadDir;
    
    private final Map<String, BimParser> parserMap = new HashMap<>();
    
    /**
     * EN: Initialize the processing service
     * PT: Inicializar o serviço de processamento
     */
    @PostConstruct
    public void init() {
        // Map parsers by name
        for (BimParser parser : parsers) {
            parserMap.put(parser.getParserName(), parser);
            log.info("Registered BIM parser: {} for extensions: {}", 
                    parser.getParserName(), String.join(", ", parser.getSupportedExtensions()));
        }
    }
    
    /**
     * EN: Process a BIM file asynchronously
     * PT: Processar um arquivo BIM de forma assíncrona
     * 
     * @param fileId EN: ID of the BIM file to process / PT: ID do arquivo BIM para processar
     * @return EN: CompletableFuture for the processing task / PT: CompletableFuture para a tarefa de processamento
     */
    @Async
    public CompletableFuture<BimFile> processBimFile(Long fileId) {
        BimFile bimFile = bimFileRepository.findById(fileId)
            .orElseThrow(() -> new IllegalArgumentException("BIM file not found with id: " + fileId));
        
        log.info("Starting processing of BIM file: {}", bimFile.getOriginalFilename());
        
        try {
            // Update status to PROCESSING
            bimFile.setStatus(ProcessingStatus.PROCESSING);
            bimFileRepository.save(bimFile);
            
            // Detect appropriate parser
            String parserName = formatDetectionService.detectParser(bimFile);
            if (parserName == null) {
                bimFile.setStatus(ProcessingStatus.UNSUPPORTED_FORMAT);
                bimFile.setErrorMessage("Unsupported file format: " + bimFile.getFileExtension());
                return CompletableFuture.completedFuture(bimFileRepository.save(bimFile));
            }
            
            // Get parser
            BimParser parser = parserMap.get(parserName);
            if (parser == null) {
                bimFile.setStatus(ProcessingStatus.FAILED);
                bimFile.setErrorMessage("No parser available for format: " + bimFile.getFileExtension());
                return CompletableFuture.completedFuture(bimFileRepository.save(bimFile));
            }
            
            // Get file path
            Path filePath = Paths.get(uploadDir).resolve(bimFile.getStoredFilename());
            File file = filePath.toFile();
            
            // Parse file
            Map<String, Object> result = parser.parseFile(file);
            
            // Process result (in a real implementation, this would save the extracted data)
            String outputPath = processParsingResult(bimFile, result);
            bimFile.setOutputPath(outputPath);
            
            // Update status to COMPLETED
            bimFile.setStatus(ProcessingStatus.COMPLETED);
            return CompletableFuture.completedFuture(bimFileRepository.save(bimFile));
            
        } catch (Exception e) {
            log.error("Error processing BIM file: {}", bimFile.getOriginalFilename(), e);
            bimFile.setStatus(ProcessingStatus.FAILED);
            bimFile.setErrorMessage("Processing error: " + e.getMessage());
            return CompletableFuture.completedFuture(bimFileRepository.save(bimFile));
        }
    }
    
    /**
     * EN: Process the parsing result and generate output
     * PT: Processar o resultado da análise e gerar saída
     * 
     * @param bimFile EN: BIM file being processed / PT: Arquivo BIM sendo processado
     * @param result EN: Parsing result / PT: Resultado da análise
     * @return EN: Path to the output file / PT: Caminho para o arquivo de saída
     */
    private String processParsingResult(BimFile bimFile, Map<String, Object> result) {
        // This is a placeholder implementation
        // In a real implementation, this would transform the parsed data into a standardized format
        // and save it to a file or database
        
        String outputFileName = bimFile.getStoredFilename() + ".json";
        String outputPath = "/output/" + outputFileName;
        
        log.info("Processed BIM file: {} with result size: {}", 
                bimFile.getOriginalFilename(), result.size());
        
        return outputPath;
    }
    
    /**
     * EN: Queue a BIM file for processing
     * PT: Enfileirar um arquivo BIM para processamento
     * 
     * @param fileId EN: ID of the BIM file to queue / PT: ID do arquivo BIM para enfileirar
     * @return EN: CompletableFuture for the processing task / PT: CompletableFuture para a tarefa de processamento
     */
    public CompletableFuture<BimFile> queueFileForProcessing(Long fileId) {
        return processBimFile(fileId);
    }
}
