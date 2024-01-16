package com.upc.cargasinestres.CargaSinEstres.Security.service;

import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.LoginRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.request.RegisterRequestDto;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.RegisteredUserResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Security.jwt.provider.JwtTokenProvider;
import com.upc.cargasinestres.CargaSinEstres.Security.model.dto.response.TokenResponseDto;
import com.upc.cargasinestres.CargaSinEstres.Shared.dto.enums.EStatus;
import com.upc.cargasinestres.CargaSinEstres.Shared.dto.response.ApiResponse;
import com.upc.cargasinestres.CargaSinEstres.Shared.exception.CustomException;
import com.upc.cargasinestres.CargaSinEstres.Users.model.Entity.User;
import com.upc.cargasinestres.CargaSinEstres.Users.model.enums.ERole;
import com.upc.cargasinestres.CargaSinEstres.Users.repository.IRoleRepository;
import com.upc.cargasinestres.CargaSinEstres.Users.repository.IUserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Service for user authentication and registration
 * @author Grupo1
 */
@Service
public class AuthService implements IAuthService {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final ModelMapper modelMapper;

    public AuthService(AuthenticationManager authenticationManager, IUserRepository userRepository, IRoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, ModelMapper modelMapper) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.modelMapper = modelMapper;
    }

    @Override
    public ApiResponse<RegisteredUserResponseDto> registerUser(RegisterRequestDto request) {
        //si el email ya está registrado
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new CustomException(HttpStatus.CONFLICT, "El email '" + request.getEmail() + "' ya está registrado");
        }

        //si el username ya está registrado
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new CustomException(HttpStatus.CONFLICT, "El username '" + request.getUsername() + "' ya está registrado");
        }

        //si no existe, lo registra
        var user = User.builder()
                .fullName(request.getFullName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        //establecer los roles
        var roles = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "No se pudo registrar el usuario, no se encontró el rol USER"));
        user.setRoles(Collections.singleton(roles)); //establece un solo rol

        //guarda el usuario
        var newUser = userRepository.save(user);

        //mapea de la entidad al dto
        var responseData = modelMapper.map(newUser, RegisteredUserResponseDto.class);

        return new ApiResponse<>("Registro correcto", EStatus.SUCCESS, responseData);
    }

    @Override
    public ApiResponse<TokenResponseDto> login(LoginRequestDto request) {
        //se validan las credenciales
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsernameOrEmail(),
                        request.getPassword()
                )
        );

        //establece la seguridad
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //se obtiene el token
        String token = jwtTokenProvider.generateToken(authentication);

        var responseData = new TokenResponseDto(token);
        return new ApiResponse<>("Autenticación correcta", EStatus.SUCCESS, responseData);
    }
}