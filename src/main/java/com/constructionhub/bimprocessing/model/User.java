package com.constructionhub.bimprocessing.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User Entity
 * 
 * EN: Entity class representing a user in the BIM Processing system.
 * Stores user authentication and authorization information.
 * 
 * PT: Classe de entidade que representa um usuário no sistema de Processamento BIM.
 * Armazena informações de autenticação e autorização do usuário.
 */
@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * EN: Unique identifier for the user
     * PT: Identificador único para o usuário
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * EN: Username for authentication
     * PT: Nome de usuário para autenticação
     */
    @Column(nullable = false, unique = true)
    private String username;

    /**
     * EN: Email address of the user
     * PT: Endereço de email do usuário
     */
    @Column(nullable = false, unique = true)
    private String email;

    /**
     * EN: Hashed password for authentication
     * PT: Senha criptografada para autenticação
     */
    @Column(nullable = false)
    private String password;

    /**
     * EN: First name of the user
     * PT: Primeiro nome do usuário
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * EN: Last name of the user
     * PT: Sobrenome do usuário
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * EN: Whether the user account is active
     * PT: Se a conta do usuário está ativa
     */
    @Column(nullable = false)
    private boolean active;

    /**
     * EN: Collection of roles assigned to the user
     * PT: Coleção de funções atribuídas ao usuário
     */
    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private Set<String> roles = new HashSet<>();

    /**
     * EN: Date and time when the user was created
     * PT: Data e hora em que o usuário foi criado
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * EN: Date and time when the user was last updated
     * PT: Data e hora em que o usuário foi atualizado pela última vez
     */
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
