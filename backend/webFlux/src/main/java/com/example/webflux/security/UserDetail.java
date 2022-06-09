package com.example.webflux.security;



import com.example.webflux.security.jwt.model.JwtPayload;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;

public final class UserDetail implements UserDetails {
    private HashSet<GrantedAuthority> authorities = new HashSet();
    private JwtPayload user;

    UserDetail() {
        this.authorities.add(new SimpleGrantedAuthority("USER"));
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public String getPassword() {
        return "password";
    }

    public String getUsername() {
        return this.user.getUsername();
    }

    public JwtPayload getUser() {
        return this.user;
    }

    void setUser(JwtPayload user) {
        this.user = user;
        String[] roles = user.getRole().split(",");
        for (String role : roles) {
            this.authorities.add(new SimpleGrantedAuthority(role));
        }
    }
}
