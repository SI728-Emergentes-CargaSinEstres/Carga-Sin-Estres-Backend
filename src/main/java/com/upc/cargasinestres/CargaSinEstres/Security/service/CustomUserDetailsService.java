package com.upc.cargasinestres.CargaSinEstres.Security.service;

import com.upc.cargasinestres.CargaSinEstres.Shared.util.Utilities;
import com.upc.cargasinestres.CargaSinEstres.Users.repository.IUserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Class that implements Spring Security's UserDetailsService interface to obtain authenticated user data
 * @author Grupo1
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    /**
     * Constructs a new CustomUserDetailsService with the specified IUserRepository.
     *
     * @param userRepository The repository for user data.
     */
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves the UserDetails of the authenticated user by their username or email.
     *
     * @param usernameOrEmail The username or email of the authenticated user.
     * @return UserDetails The details of the authenticated user.
     * @throws UsernameNotFoundException if the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        //busca al usuario por su username o email
        var user = userRepository.findByEmailOrUsername(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("No se encontró al usuario con el username o email: " + usernameOrEmail));

        //crea y retorna un objeto que representa al usuario autenticado
        return new User(user.getEmail(), user.getPassword(), Utilities.mapRoles(user.getRoles()));
    }
}
