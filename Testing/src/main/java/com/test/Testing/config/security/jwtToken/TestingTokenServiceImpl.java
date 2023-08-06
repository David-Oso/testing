package com.test.Testing.config.security.jwtToken;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class TestingTokenServiceImpl implements TestingTokenService {
    private final TestingTokenRepository testingTokenRepository;

    @Override
    public void saveToken(TestingToken testingToken) {
        testingTokenRepository.save(testingToken);
    }

    @Override
    public Optional<TestingToken> getValidTokenByAnyToken(String anyToken) {
        return testingTokenRepository.findJwtTokenByToken(anyToken);
    }

    @Override
    public void revokeToken(String accessToken) {
        final TestingToken testingToken = getValidTokenByAnyToken(accessToken)
                .orElse(null);
        if(testingToken != null){
            testingToken.setRevoked(true);
            testingTokenRepository.save(testingToken);
        }
    }
}
