package com.challenge.equipos.domain.interfaces;

import com.challenge.equipos.application.dto.AuthResponse;
import com.challenge.equipos.application.dto.UserLogin;

public interface AuthService {
    AuthResponse getToken(UserLogin user);
}
