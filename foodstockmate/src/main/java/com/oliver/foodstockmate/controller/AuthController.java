package com.oliver.foodstockmate.controller;

import com.oliver.foodstockmate.dto.LoginRequestDto;
import com.oliver.foodstockmate.dto.RegisterRequestDto;
import com.oliver.foodstockmate.dto.UserDto;
import com.oliver.foodstockmate.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUSer(@RequestBody RegisterRequestDto request) {
        authService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> UserLogin(@RequestBody LoginRequestDto request) {
        String token = authService.login(request);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Authentication authentication) {
        UserDto userDto = authService.getCurrentUser(authentication);
        return ResponseEntity.ok(userDto);
    }
}
