package com.netcracker.group5.medkit.model.dto.user;

import javax.validation.constraints.NotBlank;

public class EditPasswordRequestItem {

    @NotBlank
    private String oldPassword;

    @NotBlank
    private String newPassword;

    public EditPasswordRequestItem() {
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
