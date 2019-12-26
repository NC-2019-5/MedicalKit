package com.netcracker.group5.medkit.model.dto.user;

import com.netcracker.group5.medkit.model.domain.user.Patient;
import com.netcracker.group5.medkit.model.domain.user.Sex;

import java.time.LocalDate;

public class GetPatientResponseItem {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Sex gender;
    private float weight;
    private float height;
    private String location;
    private String phoneNumber;
    private String email;
    private String password; // -

    public GetPatientResponseItem() {
    }

    public GetPatientResponseItem(Patient patient) {
        this.firstName = patient.getName();
        this.lastName = patient.getSurname();
        this.dateOfBirth = patient.getBirthDate();
        this.gender = patient.getSex();
        this.weight = patient.getWeight();
        this.height = patient.getHeight();
        this.location = patient.getLocation();
        this.phoneNumber = patient.getPhoneNumber();
        this.email = patient.getEmail();
        this.password = patient.getPassword();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
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
}
