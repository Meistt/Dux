package com.challenge.dux.application.service;

import com.challenge.dux.application.dto.AuthResponse;
import com.challenge.dux.application.dto.UserLogin;
import com.challenge.dux.application.exceptions.RecursoNoEncontradoException;
import com.challenge.dux.domain.interfaces.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService {

    private final Map<String, String> users = new HashMap<>();
    private final String SECRET_KEY = String.valueOf(Keys.secretKeyFor(SignatureAlgorithm.HS256));

    @PostConstruct
    public void init() {
        users.put("test", "12345");

    }

    @Override
    public AuthResponse getToken(UserLogin user) {
        if (existeUsuario()){
            return createTokenUser(user);
        }
        throw new RecursoNoEncontradoException("Usuario no encontrado");
    }

    private AuthResponse createTokenUser(UserLogin user) {
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                .compact();
        return new AuthResponse(token);
    }

    private boolean existeUsuario() {
        return users.containsKey("test") && users.get("test").equals("12345");
    }
}
