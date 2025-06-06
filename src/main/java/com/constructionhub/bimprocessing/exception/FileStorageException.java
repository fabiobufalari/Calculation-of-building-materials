package com.constructionhub.bimprocessing.exception;

/**
 * File Storage Exception
 * 
 * EN: Exception thrown when there is an error storing or retrieving files.
 * Used to indicate problems with file operations in the BIM processing system.
 * 
 * PT: Exceção lançada quando há um erro ao armazenar ou recuperar arquivos.
 * Usada para indicar problemas com operações de arquivo no sistema de processamento BIM.
 */
public class FileStorageException extends RuntimeException {

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
    public FileStorageException(String message) {
        super(message);
    }

    /**
     * EN: Constructor with error message and cause
     * PT: Construtor com mensagem de erro e causa
     * 
     * @param message EN: Error message / PT: Mensagem de erro
     * @param cause EN: Cause of the exception / PT: Causa da exceção
     */
    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }
}
