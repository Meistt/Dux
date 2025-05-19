package com.challenge.dux.application.service;

import com.challenge.dux.application.dto.AuthResponse;
import com.challenge.dux.application.dto.UserLogin;
import com.challenge.dux.application.exceptions.RecursoNoEncontradoException;
import com.challenge.dux.domain.interfaces.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private SecretKey secretKey;

    private final Map<String, String> users = new HashMap<>();
    @Autowired
    private SecretKey jwtSecretKey;

    @PostConstruct
    public void init() {
        users.put("test", "12345");
    }

    @Override
    public AuthResponse getToken(UserLogin user) {
        if (existeUsuario(user)){
            return createTokenUser(user);
        }
        throw new RecursoNoEncontradoException("Usuario no encontrado");
    }

    private AuthResponse createTokenUser(UserLogin user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(jwtSecretKey)
                .compact();
        return new AuthResponse(token);
    }

    private boolean existeUsuario(UserLogin user) {
        return users.containsKey("test") &&
                users.get(user.getUsername()).equals(user.getPassword());
    }
}
