package com.netcracker.group5.medkit.model.domain.user;

import com.netcracker.group5.medkit.model.domain.Requestable;
import com.netcracker.group5.medkit.model.domain.request.Request;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Doctor extends User implements Requestable {

    private String name;
    private String surname;
    private Speciality speciality;
    private LocalDateTime birthDate;
    private String workExperience;
    private Location workPlace;
    private String phoneNumber;
    private List<Patient> patients;

    public Doctor(Long id, String email, String password, Role role, List<Request> notifications, String name, String surname, Speciality speciality, LocalDateTime birthDate, String workExperience, Location workPlace, String phoneNumber, List<Patient> patients) {
        super(id, email, password, role, notifications);
        this.surname = surname;
        this.name = name;
        this.speciality = speciality;
        this.birthDate = birthDate;
        this.workExperience = workExperience;
        this.workPlace = workPlace;
        this.phoneNumber = phoneNumber;
        this.patients = patients;
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

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public LocalDateTime getDateOfBirth() {
        return birthDate;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.birthDate = dateOfBirth;
    }

    public String getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(String workExperience) {
        this.workExperience = workExperience;
    }

    public Location getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(Location workPlace) {
        this.workPlace = workPlace;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(name, doctor.name) &&
                Objects.equals(surname, doctor.surname) &&
                speciality == doctor.speciality &&
                Objects.equals(birthDate, doctor.birthDate) &&
                Objects.equals(workExperience, doctor.workExperience) &&
                Objects.equals(workPlace, doctor.workPlace) &&
                Objects.equals(phoneNumber, doctor.phoneNumber) &&
                Objects.equals(patients, doctor.patients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, surname, speciality, birthDate, workExperience, workPlace, phoneNumber, patients);
    }
}
