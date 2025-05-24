package com.challenge.equipos.infrastructure.controller;

import com.challenge.equipos.application.dto.AuthResponse;
import com.challenge.equipos.application.dto.UserLogin;
import com.challenge.equipos.domain.interfaces.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>  login (@Valid @RequestBody UserLogin user){
        AuthResponse response = authService.getToken(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
