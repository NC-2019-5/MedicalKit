package com.netcracker.group5.medkit.model.dto.user;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Role;
import com.netcracker.group5.medkit.model.domain.user.Sex;

import java.time.LocalDate;

public class GetPatientResponseItem {
    private Long id;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private Sex sex;
    private float weight;
    private float height;
    private String location;
    private String phoneNumber;
    private String email;
    private String password;
    private Role role;

    public GetPatientResponseItem() {
    }

    public GetPatientResponseItem(Patient patient) {
        this.id = patient.getId();
        this.name = patient.getName();
        this.surname = patient.getSurname();
        this.birthDate = patient.getBirthDate();
        this.sex = patient.getSex();
        this.weight = patient.getWeight();
        this.height = patient.getHeight();
        this.location = patient.getLocation();
        this.phoneNumber = patient.getPhoneNumber();
        this.email = patient.getEmail();
        this.password = patient.getPassword();
        this.role = patient.getRole();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
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
