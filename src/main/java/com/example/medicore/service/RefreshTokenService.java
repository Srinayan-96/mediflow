package com.example.medicore.service;

import com.example.medicore.entity.RefreshToken;
import com.example.medicore.entity.User;
import com.example.medicore.repository.TokenRefreshRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final TokenRefreshRepository repository;

    public RefreshToken createRefreshToken(User user){
        RefreshToken token = new RefreshToken();

        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(604800)); // 7 days

        return repository.save(token);
    }

}
