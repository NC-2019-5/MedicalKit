package com.netcracker.group5.medkit.model.domain.user;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMINISTRATOR("Administrator"),
    PATIENT("Patient"),
    DOCTOR("Doctor");

    private String roleName;

    Role(String roleName) {
        this.roleName = roleName;
    }

    public static Role getRoleByName(String name) {
        if (name.equals("Administrator")) {
            return ADMINISTRATOR;
        }
        if (name.equals("Patient")) {
            return PATIENT;
        }
        if (name.equals("Doctor")) {
            return DOCTOR;
        }
        return null;
    }

    @Override
    public String getAuthority() {
        return name();
    }

    public String getRoleName() {
        return roleName;
    }
}
