package com.challenge.dux.domain.interfaces;

import com.challenge.dux.application.dto.AuthResponse;
import com.challenge.dux.application.dto.UserLogin;

public interface AuthService {
    AuthResponse getToken(UserLogin user);
}
