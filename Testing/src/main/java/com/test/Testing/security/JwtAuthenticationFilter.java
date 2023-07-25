package com.test.Testing.security;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtUtils jwtUtils;
    private final JwtUserDetailService jwtUserDetailService;

    @Override
    protected void doFilterInternal(@Nonnull HttpServletRequest request,
                                    @Nonnull HttpServletResponse response,
                                    @Nonnull FilterChain filterChain) throws ServletException, IOException {
        String token = this.extractTokenFromRequest(request);
        if(StringUtils.hasText(token) && jwtUtils.validateToken(token)){

        }
    }

//    final String authHeader = request.getHeader("Authorization");
//        final String jwt;
//        final String userName;
//
//        jwt = authHeader.substring(7);
//        userName = jwtService.extractUsername(jwt);
//        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
//            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
//            var isTokenValid = jwtTokenRepository.findByJwtToken(jwt)
//                    .map(token -> !token.isExpired() && !token.isRevoked())
//                    .orElse(false);
//            if(jwtService.isTokenValid(jwt, userDetails) && isTokenValid){
//                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(authToken);
//            }
//        }
//        filterChain.doFilter(request, response);

    private String extractTokenFromRequest(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        return StringUtils.hasText(authHeader) && StringUtils.
                startsWithIgnoreCase(authHeader, "Bearer ") ?
                authHeader.substring(7) : null;
    }
}
