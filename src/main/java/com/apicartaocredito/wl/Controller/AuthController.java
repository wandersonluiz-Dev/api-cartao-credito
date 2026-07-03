package com.apicartaocredito.wl.Controller;

import com.apicartaocredito.wl.Model.Dtos.LoginRequest;
import com.apicartaocredito.wl.Model.Dtos.RegisterRequest;
import com.apicartaocredito.wl.Model.Dtos.TokenResponse;
import com.apicartaocredito.wl.Service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public void register(@RequestBody @Valid RegisterRequest registerRequest) throws Exception {
        authenticationService.registrarUsuario(registerRequest);

    }

    @PostMapping("/login")
    public TokenResponse register(@RequestBody @Valid LoginRequest LoginRequest) throws Exception {
       return authenticationService.loginUsuario(LoginRequest);

    }


}
