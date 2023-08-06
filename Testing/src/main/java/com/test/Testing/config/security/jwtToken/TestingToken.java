package com.test.Testing.config.security.jwtToken;

import com.test.Testing.data.model.AppUser;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestingToken {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true)
    private String token;
    private boolean isRevoked;
    private boolean isExpired;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_jwt_id")
    private AppUser appUser;
}
