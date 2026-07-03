package com.apicartaocredito.wl.Service;

import com.apicartaocredito.wl.Exception.EmailJaCadastradoEception;

import com.apicartaocredito.wl.Model.Dtos.LoginRequest;
import com.apicartaocredito.wl.Model.Dtos.RegisterRequest;
import com.apicartaocredito.wl.Model.Dtos.TokenResponse;
import com.apicartaocredito.wl.Model.RolesEntity;
import com.apicartaocredito.wl.Model.UsuarioEntity;
import com.apicartaocredito.wl.Repository.IRolesRepository;
import com.apicartaocredito.wl.Repository.IUsuarioRepository;
import com.apicartaocredito.wl.config.TokenProvider;
import com.apicartaocredito.wl.enums.RoleType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final IUsuarioRepository usuarioRepository;
    private final IRolesRepository rolesRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    @Value("${jwt.expiration}")
    private Long expirationTime;


    public void registrarUsuario (RegisterRequest request) {
        UsuarioEntity usuario = usuarioRepository.findByEmail(request.email())
                .orElse(null);

        if (usuario != null) {
            throw new EmailJaCadastradoEception();
        }

        RolesEntity role = rolesRepository.findByAuthority(RoleType.ROLE_CLIENTE.name())
                .orElseGet(() -> rolesRepository.save(RolesEntity.builder()
                        .authority(RoleType.ROLE_CLIENTE.name()).build()));



         usuarioRepository.save(UsuarioEntity.builder()
                .email(request.email())
                .senha(request.senha())
                .roles(Set.of(role))
                        .senha(passwordEncoder.encode(request.senha())).build());

    }


    public TokenResponse loginUsuario(LoginRequest loginRequest) {

        try {
           Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.senha()));
           String token = tokenProvider.gerarToken(authentication);

           return new TokenResponse(token, expirationTime);
        }catch (BadCredentialsException e) {

            throw new BadCredentialsException("Credenciais inválidas");

        }catch (Exception e) {
            throw e;
        }
    }
}