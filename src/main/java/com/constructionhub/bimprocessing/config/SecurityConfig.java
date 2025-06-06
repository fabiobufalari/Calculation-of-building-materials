package com.constructionhub.bimprocessing.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.constructionhub.bimprocessing.security.JwtAuthenticationFilter;
import com.constructionhub.bimprocessing.security.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

/**
 * Security Configuration
 * 
 * EN: Configuration class for security settings of the BIM Processing service.
 * Defines security rules, authentication mechanisms, and protected endpoints.
 * 
 * PT: Classe de configuração para as definições de segurança do serviço de Processamento BIM.
 * Define regras de segurança, mecanismos de autenticação e endpoints protegidos.
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * EN: Configures the security filter chain with JWT authentication
     * PT: Configura a cadeia de filtros de segurança com autenticação JWT
     * 
     * @param http EN: HttpSecurity object to configure / PT: Objeto HttpSecurity para configurar
     * @return EN: Configured SecurityFilterChain / PT: SecurityFilterChain configurado
     * @throws Exception EN: If configuration fails / PT: Se a configuração falhar
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(exception -> exception
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authorizeHttpRequests(auth -> auth
                // Public endpoints
                .requestMatchers("/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                // Protected endpoints
                .anyRequest().authenticated()
            );
        
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        
        return http.build();
    }
}
