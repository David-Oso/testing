package com.test.Testing.security.jwtToken;

import com.test.Testing.data.model.AppUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class JwtToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String jwtToken;
    @Enumerated(EnumType.STRING)
    private final JwtTokenType jwtTokenType = JwtTokenType.BEARER;
    private boolean isExpired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_jwt_id")
    private AppUser appUser;
}
