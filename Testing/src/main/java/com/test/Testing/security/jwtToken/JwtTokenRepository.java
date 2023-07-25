package com.test.Testing.security.jwtToken;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JwtTokenRepository extends JpaRepository <JwtToken, Long>{
    @Query(value = """
      select  token from JwtToken   token inner join  AppUser  appuser
      on token.appUser.id = appuser.id\s
       where appuser.id = :id and (token.isExpired = false)
       """)
    List<JwtToken> findAllValidTokenByUser(Long id);
    Optional<JwtToken> findBy(String token);
}
