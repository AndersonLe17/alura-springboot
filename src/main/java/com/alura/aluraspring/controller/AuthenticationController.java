package com.alura.aluraspring.controller;

import com.alura.aluraspring.domain.usuario.Usuario;
import com.alura.aluraspring.domain.usuario.dto.LoginRequest;
import com.alura.aluraspring.infra.security.TokenJWT;
import com.alura.aluraspring.infra.security.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@RequestBody @Valid LoginRequest loginDTO) {
        Authentication authToken = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        Authentication authenticate= authenticationManager.authenticate(authToken);
        String JWTtoken = tokenService.generateToken((Usuario) authenticate.getPrincipal());
        return ResponseEntity.ok(new TokenJWT(JWTtoken));
    }

}
