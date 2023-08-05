package com.test.Testing.config.security.jwtToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository<JwtToken, Long> {
    @Query("""
           select t from JwtToken  t inner join AppUser appuser\s
           on t.appUser.id = appuser.id\s
           where appuser.id = :id and (t.isExpired = false or t.isRevoked = false)
           """)
    List<JwtToken> findAllValidTokenByUser(Long id);
    Optional<JwtToken> findJwtTokenByToken(String token);
}
