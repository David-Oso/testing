package com.test.Testing.security.jwtToken;

import com.test.Testing.data.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
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
