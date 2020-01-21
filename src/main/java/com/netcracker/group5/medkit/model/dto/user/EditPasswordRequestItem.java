package com.netcracker.group5.medkit.model.dto.user;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EditPasswordRequestItem {

    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, message = "Old password is too short (3 characters min)")
    private String password;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, message = " New password is too short (3 characters min)")
    private String newPassword;

    public EditPasswordRequestItem() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
