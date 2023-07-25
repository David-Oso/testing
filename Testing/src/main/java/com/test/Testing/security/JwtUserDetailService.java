package com.test.Testing.security;

import com.test.Testing.data.model.AppUser;
import com.test.Testing.data.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {
    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("User not found!"));

        AuthenticatedUser authenticatedUser = new AuthenticatedUser();
        authenticatedUser.setAppUser(appUser);
        return authenticatedUser;
    }
}
