package com.test.Testing.config.security.filter;

import com.test.Testing.config.security.jwtToken.TestingTokenRepository;
import com.test.Testing.config.security.services.JwtService;
import com.test.Testing.config.security.services.TestingUserDetailsService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Component
@AllArgsConstructor
public class TestingAuthorizationFilter extends OncePerRequestFilter {

    private final TestingUserDetailsService testingUserDetailsService;
    private final TestingTokenRepository testingTokenRepository;
    private final JwtService jwtService;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader(AUTHORIZATION);
        final String bearer = "Bearer ";
        if(StringUtils.hasText(authHeader) &&
                StringUtils.startsWithIgnoreCase(authHeader, bearer)){
            final String jwtToken = authHeader.substring(bearer.length());
            final String userEmail = jwtService.extractUsername(jwtToken);
            boolean isTokenValid = testingTokenRepository.findJwtTokenByToken(jwtToken)
                    .map(token -> !token.isExpired() && !token.isRevoked())
                    .orElse(false);
            if(jwtService.isValidToken(jwtToken, userEmail) && isTokenValid && userEmail != null){
                UserDetails userDetails =
                        testingUserDetailsService.loadUserByUsername(userEmail);
                final UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,
                                userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource()
                        .buildDetails(request));
                SecurityContextHolder
                        .getContext()
                        .setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
