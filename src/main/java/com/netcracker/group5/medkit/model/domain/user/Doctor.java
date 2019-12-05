package com.netcracker.group5.medkit.model.domain.user;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class Doctor extends User {

    private String name;
    private String surname;
    private Speciality speciality;
    private LocalDateTime birthDate;
    private String workExperience;
    private Location workPlace;
    private String phoneNumber;
    private List<Patient> patients;

    private Doctor() {
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

    @Override
    public String toString() {
        return "Doctor{" +
                super.toString() +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", speciality=" + speciality +
                ", birthDate=" + birthDate +
                ", workExperience='" + workExperience + '\'' +
                ", workPlace=" + workPlace +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", patients=" + patients +
                '}';
    }

    public static Builder newBuilder() {
        return new Doctor().new Builder();
    }

    public class Builder extends User.AbstractBuilder<Builder> {

        private Builder() {
        }

        public Builder setName(String name) {
            Doctor.this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            Doctor.this.surname = surname;
            return this;
        }

        public Builder setSpeciality(Speciality speciality) {
            Doctor.this.speciality = speciality;
            return this;
        }

        public Builder setDateOfBirth(LocalDateTime dateOfBirth) {
            Doctor.this.birthDate = dateOfBirth;
            return this;
        }

        public Builder setWorkExperience(String workExperience) {
            Doctor.this.workExperience = workExperience;
            return this;
        }

        public Builder setWorkPlace(Location workPlace) {
            Doctor.this.workPlace = workPlace;
            return this;
        }

        public Builder setPhoneNumber(String phoneNumber) {
            Doctor.this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder setPatients(List<Patient> patients) {
            Doctor.this.patients = patients;
            return this;
        }

        public Doctor build() {
            return Doctor.this;
        }
    }
}
