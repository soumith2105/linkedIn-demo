package com.kodem.demo.linkedindemo.authentication.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.kodem.demo.linkedindemo.userModel.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


// This class will be called by UserDtlsService's loadUserByUsername method
public class UserDtls implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String username;
    private String email;
    private String password;
    private boolean active;
    private Collection<? extends GrantedAuthority> authorities;

    // Constructors
    public UserDtls(String username, String email, String password, boolean active,
            Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.active = active;
        this.authorities = authorities;
    }

    public UserDtls(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        int roles = user.getRoles();
        if (roles >= 2) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.active = user.getActive();
        this.authorities = authorities;
    }

    // Roles
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    // Password
    @Override
    public String getPassword() {
        return this.password;
    }

    // Username
    @Override
    public String getUsername() {
        return this.username;
    }

    // Email
    public String getEmail() {
        return this.email;
    }

    // Expired or not
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // Locked or not
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // Credentials expired or not
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Account enabled or not
    @Override
    public boolean isEnabled() {
        return this.active;
    }
}
