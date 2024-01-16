package com.upc.cargasinestres.CargaSinEstres.Security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;

/**
 * Class that contains the security beans
 * @author Grupo1
 */
@Configuration
public class BeansConfig {

    /**
     * Bean that handles password encrypting
     * @return Instancia de BCryptPasswordEncoder
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Bean that handles authentication
     * @param configuration of the authentication
     * @return an instance of AuthenticationManager
     * @throws Exception authentication exception
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception{
        return configuration.getAuthenticationManager();
    }
}
