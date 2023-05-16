package com.example.volunteerC.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, VOLUNTEER, CLIENT;

    @Override
    public String getAuthority() {
        return name();
    }
}
