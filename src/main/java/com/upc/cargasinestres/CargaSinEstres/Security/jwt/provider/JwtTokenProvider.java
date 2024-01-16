package com.upc.cargasinestres.CargaSinEstres.Security.jwt.provider;

import com.upc.cargasinestres.CargaSinEstres.Shared.util.Utilities;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Class that generates the token, obtains the claims and validates the token
 * @author Grupo1*/
@Slf4j
@Component
public class JwtTokenProvider {
    //obtiene una propiedad del application.properties
    @Value("${app.jwt-secret}")
    private String secret;

    @Value("${app.jwt-expiration-min}")
    private long expiration;

    /**
     * Generates the JWT token
     * @param authentication User's authentication
     * @return Token generated
     */
    public String generateToken(Authentication authentication) {
        var authenticatedUser = (User) authentication.getPrincipal();

        //in minutes
        var expiryDate = new Date(new Date().getTime() + expiration * 100_000);

        //construye el token JWT
        return Jwts.builder()
                .subject(authenticatedUser.getUsername()) //se usa el username como subject
                .issuedAt(new Date()) //fecha de emisión
                .expiration(expiryDate) //fecha de expiración
                .signWith(Utilities.getKey(secret)) //firma el token
                .claim("roles", Utilities.getRoles(authenticatedUser)) //agrega los roles
                .compact(); //construye el token
    }

    /**
     * Obtains the username from the token
     * @param token Token to process
     * @return Username of the user
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(Utilities.getKey(secret)) //verifica la firma
                .build() //construye el parser
                .parseSignedClaims(token) //parsea el token
                .getPayload(); //obtiene los claims (payload)

        return claims.getSubject();
    }

    /**
     * Validates the token
     * @param token Token to validate
     * @return True = valid token, False = invalid token
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(Utilities.getKey(secret))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (SignatureException ex) {
            log.warn("Firma del token inválida");
        } catch (MalformedJwtException ex) {
            log.warn("Token inválido");
        } catch (ExpiredJwtException ex) {
            log.warn("Token expirado");
        } catch (UnsupportedJwtException ex) {
            log.warn("Token no soportado");
        } catch (IllegalArgumentException ex) {
            log.warn("Claims vacíos");
        }

        return false;
    }
}
