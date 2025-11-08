package com.oliver.foodstockmate.service;

import com.oliver.foodstockmate.dto.LoginRequestDto;
import com.oliver.foodstockmate.dto.RegisterRequestDto;
import com.oliver.foodstockmate.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    void register(RegisterRequestDto request);
    String login(LoginRequestDto request);
    UserDto getCurrentUser(Authentication authentication);
}
