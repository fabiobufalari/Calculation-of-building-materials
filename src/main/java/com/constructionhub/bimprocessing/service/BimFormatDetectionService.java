package com.constructionhub.bimprocessing.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.constructionhub.bimprocessing.model.BimFile;
import com.constructionhub.bimprocessing.model.BimFile.ProcessingStatus;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * BIM Format Detection Service
 * 
 * EN: Service for detecting and routing BIM file formats to appropriate parsers.
 * Identifies file types and determines which parser should handle the file.
 * 
 * PT: Serviço para detecção e roteamento de formatos de arquivo BIM para analisadores apropriados.
 * Identifica tipos de arquivo e determina qual analisador deve processar o arquivo.
 */
@Service
@Slf4j
public class BimFormatDetectionService {

    @Value("${bim.parsers.autocad.enabled}")
    private boolean autocadEnabled;
    
    @Value("${bim.parsers.autocad.supported-extensions}")
    private String autocadExtensions;
    
    @Value("${bim.parsers.revit.enabled}")
    private boolean revitEnabled;
    
    @Value("${bim.parsers.revit.supported-extensions}")
    private String revitExtensions;
    
    @Value("${bim.parsers.archicad.enabled}")
    private boolean archicadEnabled;
    
    @Value("${bim.parsers.archicad.supported-extensions}")
    private String archicadExtensions;
    
    @Value("${bim.parsers.sketchup.enabled}")
    private boolean sketchupEnabled;
    
    @Value("${bim.parsers.sketchup.supported-extensions}")
    private String sketchupExtensions;
    
    private final Map<String, String> extensionToParserMap = new ConcurrentHashMap<>();
    private final Map<String, Boolean> parserEnabledMap = new ConcurrentHashMap<>();
    
    /**
     * EN: Initialize the format detection service
     * PT: Inicializar o serviço de detecção de formato
     */
    @PostConstruct
    public void init() {
        // Map file extensions to parsers
        mapExtensionsToParser(autocadExtensions, "autocad");
        mapExtensionsToParser(revitExtensions, "revit");
        mapExtensionsToParser(archicadExtensions, "archicad");
        mapExtensionsToParser(sketchupExtensions, "sketchup");
        
        // Set parser enabled status
        parserEnabledMap.put("autocad", autocadEnabled);
        parserEnabledMap.put("revit", revitEnabled);
        parserEnabledMap.put("archicad", archicadEnabled);
        parserEnabledMap.put("sketchup", sketchupEnabled);
        
        log.info("BIM Format Detection Service initialized with {} supported extensions", extensionToParserMap.size());
    }
    
    /**
     * EN: Map file extensions to a specific parser
     * PT: Mapear extensões de arquivo para um analisador específico
     * 
     * @param extensions EN: Comma-separated list of extensions / PT: Lista de extensões separadas por vírgula
     * @param parserName EN: Name of the parser / PT: Nome do analisador
     */
    private void mapExtensionsToParser(String extensions, String parserName) {
        if (extensions != null && !extensions.isEmpty()) {
            Arrays.stream(extensions.split(","))
                .map(String::trim)
                .map(String::toLowerCase)
                .forEach(ext -> extensionToParserMap.put(ext, parserName));
        }
    }
    
    /**
     * EN: Detect the appropriate parser for a BIM file
     * PT: Detectar o analisador apropriado para um arquivo BIM
     * 
     * @param bimFile EN: BIM file to detect parser for / PT: Arquivo BIM para detectar analisador
     * @return EN: Name of the parser or null if unsupported / PT: Nome do analisador ou null se não suportado
     */
    public String detectParser(BimFile bimFile) {
        String extension = bimFile.getFileExtension().toLowerCase();
        String parser = extensionToParserMap.get(extension);
        
        if (parser == null) {
            log.warn("Unsupported file extension: {}", extension);
            return null;
        }
        
        if (!parserEnabledMap.getOrDefault(parser, false)) {
            log.warn("Parser {} is disabled but required for extension {}", parser, extension);
            return null;
        }
        
        log.info("Detected parser {} for file extension {}", parser, extension);
        return parser;
    }
    
    /**
     * EN: Check if a file format is supported
     * PT: Verificar se um formato de arquivo é suportado
     * 
     * @param extension EN: File extension to check / PT: Extensão de arquivo para verificar
     * @return EN: True if supported, false otherwise / PT: Verdadeiro se suportado, falso caso contrário
     */
    public boolean isFormatSupported(String extension) {
        if (extension == null) {
            return false;
        }
        
        String parser = extensionToParserMap.get(extension.toLowerCase());
        return parser != null && parserEnabledMap.getOrDefault(parser, false);
    }
    
    /**
     * EN: Get all supported file extensions
     * PT: Obter todas as extensões de arquivo suportadas
     * 
     * @return EN: List of supported extensions / PT: Lista de extensões suportadas
     */
    public List<String> getSupportedExtensions() {
        return extensionToParserMap.keySet().stream()
            .filter(ext -> {
                String parser = extensionToParserMap.get(ext);
                return parserEnabledMap.getOrDefault(parser, false);
            })
            .toList();
    }
}
