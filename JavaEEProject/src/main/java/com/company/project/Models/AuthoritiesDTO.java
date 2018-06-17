package com.company.project.Models;

import org.hibernate.annotations.ColumnDefault;
import org.junit.jupiter.api.Test;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "authorities")
public class AuthoritiesDTO {

    public enum Role {
        ROLE_CLIENT, ROLE_ADMIN, ROLE_EMPLOYEE

    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authoritiyId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private UserDTO username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

    public AuthoritiesDTO(UserDTO username, Role authority) {
        this.username = username;
        this.authority = authority;
    }

    public AuthoritiesDTO() {
    }

    public long getAuthoritiyId() {
        return authoritiyId;
    }

    public void setAuthoritiyId(long authoritiyId) {
        this.authoritiyId = authoritiyId;
    }

    public UserDTO getUsername() {
        return username;
    }

    public void setUsername(UserDTO username) {
        this.username = username;
    }

    public Role getAuthority() {
        return authority;
    }

    public void setAuthority(Role authority) {
        this.authority = authority;
    }


}
