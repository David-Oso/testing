package com.test.Testing.service.jwt;

import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.security.JwtUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JwtTokenTokenServiceImpl implements JwtTokenService {
    private final JwtUtils jwtUtils;

    @Override
    public JwtTokenResponse getJwtTokens(String email) {
        String accessToken = jwtUtils.generateAccessToken(email);
        String refreshToken = jwtUtils.generateRefreshToken(email);

        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
}
