package com.constructionhub.bimprocessing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.constructionhub.bimprocessing.model.User;

/**
 * User Repository
 * 
 * EN: Repository interface for User entity operations.
 * Provides methods to interact with the users table in the database.
 * 
 * PT: Interface de repositório para operações da entidade Usuário.
 * Fornece métodos para interagir com a tabela de usuários no banco de dados.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * EN: Find a user by username
     * PT: Encontrar um usuário pelo nome de usuário
     * 
     * @param username EN: Username to search for / PT: Nome de usuário a ser pesquisado
     * @return EN: Optional containing the user if found / PT: Optional contendo o usuário se encontrado
     */
    Optional<User> findByUsername(String username);
    
    /**
     * EN: Find a user by email
     * PT: Encontrar um usuário pelo email
     * 
     * @param email EN: Email to search for / PT: Email a ser pesquisado
     * @return EN: Optional containing the user if found / PT: Optional contendo o usuário se encontrado
     */
    Optional<User> findByEmail(String email);
    
    /**
     * EN: Check if a user exists by username
     * PT: Verificar se um usuário existe pelo nome de usuário
     * 
     * @param username EN: Username to check / PT: Nome de usuário a ser verificado
     * @return EN: True if exists, false otherwise / PT: Verdadeiro se existir, falso caso contrário
     */
    boolean existsByUsername(String username);
    
    /**
     * EN: Check if a user exists by email
     * PT: Verificar se um usuário existe pelo email
     * 
     * @param email EN: Email to check / PT: Email a ser verificado
     * @return EN: True if exists, false otherwise / PT: Verdadeiro se existir, falso caso contrário
     */
    boolean existsByEmail(String email);
}
