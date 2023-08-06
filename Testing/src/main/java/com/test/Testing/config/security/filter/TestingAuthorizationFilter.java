package com.test.Testing.config.security.filter;

import com.test.Testing.config.security.services.TestingUserDetailsService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@AllArgsConstructor
public class TestingAuthorizationFilter extends OncePerRequestFilter {

    private final TestingUserDetailsService testingUserDetailsService;
    private final

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String bearer = "Bearer ";
        if(StringUtils.hasText(authHeader) &&
                StringUtils.startsWithIgnoreCase(authHeader, bearer)){
            final String accessToken = authHeader.substring(bearer.length());
            if(jwtSer)
        }

    }
}
