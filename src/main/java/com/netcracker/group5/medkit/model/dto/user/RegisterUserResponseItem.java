package com.netcracker.group5.medkit.model.dto.user;

import com.netcracker.group5.medkit.model.domain.user.Role;

public class RegisterUserResponseItem {

    private Long id;
    private String email;
    private String password;
    private Role role;

    public RegisterUserResponseItem() {
    }

    public RegisterUserResponseItem(Long id, String email, String password, Role role) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
