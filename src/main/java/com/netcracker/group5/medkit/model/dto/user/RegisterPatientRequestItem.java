package com.netcracker.group5.medkit.model.dto.user;

import com.netcracker.group5.medkit.model.domain.user.Sex;

import javax.validation.constraints.*;
import java.time.LocalDate;

public class RegisterPatientRequestItem {

    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @NotBlank(message = "Last name is mandatory")
    private String lastName;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "^[+][0-9]{12}$")
    private String phoneNumber;

    @NotNull(message = "Email is mandatory")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @Size(min = 3, message = "Password is too short")
    private String password;

    private LocalDate dateOfBirth;
    private Sex gender;

    @PositiveOrZero(message = "Weight must be positive")
    private float weight;

    @PositiveOrZero(message = "Height must be positive")
    private float height;

    private String location;

    public RegisterPatientRequestItem() {
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

    @Override
    public String toString() {
        return "RegisterPatientRequestItem{" +
                "name='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDate=" + dateOfBirth +
                ", sex=" + gender +
                ", weight=" + weight +
                ", height=" + height +
                ", location='" + location + '\'' +
                '}';
    }
}
