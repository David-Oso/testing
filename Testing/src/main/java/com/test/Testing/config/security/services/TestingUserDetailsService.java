package com.test.Testing.config.security.services;

import com.test.Testing.config.security.user.AuthenticatedUser;
import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.repository.AppUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestingUserDetailsService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(
                ()-> new UsernameNotFoundException("User with this email not found!"));
        return new AuthenticatedUser(appUser);
    }
}

