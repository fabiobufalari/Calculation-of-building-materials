package com.constructionhub.bimprocessing.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.constructionhub.bimprocessing.model.User;
import com.constructionhub.bimprocessing.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Custom User Details Service
 * 
 * EN: Service that loads user-specific data for authentication.
 * Implements Spring Security's UserDetailsService to provide user authentication.
 * 
 * PT: Serviço que carrega dados específicos do usuário para autenticação.
 * Implementa o UserDetailsService do Spring Security para fornecer autenticação de usuário.
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * EN: Loads a user by username for authentication
     * PT: Carrega um usuário pelo nome de usuário para autenticação
     * 
     * @param username EN: Username to load / PT: Nome de usuário para carregar
     * @return EN: UserDetails object / PT: Objeto UserDetails
     * @throws UsernameNotFoundException EN: If user not found / PT: Se o usuário não for encontrado
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        
        return org.springframework.security.core.userdetails.User
            .withUsername(user.getUsername())
            .password(user.getPassword())
            .authorities(user.getRoles())
            .accountExpired(!user.isActive())
            .accountLocked(!user.isActive())
            .credentialsExpired(!user.isActive())
            .disabled(!user.isActive())
            .build();
    }
}
