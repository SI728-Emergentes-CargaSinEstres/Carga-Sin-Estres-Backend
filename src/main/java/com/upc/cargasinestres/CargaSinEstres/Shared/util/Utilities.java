package com.upc.cargasinestres.CargaSinEstres.Shared.util;

import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.Role;
import com.upc.cargasinestres.CargaSinEstres.Users.model.enums.ERole;
import com.upc.cargasinestres.CargaSinEstres.Users.repository.IRoleRepository;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.List;

/**
 * Class that contains the utilities for the application
 * @author Grupo1
 */
@Slf4j
public class Utilities {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";

    /**
     * Obtiene el token del header Authorization
     * @param request Solicitud http
     * @return Token obtenido
     */
    static public String getJwtTokenFromRequest(HttpServletRequest request) {
        //obtiene el token JWT desde el header
        String jwtTokenFromHeader = request.getHeader(AUTHORIZATION_HEADER);

        if (StringUtils.hasText(jwtTokenFromHeader) && jwtTokenFromHeader.startsWith(BEARER_PREFIX)) {
            return jwtTokenFromHeader.substring(BEARER_PREFIX.length());
        }

        return null;
    }

    /**
     * Map roles to GrantedAuthority objects
     * @param roles roles to map
     * @return List of GrantedAuthority objects
     */
    static public List<SimpleGrantedAuthority> mapRoles(Collection<Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .toList();
    }

    /**
     * Get roles from authenticated user
     * @param authenticatedUser Authenticated user
     * @return List of roles
     */
    static public List<String> getRoles(User authenticatedUser) {
        return authenticatedUser.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
    }

    /**
     * Get the secret key from the secret
     * @param secret secret key
     * @return secret key HMAC
     */
    static public SecretKey getKey(String secret) {
        byte[] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }

    /**
     * Insert role if not found
     * @param repository Repository of roles
     * @param roleName Role name
     */
    static public void insertRoleIfNotFound(IRoleRepository repository, ERole roleName) {
        if (!repository.existsByName(roleName)) {
            var newRole = new Role();
            newRole.setName(roleName);

            repository.save(newRole);
            log.info("Role {} inserted", roleName);
        }
    }
}
