package com.test.Testing.security;

import com.test.Testing.security.jwtToken.JwtTokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {
    private final JwtTokenRepository jwtTokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String token = extractTokenFromRequest(request);
        var storedToken = jwtTokenRepository.findByJwtToken(token).orElse(null);
        if(storedToken != null){
            storedToken.setExpired(true);
            jwtTokenRepository.save(storedToken);
            SecurityContextHolder.clearContext();
        }
    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        final  String authHeader = request.getHeader("Authorization");
        return StringUtils.hasText(authHeader) && StringUtils.
                startsWithIgnoreCase(authHeader, "Bearer ") ?
                authHeader.substring(7) : null;
    }
}
