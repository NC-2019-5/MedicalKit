package com.netcracker.group5.medkit.model.dto.user;

public class EditPasswordRequestItem {

    private String oldPassword;
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
