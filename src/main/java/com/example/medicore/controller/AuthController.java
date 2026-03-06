package com.example.medicore.controller;

import com.example.medicore.api.ApiResponse;
import com.example.medicore.dto.LoginRequestDTO;
import com.example.medicore.entity.RefreshToken;
import com.example.medicore.entity.User;
import com.example.medicore.repository.TokenRefreshRepository;
import com.example.medicore.repository.UserRepository;
import com.example.medicore.security.JwtService;
import com.example.medicore.service.RefreshTokenService;

import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final TokenRefreshRepository tokenRefreshRepository;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ApiResponse<Map<String, String>> login(@RequestBody LoginRequestDTO request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        User user = userRepository
                .findByUsername(request.getUsername())
                .orElseThrow();

        String accessToken = jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);

        return new ApiResponse<>(
                true,
                "Login successful",
                Map.of(
                        "accessToken", accessToken,
                        "refreshToken", refreshToken.getToken()
                )
        );
    }

    @PostMapping("/refresh")
    public ApiResponse<String> refreshToken(@RequestBody String refreshTokenValue) {

        RefreshToken token = tokenRefreshRepository
                .findByToken(refreshTokenValue)
                .orElseThrow();

        User user = token.getUser();

        String newAccessToken =
                jwtService.generateToken(user.getUsername(), user.getRole());

        return new ApiResponse<>(
                true,
                "Token refreshed",
                newAccessToken
        );
    }
}