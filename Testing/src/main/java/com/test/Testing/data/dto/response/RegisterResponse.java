package com.test.Testing.data.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class RegisterResponse {
    private String message;
    private boolean isSuccess;
   private JwtTokenResponse jwtTokenResponse;
}
