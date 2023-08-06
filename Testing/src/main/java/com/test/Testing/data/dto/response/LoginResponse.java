package com.test.Testing.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class LoginResponse {
    private JwtTokenResponse jwtTokenResponse;
}
