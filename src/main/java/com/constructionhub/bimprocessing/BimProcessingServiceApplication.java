package com.constructionhub.bimprocessing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * BIM Processing Service Application
 * 
 * EN: Main application class for the BIM Processing microservice.
 * This service handles the processing of Building Information Modeling (BIM) files,
 * extracting construction data, and providing normalized output for other services.
 * 
 * PT: Classe principal da aplicação para o microsserviço de Processamento BIM.
 * Este serviço lida com o processamento de arquivos de Modelagem de Informação da Construção (BIM),
 * extraindo dados de construção e fornecendo saída normalizada para outros serviços.
 */
@SpringBootApplication
@EnableFeignClients
public class BimProcessingServiceApplication {

    /**
     * EN: Main method to start the BIM Processing Service
     * PT: Método principal para iniciar o Serviço de Processamento BIM
     * 
     * @param args EN: Command line arguments / PT: Argumentos de linha de comando
     */
    public static void main(String[] args) {
        SpringApplication.run(BimProcessingServiceApplication.class, args);
    }
}
