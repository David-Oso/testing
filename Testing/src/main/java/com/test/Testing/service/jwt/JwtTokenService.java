package com.test.Testing.service.jwt;

import com.test.Testing.data.dto.response.JwtTokenResponse;
import com.test.Testing.data.model.AppUser;

public interface JwtTokenService {
    JwtTokenResponse getJwtTokens(AppUser appUser);
}
