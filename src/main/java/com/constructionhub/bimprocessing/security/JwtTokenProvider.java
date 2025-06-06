package com.constructionhub.bimprocessing.security;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

/**
 * JWT Token Provider
 * 
 * EN: Component responsible for JWT token generation, validation, and parsing.
 * Handles all JWT-related operations for secure API authentication.
 * 
 * PT: Componente responsável pela geração, validação e análise de tokens JWT.
 * Lida com todas as operações relacionadas a JWT para autenticação segura da API.
 */
@Component
@Slf4j
public class JwtTokenProvider {

    @Value("${security.jwt.secret}")
    private String jwtSecret;

    @Value("${security.jwt.expiration}")
    private long jwtExpirationMs;

    /**
     * EN: Validates a JWT token
     * PT: Valida um token JWT
     * 
     * @param token EN: JWT token to validate / PT: Token JWT para validar
     * @return EN: True if valid, false otherwise / PT: Verdadeiro se válido, falso caso contrário
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty");
        }
        return false;
    }

    /**
     * EN: Extracts username from JWT token
     * PT: Extrai o nome de usuário do token JWT
     * 
     * @param token EN: JWT token / PT: Token JWT
     * @return EN: Username from token / PT: Nome de usuário do token
     */
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    /**
     * EN: Extracts claim from JWT token
     * PT: Extrai uma reivindicação do token JWT
     * 
     * @param <T> EN: Type of claim / PT: Tipo de reivindicação
     * @param token EN: JWT token / PT: Token JWT
     * @param claimsResolver EN: Function to resolve claim / PT: Função para resolver a reivindicação
     * @return EN: Claim value / PT: Valor da reivindicação
     */
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    /**
     * EN: Extracts all claims from JWT token
     * PT: Extrai todas as reivindicações do token JWT
     * 
     * @param token EN: JWT token / PT: Token JWT
     * @return EN: All claims / PT: Todas as reivindicações
     */
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    /**
     * EN: Gets signing key for JWT token
     * PT: Obtém a chave de assinatura para o token JWT
     * 
     * @return EN: Signing key / PT: Chave de assinatura
     */
    private Key getSigningKey() {
        byte[] keyBytes = jwtSecret.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
