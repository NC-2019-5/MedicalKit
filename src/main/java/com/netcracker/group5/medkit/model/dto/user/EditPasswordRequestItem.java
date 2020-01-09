package com.netcracker.group5.medkit.model.dto.user;

import javax.validation.constraints.NotBlank;

public class EditPasswordRequestItem {

    @NotBlank
    private String password;

    @NotBlank
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
