package com.constructionhub.bimprocessing.exception;

/**
 * Resource Not Found Exception
 * 
 * EN: Exception thrown when a requested resource cannot be found.
 * Used to indicate that a specific entity or resource does not exist in the system.
 * 
 * PT: Exceção lançada quando um recurso solicitado não pode ser encontrado.
 * Usada para indicar que uma entidade ou recurso específico não existe no sistema.
 */
public class ResourceNotFoundException extends RuntimeException {

    /**
     * EN: Serial version UID for serialization
     * PT: UID de versão serial para serialização
     */
    private static final long serialVersionUID = 1L;

    /**
     * EN: Constructor with error message
     * PT: Construtor com mensagem de erro
     * 
     * @param message EN: Error message / PT: Mensagem de erro
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }

    /**
     * EN: Constructor with error message and cause
     * PT: Construtor com mensagem de erro e causa
     * 
     * @param message EN: Error message / PT: Mensagem de erro
     * @param cause EN: Cause of the exception / PT: Causa da exceção
     */
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
