package com.example.miniprojecthospitalkelompok2.security.services;

import com.example.miniprojecthospitalkelompok2.common.TokenRefreshException;
import com.example.miniprojecthospitalkelompok2.entity.RefreshToken;
import com.example.miniprojecthospitalkelompok2.entity.Users;
import com.example.miniprojecthospitalkelompok2.repository.RefreshTokenRepository;
import com.example.miniprojecthospitalkelompok2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefreshTokenService {
  @Value("${Hospital.app.jwtRefreshExpirationMs}")
  private Long refreshTokenDurationMs;

  @Autowired
  private RefreshTokenRepository refreshTokenRepository;

  @Autowired
  private UserRepository userRepository;

  public Optional<RefreshToken> findByToken(String token) {
    return refreshTokenRepository.findByToken(token);
  }

  public RefreshToken createRefreshToken(Long userId) {
    RefreshToken refreshToken = new RefreshToken();

    refreshToken.setUsers(userRepository.findById(userId).get());
    refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
    refreshToken.setToken(UUID.randomUUID().toString());

    refreshToken = refreshTokenRepository.save(refreshToken);
    return refreshToken;
  }

  public RefreshToken verifyExpiration(RefreshToken token) {
    if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
      refreshTokenRepository.delete(token);
      throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
    }

    return token;
  }

  @Transactional
  public int deleteByUserId(Long userId) {
    Users userr = userRepository.findById(userId).orElse(null);
    return refreshTokenRepository.deleteByUsers(userr);
    // refreshTokenRepository.deleteById(userRepository.findById(userId).get().getUser_id());
  }
}
