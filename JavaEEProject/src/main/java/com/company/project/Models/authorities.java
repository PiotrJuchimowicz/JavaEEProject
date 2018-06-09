package com.company.project.Models;

import org.junit.jupiter.api.Test;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Null;

@Entity
@Table(name = "authorities")
public class authorities {

    public enum Role {
        CLIENT, ADMIN, EMPLOYEE


    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long authoritiyId;

    @OneToOne
    @JoinColumn(name = "username")
    private UserDTO username;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role authority;

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
