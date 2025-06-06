package com.constructionhub.bimprocessing.security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/**
 * JWT Authentication Filter
 * 
 * EN: Filter that intercepts requests to validate JWT tokens and set authentication.
 * Extracts and validates JWT tokens from request headers for secure API access.
 * 
 * PT: Filtro que intercepta requisições para validar tokens JWT e definir autenticação.
 * Extrai e valida tokens JWT dos cabeçalhos de requisição para acesso seguro à API.
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailsService userDetailsService;

    /**
     * EN: Filters incoming requests to validate JWT tokens and set authentication
     * PT: Filtra requisições de entrada para validar tokens JWT e definir autenticação
     * 
     * @param request EN: HTTP request / PT: Requisição HTTP
     * @param response EN: HTTP response / PT: Resposta HTTP
     * @param filterChain EN: Filter chain / PT: Cadeia de filtros
     * @throws ServletException EN: If a servlet error occurs / PT: Se ocorrer um erro de servlet
     * @throws IOException EN: If an I/O error occurs / PT: Se ocorrer um erro de I/O
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        try {
            String jwt = getJwtFromRequest(request);

            if (jwt != null && tokenProvider.validateToken(jwt)) {
                String username = tokenProvider.getUsernameFromToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
                
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception ex) {
            logger.error("Could not set user authentication in security context", ex);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * EN: Extracts JWT token from the Authorization header
     * PT: Extrai o token JWT do cabeçalho de Autorização
     * 
     * @param request EN: HTTP request / PT: Requisição HTTP
     * @return EN: JWT token or null if not found / PT: Token JWT ou null se não encontrado
     */
    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
