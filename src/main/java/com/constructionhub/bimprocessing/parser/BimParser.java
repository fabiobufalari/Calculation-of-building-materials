package com.constructionhub.bimprocessing.parser;

import java.io.File;
import java.util.Map;

/**
 * BIM Parser Interface
 * 
 * EN: Interface for all BIM file format parsers.
 * Defines the contract that all specific BIM parsers must implement.
 * 
 * PT: Interface para todos os analisadores de formato de arquivo BIM.
 * Define o contrato que todos os analisadores BIM específicos devem implementar.
 */
public interface BimParser {
    
    /**
     * EN: Parse a BIM file and extract its data
     * PT: Analisar um arquivo BIM e extrair seus dados
     * 
     * @param file EN: File to parse / PT: Arquivo para analisar
     * @return EN: Extracted data as a map / PT: Dados extraídos como um mapa
     * @throws Exception EN: If parsing fails / PT: Se a análise falhar
     */
    Map<String, Object> parseFile(File file) throws Exception;
    
    /**
     * EN: Get the name of the parser
     * PT: Obter o nome do analisador
     * 
     * @return EN: Parser name / PT: Nome do analisador
     */
    String getParserName();
    
    /**
     * EN: Get supported file extensions
     * PT: Obter extensões de arquivo suportadas
     * 
     * @return EN: Array of supported extensions / PT: Array de extensões suportadas
     */
    String[] getSupportedExtensions();
    
    /**
     * EN: Check if the parser can handle a specific file
     * PT: Verificar se o analisador pode processar um arquivo específico
     * 
     * @param file EN: File to check / PT: Arquivo para verificar
     * @return EN: True if supported, false otherwise / PT: Verdadeiro se suportado, falso caso contrário
     */
    boolean canHandle(File file);
}
