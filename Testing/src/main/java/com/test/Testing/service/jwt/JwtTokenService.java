package com.test.Testing.service.jwt;

import com.test.Testing.data.dto.response.JwtTokenResponse;

public interface JwtTokenService {
    JwtTokenResponse getJwtTokens(String email);
}
