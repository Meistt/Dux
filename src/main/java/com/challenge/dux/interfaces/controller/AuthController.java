package com.challenge.dux.interfaces.controller;

import com.challenge.dux.application.dto.AuthResponse;
import com.challenge.dux.application.dto.UserLogin;
import com.challenge.dux.domain.interfaces.AuthService;
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
