package com.netcracker.group5.medkit.model.dto.user;

import com.netcracker.group5.medkit.model.domain.user.Role;

import java.util.Objects;

public class LoginUserResponseItem {

    private Long id;
    private String email;
    private String password;
    private Role role;

    public LoginUserResponseItem() {
    }

    public LoginUserResponseItem(Long id, String email, String password, Role role) {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUserResponseItem loginUserResponseItem = (LoginUserResponseItem) o;
        return Objects.equals(id, loginUserResponseItem.id) &&
                role == loginUserResponseItem.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", role=" + role +
                '}';
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
