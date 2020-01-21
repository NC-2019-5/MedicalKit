package com.netcracker.group5.medkit.model.dto.user;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginUserRequestItem {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 3, message = "Password is too short (3 characters min)")
    private String password;

    public LoginUserRequestItem() {
    }

    public LoginUserRequestItem(String email, String password) {
        this.email = email;
        this.password = password;
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
}
