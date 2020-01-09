package com.netcracker.group5.medkit.model.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR,
    PATIENT,
    DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
