package com.netcracker.group5.medkit.model.dto.user;

public class RegisterPatientResponseItem {

    private String email;

    public RegisterPatientResponseItem(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
