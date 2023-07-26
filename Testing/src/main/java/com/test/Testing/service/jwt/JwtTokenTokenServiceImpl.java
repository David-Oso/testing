package com.test.Testing.service.jwt;

import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.security.JwtUtils;
import com.test.Testing.security.jwtToken.JwtToken;
import com.test.Testing.security.jwtToken.JwtTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtTokenTokenServiceImpl implements JwtTokenService {
    private final JwtUtils jwtUtils;
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public JwtTokenResponse getJwtTokens(AppUser appUser) {
        String email = appUser.getEmail();
        String accessToken = jwtUtils.generateAccessToken(email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        saveUserJwtToken(appUser, accessToken);
        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserJwtToken(AppUser appUser, String token) {
        JwtToken jwtToken = JwtToken.builder()
                .appUser(appUser)
                .jwtToken(token)
                .isExpired(false)
                .build();
        jwtTokenRepository.save(jwtToken);
    }
}
