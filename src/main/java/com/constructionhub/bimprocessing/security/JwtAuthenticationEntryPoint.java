package com.constructionhub.bimprocessing.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT Authentication Entry Point
 * 
 * EN: Handles unauthorized access attempts to secured endpoints.
 * Returns appropriate HTTP response when authentication fails.
 * 
 * PT: Lida com tentativas de acesso não autorizado a endpoints seguros.
 * Retorna resposta HTTP apropriada quando a autenticação falha.
 */
@Component
@Slf4j
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * EN: Method called when an unauthenticated user tries to access secured resources
     * PT: Método chamado quando um usuário não autenticado tenta acessar recursos seguros
     * 
     * @param request EN: HTTP request / PT: Requisição HTTP
     * @param response EN: HTTP response / PT: Resposta HTTP
     * @param authException EN: Authentication exception / PT: Exceção de autenticação
     * @throws IOException EN: If an I/O error occurs / PT: Se ocorrer um erro de I/O
     * @throws ServletException EN: If a servlet error occurs / PT: Se ocorrer um erro de servlet
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        
        log.error("Unauthorized access attempt: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized: Authentication token is missing or invalid");
    }
}
