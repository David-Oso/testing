package com.test.Testing.config.security.provider;

import com.test.Testing.config.security.services.TestingUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TestingAuthenticationProvider implements AuthenticationProvider {
    private final TestingUserDetailsService testingUserDetailsService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String requestName = authentication.getPrincipal().toString();
        final String requestPassword = authentication.getCredentials().toString();

        final UserDetails userDetails = testingUserDetailsService.loadUserByUsername(requestName);
        final String username = userDetails.getUsername();
        final String password = userDetails.getPassword();

        if(passwordEncoder.matches(requestPassword, password)){
            return new UsernamePasswordAuthenticationToken(
                    username,
                    password,
                    userDetails.getAuthorities());
        }
        throw new BadCredentialsException("Incorrect username or password");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
