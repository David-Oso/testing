package com.test.Testing.config.security.jwtToken;

import java.util.Optional;

public interface TestingTokenService {
    void saveToken(TestingToken testingToken);
    Optional<TestingToken> getValidTokenByAnyToken(String anyToken);
    void revokeToken(String accessToken);
}
