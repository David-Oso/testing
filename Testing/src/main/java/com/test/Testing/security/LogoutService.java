package com.test.Testing.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
@Service
@RequiredArgsConstructor
public class LogoutService implements LogoutHandler {

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

    }
    private String extractTokenFromRequest(HttpServletRequest request) {
        final  String authHeader = request.getHeader("Authorization");
        return StringUtils.hasText(authHeader) && StringUtils.
                startsWithIgnoreCase(authHeader, "Bearer ") ?
                authHeader.substring(7) : null;
    }

//      final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        if(authHeader == null || !authHeader.startsWith("Bearer ")){
//            return;
//        }
//        jwt = authHeader.substring(7);
//        var storedToken = jwtTokenRepository.findByJwtToken(jwt).orElse(null);
//        if(storedToken != null){
//            storedToken.setExpired(true);
//            storedToken.setRevoked(true);
//            jwtTokenRepository.save(storedToken);
//            SecurityContextHolder.clearContext();
//        }
//    }
}
